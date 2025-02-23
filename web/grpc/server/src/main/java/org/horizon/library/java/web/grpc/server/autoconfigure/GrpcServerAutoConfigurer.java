package org.horizon.library.java.web.grpc.server.autoconfigure;

import org.horizon.library.java.web.grpc.server.interceptor.GrpcServerExceptionInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Web Grpc Server
 *
 * @author wjm
 * @since 2024-06-14 16:28
 */
@AutoConfiguration
public class GrpcServerAutoConfigurer {

    @Bean
    @ConditionalOnBean(GrpcServerExceptionInterceptorRegistrar.class)
    public GrpcServerExceptionInterceptor grpcServerExceptionInterceptor() {
        return new GrpcServerExceptionInterceptor();
    }

}