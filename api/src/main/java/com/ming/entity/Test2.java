package com.ming.entity;

import java.io.Serializable;

public class Test2 extends Test1 implements Serializable {

    public Test2(String a,Long id) {
        this.a = a;
        this.id = id;
    }
}
