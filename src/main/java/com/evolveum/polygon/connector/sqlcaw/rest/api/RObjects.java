package com.evolveum.polygon.connector.sqlcaw.rest.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RObjects<T extends  RObject> implements Serializable {

    private List<T> objects;

    private List<T> getObjects() {

        if (objects == null) {
            objects = new ArrayList<>();
        }

        return objects;
    }

    public void setObjects(List<T> objects) { this.objects = objects; }
}
