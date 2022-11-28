package com.ming.entity;

import java.io.Serializable;

public class Demo implements Serializable {
    private Long id;
    private String s;

    public Demo() {

    }

    public Demo(Long id, String s) {
        this.id = id;
        this.s = s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", s='" + s + '\'' +
                '}';
    }
}
