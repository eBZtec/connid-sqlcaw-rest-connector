package com.evolveum.polygon.connector.sqlcaw.rest.api;

import java.io.Serializable;

public class RDelta<T extends RObject> implements Serializable {

    private T object;
    private RDeltaType type;

    public T getObject() { return object; }

    public void setObject(T object) { this.object = object; }

    public RDeltaType getType() { return type; }

    public void setType(RDeltaType type) { this.type = type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RDelta rDelta = (RDelta) o;

        if (object != null ? !object.equals(rDelta.object) : rDelta.object != null) return false;

        return type == rDelta.type;
    }

    @Override
    public int hashCode() {
        int result = object != null ? object.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RDelta{");
        sb.append("o=").append(object);
        sb.append(", t=").append(type);
        sb.append("}");
        return sb.toString();
    }
}

