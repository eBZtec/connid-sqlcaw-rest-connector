package com.evolveum.polygon.connector.sqlcaw.rest;

import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.test.common.TestHelpers;

public abstract class BaseTest {

    protected ConnectorFacade setupConnector() {

        SqlCAWRestConfiguration config = new SqlCAWRestConfiguration();
        config.setUrl("http://localhost:8091");
        config.setUsername("user");
        config.setPassword(new GuardedString("user".toCharArray()));

        return setupConnector(config);
    }

    protected ConnectorFacade setupConnector(SqlCAWRestConfiguration config) {

        ConnectorFacadeFactory factory = ConnectorFacadeFactory.getInstance();

        APIConfiguration impl = TestHelpers.createTestConfiguration(SqlCAWRestConnector.class, config);

        impl.getResultsHandlerConfiguration().setEnableAttributesToGetSearchResultsHandler(false);
        impl.getResultsHandlerConfiguration().setEnableCaseInsensitiveFilter(false);
        impl.getResultsHandlerConfiguration().setEnableFilteredResultsHandler(false);
        impl.getResultsHandlerConfiguration().setEnableNormalizingResultsHandler(false);
        impl.getResultsHandlerConfiguration().setFilteredResultsHandlerInValidationMode(false);

        return factory.newInstance(impl);
    }
}
