package com.ming.entity;

import java.io.Serializable;

public class TestDemo implements Serializable {
    private Test1 test1;
    private String b;

    public TestDemo() {
    }

    public TestDemo(Test1 test1, String b) {
        this.test1 = test1;
        this.b = b;
    }

    public Test1 getTest1() {
        return test1;
    }

    public void setTest1(Test1 test1) {
        this.test1 = test1;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
