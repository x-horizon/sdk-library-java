// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

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