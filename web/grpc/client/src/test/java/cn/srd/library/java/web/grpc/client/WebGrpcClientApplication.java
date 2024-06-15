// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.client;

import cn.srd.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcExceptionInterceptor;
import cn.srd.library.java.tool.spring.webmvc.autoconfigure.EnableWebMvcResponseBodyAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableWebMvcResponseBodyAdvice
@EnableWebMvcExceptionInterceptor
@SpringBootApplication
public class WebGrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGrpcClientApplication.class, args);
    }

}