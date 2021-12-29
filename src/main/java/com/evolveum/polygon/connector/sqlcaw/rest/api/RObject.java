package com.evolveum.polygon.connector.sqlcaw.rest.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_type"
)
@JsonSubTypes(
        @JsonSubTypes.Type(value = RUser.class, name = "user")
)
public class RObject {
    private String id;
    private String name;
    private Long changed;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChanged() {
        return changed;
    }

    public void setChanged(Long changed) {
        this.changed = changed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        RObject rObject = (RObject) o;

        if (id != null ? !id.equals(rObject.id) : rObject.id != null) return false;
        if (name != null ? !name.equals(rObject.name) : rObject.name != null) return false;
        return changed != null ? changed.equals(rObject.changed) : rObject.changed == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (changed != null ? changed.hashCode() : 0);

        return result;
    }
}
