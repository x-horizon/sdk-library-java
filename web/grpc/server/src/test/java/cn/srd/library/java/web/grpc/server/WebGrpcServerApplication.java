// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.server;

import cn.srd.library.java.web.grpc.server.autoconfigure.EnableGrpcServerExceptionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableGrpcServerExceptionInterceptor
@SpringBootApplication
public class WebGrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGrpcServerApplication.class, args);
    }

}