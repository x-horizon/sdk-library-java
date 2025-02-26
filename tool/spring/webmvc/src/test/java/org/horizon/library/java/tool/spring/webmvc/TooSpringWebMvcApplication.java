package org.horizon.library.java.tool.spring.webmvc;

import org.horizon.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcExceptionInterceptor;
import org.horizon.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcResponseBodyAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableWebMvcResponseBodyAdvice
@EnableWebMvcExceptionInterceptor
@SpringBootApplication
public class TooSpringWebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TooSpringWebMvcApplication.class, args);
    }

}