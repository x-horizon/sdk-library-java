// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.server.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;

/**
 * @author wjm
 * @since 2024-06-13 23:24
 */
@Slf4j
@GrpcAdvice
public class GrpcServerExceptionInterceptor {

    // @GrpcExceptionHandler(DataNotFoundException.class)
    // public Status handleDataNotFoundException(DataNotFoundException exception) {
    //     return Status.NOT_FOUND.withDescription(exception.getMessage()).withCause(exception);
    // }
    //
    // @GrpcExceptionHandler(WarningException.class)
    // public Status handleWarnOperationException(WarningException exception) {
    //     String message = exception.getMessage();
    //     // log.warn(formatMessage(httpServletRequest.getRequestURI(), message));
    //     return Status.NOT_FOUND.withDescription(exception.getMessage()).withCause(exception);
    //     return error(exception.getStatus(), message);
    // }
    //
    // @GrpcExceptionHandler(RunningException.class)
    // public Status handleRunningException(RunningException exception) {
    //     // log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
    //     return error(exception.getStatus(), exception.getMessage());
    // }
    //
    // @GrpcExceptionHandler(RuntimeException.class)
    // public Status handleRuntimeException(RuntimeException exception) {
    //     // log.error(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
    //     return Status.INTERNAL.withDescription(exception.getMessage()).withCause(exception);
    //     // return error(HttpStatus.INTERNAL_ERROR);
    // }
    //
    // @GrpcExceptionHandler(Exception.class)
    // public Status handleException(Exception exception) {
    //     // log.error(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
    //     return error(HttpStatus.INTERNAL_ERROR);
    // }
    //
    // @GrpcExceptionHandler(Throwable.class)
    // public Status handleThrowable(Throwable exception) {
    //     return Status.INTERNAL.withDescription(exception.getMessage()).withCause(exception);
    // }
    //
    // @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    // private String formatMessage(String requestUri, String message) {
    //     return STR."\{ModuleView.TOOL_SPRING_WEBMVC_SYSTEM}请求资源地址：'\{requestUri}'，错误信息：\{message}";
    // }

}