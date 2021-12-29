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

import org.identityconnectors.common.StringUtil;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.spi.ConfigurationProperty;

public class SqlCAWRestConfiguration extends AbstractConfiguration {

    private static final Log LOG = Log.getLog(SqlCAWRestConfiguration.class);

    private String url;
    private String username;
    private GuardedString password;

    @Override
    public void validate() {
        String exceptionMsg = null;

        if (StringUtil.isBlank(url)) {
            exceptionMsg = "Connection URL is not provided";
        } else if (StringUtil.isBlank(username)) {
            exceptionMsg = "The username of the technical account is not provided";
        } else if(password == null) {
            exceptionMsg = "The username of the technical account is not provided";
        }

        if (!StringUtil.isBlank(exceptionMsg)) {
            LOG.info("End of configuration validation procedure.");
            return;
        }

        LOG.error("{0}", exceptionMsg);
        throw new ConfigurationException(exceptionMsg);
    }

    @ConfigurationProperty(
            displayMessageKey = "sqlcawrest.config.url",
            helpMessageKey = "sqlcawrest.config.url.help",
            order = 10
    )
    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @ConfigurationProperty(
            displayMessageKey = "sqlcawrest.config.username",
            helpMessageKey = "sqlcawrest.config.username.help",
            order = 10
    )
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @ConfigurationProperty(
            displayMessageKey = "sqlcawrest.config.password",
            helpMessageKey = "sqlcawrest.config.password.help",
            order = 10
    )
    public GuardedString getPassword() { return password; }

    public void setPassword(GuardedString password) { this.password = password; }
}