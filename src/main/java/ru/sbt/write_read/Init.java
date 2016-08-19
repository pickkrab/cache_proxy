package ru.sbt.write_read;

import java.io.Serializable;
import java.util.List;

public class Init implements Serializable {

    List<Object> key;
    Object object;

    public Init(List<Object> key, Object object) {
        this.key = key;
        this.object = object;
    }

    public List<Object> getKey() {
        return key;
    }

    public Object getObject() {
        return object;
    }
}
