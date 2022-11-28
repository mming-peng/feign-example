package com.ming.exception;

public class HttpStatus {
    private String appName;
    private int code;
    private String message;
    private String debugStackTrace;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugStackTrace() {
        return debugStackTrace;
    }

    public void setDebugStackTrace(String debugStackTrace) {
        this.debugStackTrace = debugStackTrace;
    }
}
