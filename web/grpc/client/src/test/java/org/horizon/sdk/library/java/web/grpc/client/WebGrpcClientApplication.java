package org.horizon.sdk.library.java.web.grpc.client;

import org.horizon.sdk.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcExceptionInterceptor;
import org.horizon.sdk.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcResponseBodyAdvice;
import org.horizon.sdk.library.java.web.grpc.client.autoconfigure.EnableGrpcClientWebMvcExceptionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableWebMvcResponseBodyAdvice
@EnableWebMvcExceptionInterceptor
@EnableGrpcClientWebMvcExceptionInterceptor
@SpringBootApplication
public class WebGrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGrpcClientApplication.class, args);
    }

}