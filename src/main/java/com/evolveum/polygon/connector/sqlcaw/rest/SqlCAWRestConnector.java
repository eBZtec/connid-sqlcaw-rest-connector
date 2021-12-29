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
import com.evolveum.polygon.connector.sqlcaw.rest.util.SqlCAWRestConstants;
import org.apache.cxf.interceptor.Fault;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.*;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.Connector;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.operations.SchemaOp;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ConnectorClass(displayNameKey = "sqlcawrest.connector.display", configurationClass = SqlCAWRestConfiguration.class)
public class SqlCAWRestConnector extends AbstractSqlCAWRestConnector implements Connector, TestOp, SchemaOp {

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

    @Override
    public Schema schema() {

        SchemaBuilder sb = new SchemaBuilder(SqlCAWRestConnector.class);
        ObjectClassInfoBuilder ocBuilder = new ObjectClassInfoBuilder();

        ocBuilder.setType(ObjectClass.ACCOUNT_NAME);
        // UID
        ocBuilder.addAttributeInfo(buildAttributeInfo(Uid.NAME, String.class, SqlCAWRestConstants.OBJECT_ATTR_UID,
                AttributeInfo.Flags.NOT_UPDATEABLE, AttributeInfo.Flags.NOT_CREATABLE));
        // Name
        ocBuilder.addAttributeInfo(buildAttributeInfo(Name.NAME, String.class, SqlCAWRestConstants.OBJECT_ATTR_NAME));
        // cod_grupo
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_COD_GRUPO, String.class,
                null));
        // cod_parceiro
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_COD_PARCEIRO, String.class,
                null));
        // cod_tipo_usu
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_COD_TIPO_USU, String.class,
                null));
        // cod_usu
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_COD_USU, String.class, null));
        //dat_alteracao_senha
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_DAT_ALTERACAO_SENHA, String.class,
                null));
        // dat_ultimo_acesso
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_DAT_ULTIMO_ACESSO, String.class,
                null));
        //dsc_email
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_DSC_EMAIL, String.class,
                null));
        // flg_ativo
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_FLG_ATIVO, String.class,
                null));
        // flg_usu_bloqueado
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_FLG_USU_BLOQUEADO, String.class,
                null));
        // id_usu
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_ID_USU, String.class,
                null));
        // nom_usu
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_NOM_USU, String.class,
                null));
        // IDC_CTL_SEN_EXN
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_IDC_CTL_SEN_EXN, String.class,
                null));
        // qtd_senha_invalida
        ocBuilder.addAttributeInfo(buildAttributeInfo(SqlCAWRestConstants.USER_QTD_SENHA_INVALIDA, String.class,
                null));
        // Password
        ocBuilder.addAttributeInfo(buildAttributeInfo(OperationalAttributes.PASSWORD_NAME, GuardedString.class,
                SqlCAWRestConstants.USER_ATTR_PASSWORD, AttributeInfo.Flags.NOT_READABLE,
                AttributeInfo.Flags.NOT_RETURNED_BY_DEFAULT));
        // Administrative Status
        ocBuilder.addAttributeInfo(buildAttributeInfo(OperationalAttributes.ENABLE_NAME, Boolean.class,
                SqlCAWRestConstants.USER_ATTR_ENABLED));
        sb.defineObjectClass(ocBuilder.build());

        return null;
    }

    private AttributeInfo buildAttributeInfo(String name, Class type, String nativeName, AttributeInfo.Flags... flags) {

        AttributeInfoBuilder aib = new AttributeInfoBuilder(name);
        aib.setType(type);

        if (nativeName == null) {
            nativeName = name;
        }

        aib.setNativeName(nativeName);

        if (flags.length != 0) {
            Set<AttributeInfo.Flags> set = new HashSet<>();
            set.addAll(Arrays.asList(flags));
            aib.setFlags(set);
        }

        return aib.build();
    }
}
