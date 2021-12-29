package com.evolveum.polygon.connector.sqlcaw.rest.util;

import org.identityconnectors.common.security.GuardedString;

public class StringAccessor implements GuardedString.Accessor{

    private String value;

    @Override
    public void access(char[] chars) {

        value = chars == null ? null : String.valueOf(chars);
    }

    public String getValue() {

        return value;
    }
}
