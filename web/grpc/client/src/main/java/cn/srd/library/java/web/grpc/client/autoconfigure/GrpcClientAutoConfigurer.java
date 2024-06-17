// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.client.autoconfigure;

import cn.srd.library.java.web.grpc.client.interceptor.GrpcClientResponseInterceptor;
import cn.srd.library.java.web.grpc.client.interceptor.GrpcWebMvcExceptionInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Web Grpc Client
 *
 * @author wjm
 * @since 2024-06-14 16:28
 */
@AutoConfiguration
@Configuration
public class GrpcClientAutoConfigurer {

    @GrpcGlobalClientInterceptor
    public GrpcClientResponseInterceptor grpcClientResponseInterceptor() {
        return new GrpcClientResponseInterceptor();
    }

    @Bean
    public GrpcWebMvcExceptionInterceptor grpcWebMvcExceptionInterceptor() {
        return new GrpcWebMvcExceptionInterceptor();
    }

}