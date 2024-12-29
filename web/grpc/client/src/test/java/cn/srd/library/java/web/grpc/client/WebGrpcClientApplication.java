package cn.srd.library.java.web.grpc.client;

import cn.srd.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcExceptionInterceptor;
import cn.srd.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcResponseBodyAdvice;
import cn.srd.library.java.web.grpc.client.autoconfigure.EnableGrpcClientWebMvcExceptionInterceptor;
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