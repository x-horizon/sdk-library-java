package org.horizon.library.java.web.grpc.server.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable grpc server exception interceptor
 *
 * @author wjm
 * @see GrpcServerExceptionInterceptorRegistrar
 * @see GrpcServerAutoConfigurer#grpcServerExceptionInterceptor()
 * @since 2024-06-17 17:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(GrpcServerExceptionInterceptorRegistrar.class)
public @interface EnableGrpcServerExceptionInterceptor {

}