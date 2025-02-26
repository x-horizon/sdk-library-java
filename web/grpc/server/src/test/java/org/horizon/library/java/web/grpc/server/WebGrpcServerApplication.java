package org.horizon.library.java.web.grpc.server;

import org.horizon.library.java.web.grpc.server.autoconfigure.EnableGrpcServerExceptionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableGrpcServerExceptionInterceptor
@SpringBootApplication
public class WebGrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGrpcServerApplication.class, args);
    }

}