package com.evolveum.polygon.connector.sqlcaw.rest.util;

import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeUtil;
import org.identityconnectors.framework.common.objects.Name;
import org.identityconnectors.framework.common.objects.Uid;
import org.identityconnectors.framework.common.objects.filter.*;

import java.util.List;

public class FilterHandler implements FilterVisitor<String, String> {

    private static final String ID = "id";
    private static final String EQUAL = "=";
    private static final String ALL_VALUES = "*";

    @Override
    public String visitAndFilter(String s, AndFilter andFilter) {
        return null;
    }

    @Override
    public String visitContainsFilter(String s, ContainsFilter containsFilter) {
        return null;
    }

    @Override
    public String visitContainsAllValuesFilter(String s, ContainsAllValuesFilter containsAllValuesFilter) {
        return null;
    }

    @Override
    public String visitEqualsFilter(String s, EqualsFilter equalsFilter) {
        s = s != null ? s : "";

        Attribute attr = equalsFilter.getAttribute();

        StringBuilder query = new StringBuilder();

        if (attr != null) {
            String singleValue = null;
            String name = attr.getName();
            List value = attr.getValue();

            if (name != null && !name.isEmpty()) {

                if (Uid.NAME.equals(name)) {
                    name = ID;
                } else if (Name.NAME.equals(name)) {
                    name = SqlCAWRestConstants.OBJECT_ATTR_NAME;
                }
            } else {
                name = SqlCAWRestConstants.USER_COD_USU;
            }

            if (value != null && !value.isEmpty()) {
                singleValue = AttributeUtil.getSingleValue(attr).toString();
            } else {
                singleValue = ALL_VALUES;
            }

            query.append(name);
            query.append(EQUAL);
            query.append(singleValue);
        } else {
            query.append(SqlCAWRestConstants.OBJECT_ATTR_UID);
            query.append(EQUAL);
            query.append(ALL_VALUES);
        }

        return query.toString();
    }

    @Override
    public String visitExtendedFilter(String s, Filter filter) {
        return null;
    }

    @Override
    public String visitGreaterThanFilter(String s, GreaterThanFilter greaterThanFilter) {
        return null;
    }

    @Override
    public String visitGreaterThanOrEqualFilter(String s, GreaterThanOrEqualFilter greaterThanOrEqualFilter) {
        return null;
    }

    @Override
    public String visitLessThanFilter(String s, LessThanFilter lessThanFilter) {
        return null;
    }

    @Override
    public String visitLessThanOrEqualFilter(String s, LessThanOrEqualFilter lessThanOrEqualFilter) {
        return null;
    }

    @Override
    public String visitNotFilter(String s, NotFilter notFilter) {
        return null;
    }

    @Override
    public String visitOrFilter(String s, OrFilter orFilter) {
        return null;
    }

    @Override
    public String visitStartsWithFilter(String s, StartsWithFilter startsWithFilter) {
        return null;
    }

    @Override
    public String visitEndsWithFilter(String s, EndsWithFilter endsWithFilter) {
        return null;
    }

    @Override
    public String visitEqualsIgnoreCaseFilter(String s, EqualsIgnoreCaseFilter equalsIgnoreCaseFilter) {
        return null;
    }
}

