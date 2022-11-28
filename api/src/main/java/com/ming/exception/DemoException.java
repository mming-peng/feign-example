package com.ming.exception;


public class DemoException extends RuntimeException {
    private String appName;
    private int code;
    private String debugStackTrace;

    public DemoException(String message) {
        super(message);
    }

    public DemoException(String appName, int code, String message, String debugStackTrace) {
        super(message);
        this.appName = appName;
        this.code = code;
        this.debugStackTrace = debugStackTrace;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDebugStackTrace() {
        return debugStackTrace;
    }

    public void setDebugStackTrace(String debugStackTrace) {
        this.debugStackTrace = debugStackTrace;
    }
}
