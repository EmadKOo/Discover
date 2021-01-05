package com.example.discover.pojo.WorldWide;

import java.io.Serializable;

public class Source implements Serializable {
    public String id ;
    public String name ;

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Source() {
    }

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
}
