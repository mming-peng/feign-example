package com.ming.provider.exception;

import com.ming.exception.DemoException;
import com.ming.exception.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理
 * Created by macro on 2020/2/27.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = DemoException.class)
    public HttpStatus handle(DemoException e) {
        HttpStatus httpStatus = new HttpStatus();
        httpStatus.setAppName("test");
        httpStatus.setCode(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpStatus.setMessage(e.getMessage());
        httpStatus.setDebugStackTrace("testdebug");
        return httpStatus;
    }
}
