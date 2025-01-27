package cn.library.java.web.grpc.client;

import cn.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcExceptionInterceptor;
import cn.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcResponseBodyAdvice;
import cn.library.java.web.grpc.client.autoconfigure.EnableGrpcClientWebMvcExceptionInterceptor;
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