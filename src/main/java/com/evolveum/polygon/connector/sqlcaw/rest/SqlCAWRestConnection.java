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

import com.evolveum.polygon.connector.sqlcaw.rest.api.TestService;
import com.evolveum.polygon.connector.sqlcaw.rest.api.UserService;
import com.evolveum.polygon.connector.sqlcaw.rest.util.ConnectionHandler;
import org.identityconnectors.common.logging.Log;

public class SqlCAWRestConnection extends ConnectionHandler {

    private static final Log LOG = Log.getLog(SqlCAWRestConnection.class);

    private SqlCAWRestConfiguration configuration;

    private static final Class USER_CLASS = UserService.class;
    private static final Class TEST_SERVICE = TestService.class;

    private TestService testService;
    private UserService userService;

    public SqlCAWRestConnection(SqlCAWRestConfiguration configuration) {
        super(configuration);
    }

    public void setupConnection() {
        userService = (UserService) setupClient(USER_CLASS);
        testService = (TestService) setupClient(TEST_SERVICE);

        LOG.info("The connector services initialized successfully");
    }

    public UserService getUserService() { return userService; }

    public TestService getTestService() { return testService; }

    public void dispose() {

        setConfiguration(null);
        userService = null;
        testService = null;

        LOG.info("The configuration was disposed successfully");
    }
}