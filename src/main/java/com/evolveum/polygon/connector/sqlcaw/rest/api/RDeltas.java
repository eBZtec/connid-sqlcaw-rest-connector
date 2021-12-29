package com.evolveum.polygon.connector.sqlcaw.rest.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RDeltas implements Serializable {

    private List<RDelta> deltas;

    public List<RDelta> getDeltas() {
        if (deltas == null) {
            deltas = new ArrayList<>();
        }

        return deltas;
    }

    public void setDeltas(List<RDelta> deltas) { this.deltas = deltas; }
}

