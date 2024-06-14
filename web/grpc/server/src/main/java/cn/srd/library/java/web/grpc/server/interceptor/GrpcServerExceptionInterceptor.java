// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.server.interceptor;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.web.HttpStatus;
import cn.srd.library.java.contract.model.protocol.WebResponse;
import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.contract.model.throwable.RunningException;
import cn.srd.library.java.contract.model.throwable.WarningException;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.text.Strings;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;

import static cn.srd.library.java.contract.model.protocol.WebResponse.error;

/**
 * @author wjm
 * @since 2024-06-13 23:24
 */
@Slf4j
@GrpcAdvice
public class GrpcServerExceptionInterceptor {

    @GrpcExceptionHandler(BindException.class)
    public WebResponse<Void> handleBindException(BindException exception) {
        String message = Strings.joinWithComma(Converts.toList(exception.getFieldErrors(), DefaultMessageSourceResolvable::getDefaultMessage));
        // log.warn(formatMessage(httpServletRequest.getRequestURI(), message));
        return error(HttpStatus.BAD_REQUEST, message);
    }

    @GrpcExceptionHandler(DataNotFoundException.class)
    public WebResponse<Void> handleDataNotFoundException(DataNotFoundException exception) {
        String message = "操作失败，数据不存在";
        // log.warn(formatMessage(httpServletRequest.getRequestURI(), message));
        return error(exception.getStatus(), message);
    }

    @GrpcExceptionHandler(WarningException.class)
    public WebResponse<Void> handleWarnOperationException(WarningException exception) {
        String message = exception.getMessage();
        // log.warn(formatMessage(httpServletRequest.getRequestURI(), message));
        return error(exception.getStatus(), message);
    }

    @GrpcExceptionHandler(RunningException.class)
    public WebResponse<Void> handleRunningException(RunningException exception) {
        // log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return error(exception.getStatus(), exception.getMessage());
    }

    @GrpcExceptionHandler(RuntimeException.class)
    public Status handleRuntimeException(RuntimeException exception) {
        // log.error(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return Status.INVALID_ARGUMENT.withDescription("exception.getMessage()").withCause(exception);
        // return error(HttpStatus.INTERNAL_ERROR);
    }

    @GrpcExceptionHandler(Exception.class)
    public WebResponse<Void> handleException(Exception exception) {
        // log.error(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return error(HttpStatus.INTERNAL_ERROR);
    }

    @GrpcExceptionHandler(Throwable.class)
    public WebResponse<Void> handleThrowable(Throwable exception) {
        // log.error(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return error(HttpStatus.INTERNAL_ERROR);
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    private String formatMessage(String requestUri, String message) {
        return STR."\{ModuleView.TOOL_SPRING_WEBMVC_SYSTEM}请求资源地址：'\{requestUri}'，错误信息：\{message}";
    }

}