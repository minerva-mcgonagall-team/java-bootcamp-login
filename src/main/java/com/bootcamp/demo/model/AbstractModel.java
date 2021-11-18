package com.bootcamp.demo.model;

import java.io.Serializable;



public class AbstractModel implements Serializable {
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
