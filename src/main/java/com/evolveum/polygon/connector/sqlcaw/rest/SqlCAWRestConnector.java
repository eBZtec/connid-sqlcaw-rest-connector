/*
 * Copyright (c) 2010-2014 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evolveum.polygon.connector.sqlcaw.rest;

import com.evolveum.polygon.connector.sqlcaw.rest.api.RObject;
import com.evolveum.polygon.connector.sqlcaw.rest.api.RUser;
import com.evolveum.polygon.connector.sqlcaw.rest.api.TestService;
import com.evolveum.polygon.connector.sqlcaw.rest.api.UserService;
import com.evolveum.polygon.connector.sqlcaw.rest.util.AbstractSqlCAWRestConnector;
import org.apache.cxf.interceptor.Fault;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.*;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.ConnectorObject;
import org.identityconnectors.framework.common.objects.Uid;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.Connector;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.operations.TestOp;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.Set;

@ConnectorClass(displayNameKey = "sqlcawrest.connector.display", configurationClass = SqlCAWRestConfiguration.class)
public class SqlCAWRestConnector extends AbstractSqlCAWRestConnector implements Connector, TestOp {

    private static final Log LOG = Log.getLog(SqlCAWRestConnector.class);

    private SqlCAWRestConfiguration configuration;
    private SqlCAWRestConnection connection;

    private UserService userService;
    private TestService testService;

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void init(Configuration configuration) {
        this.configuration = (SqlCAWRestConfiguration)configuration;
        this.connection = new SqlCAWRestConnection(this.configuration);

        connection.setupConnection();

        userService = connection.getUserService();
        testService = connection.getTestService();

        LOG.info("Initialization of configuration has finished.");
    }

    @Override
    public void dispose() {
        configuration = null;
        userService = null;
        testService = null;

        if (connection != null) {
            connection.dispose();
            connection = null;
        }
    }

    @Override
    protected void handleGenericException(Exception ex, String message) {

        if (ex instanceof ConnectException || ex instanceof Fault || ex instanceof ProcessingException) {
            throw new ConnectionFailedException(message + ", reason: " + ex.getMessage(), ex);
        }

        if ( ex instanceof ConnectorException || ex instanceof UnsupportedOperationException || ex instanceof IllegalArgumentException) {
            throw (RuntimeException) ex;
        }

        if (ex instanceof NotAuthorizedException) {
            throw new InvalidCredentialException("Not authorized");
        }

        if (ex instanceof NotFoundException) {
            throw new UnknownUidException(message);
        }

        if (ex instanceof IOException) {
            if ((ex instanceof SocketTimeoutException || ex instanceof NoRouteToHostException)) {
                throw new OperationTimeoutException(message + ", timeout occured, reason: " + ex.getMessage(), ex);
            }

            throw new ConnectorIOException(message + " IO exception occcured, reason: " + ex.getMessage(), ex);
        }

        if (ex instanceof ClientErrorException) {
            Response response = ((ClientErrorException) ex).getResponse();

            if (response != null) {
                int status = response.getStatus();

                if (status == 409) {
                    throw new AlreadyExistsException(message + " Conflict during operation execution occured, reason:" + ex.getMessage(), ex);

                } else if (status == 422) {
                    throw new InvalidAttributeValueException(message + ", reason: " + ex.getMessage(), ex);

                } else {
                    throw new ConnectorException(message + ", reason: " + ex.getMessage(), ex);

                }
            }

            throw new ConnectorException(message + ", reason: " + ex.getMessage(), ex);
        }
    }

    @Override
    protected RUser translateUser(Uid uid, Set<Attribute> attributes) {
        return null;
    }

    @Override
    protected ConnectorObject translate(RObject object) {
        return null;
    }

    @Override
    public void test() {
        try {
            connection.getTestService().test();
            LOG.info("Test service execution finished");
        } catch(Exception ex) {
            handleGenericException(ex, "Test connection failed, reason: " + ex.getMessage());
        }
    }
}
