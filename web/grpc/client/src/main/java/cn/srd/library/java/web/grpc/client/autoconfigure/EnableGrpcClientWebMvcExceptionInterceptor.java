package cn.srd.library.java.web.grpc.client.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable grpc client spring webmvc exception interceptor
 *
 * @author wjm
 * @see GrpcClientWebMvcExceptionInterceptorRegistrar
 * @see GrpcClientAutoConfigurer#grpcClientWebMvcExceptionInterceptor()
 * @since 2024-06-17 17:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(GrpcClientWebMvcExceptionInterceptorRegistrar.class)
public @interface EnableGrpcClientWebMvcExceptionInterceptor {

}