package com.ming.consumer.config;

import cn.hutool.json.JSONUtil;
import com.ming.exception.DemoException;
import com.ming.exception.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ming.feign.plus.decoder.FeignErrorDecoder;

@Configuration
public class FeignExceptionConfig {
    @Bean
    public FeignErrorDecoder feignExceptionDecoder() {
        return (methodName, response, e) -> {
            HttpStatus status = JSONUtil.toBean(response, HttpStatus.class);
            return new DemoException(status.getAppName(), status.getCode(), status.getMessage(), status.getDebugStackTrace());
        };
    }
}
