package com.evolveum.polygon.connector.sqlcaw.rest.util;

import com.evolveum.polygon.connector.sqlcaw.rest.SqlCAWRestConfiguration;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;

public abstract class ConnectionHandler {

    private SqlCAWRestConfiguration configuration;

    public ConnectionHandler(SqlCAWRestConfiguration configuration) { this.configuration = configuration; }

    protected <T> T setupClient(Class<T> type) {
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
        bean.setResourceClass(type);
        bean.setAddress(this.configuration.getUrl());

        String username = this.configuration.getUsername();
        if (username != null) {
            bean.setUsername(username);

            StringAccessor accessor = new StringAccessor();
            if (this.configuration.getPassword() != null) {
                this.configuration.getPassword().access(accessor);
                bean.setPassword(accessor.getValue());
            }
        }

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        provider.setMapper(mapper);

        bean.setProvider(provider);

        return bean.create(type);
    }

    protected void setConfiguration(SqlCAWRestConfiguration configuration) { this.configuration = configuration; }
}
