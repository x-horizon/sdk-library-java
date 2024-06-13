// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.advice;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.contract.model.throwable.RunningException;
import cn.srd.library.java.contract.model.throwable.WarningException;
import io.grpc.Status;
import io.grpc.StatusException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.validation.BindException;

/**
 * @author wjm
 * @since 2024-06-13 23:24
 */
@Slf4j
@GrpcAdvice
public class GrpcExceptionInterceptor {

    @GrpcExceptionHandler(BindException.class)
    public StatusException handleBindException(BindException exception) {
        return Status.INVALID_ARGUMENT.withCause(exception).asException();
    }

    @GrpcExceptionHandler(ConstraintViolationException.class)
    public StatusException handleValidationException(ConstraintViolationException exception) {
        return Status.INVALID_ARGUMENT.withCause(exception).asException();
    }

    @GrpcExceptionHandler(DataNotFoundException.class)
    public StatusException handleDataNotFoundException(DataNotFoundException exception) {
        return Status.NOT_FOUND.withCause(exception).asException();
    }

    @GrpcExceptionHandler(WarningException.class)
    public StatusException handleWarnOperationException(WarningException exception) {
        return Status.NOT_FOUND.withCause(exception).asException();
    }

    @GrpcExceptionHandler(RunningException.class)
    public StatusException handleRunningException(RunningException exception) {
        return Status.INTERNAL.withCause(exception).asException();
    }

    @GrpcExceptionHandler(RuntimeException.class)
    public StatusException handleRuntimeException(RuntimeException exception) {
        return Status.INTERNAL.withCause(exception).asException();
    }

    @GrpcExceptionHandler(Exception.class)
    public StatusException handleException(Exception exception) {
        return Status.INTERNAL.withCause(exception).asException();
    }

    @GrpcExceptionHandler(Throwable.class)
    public StatusException handleThrowable(Throwable exception) {
        return Status.INTERNAL.withCause(exception).asException();
    }

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    private String formatMessage(String requestUri, String message) {
        return STR."\{ModuleView.WEB_GRPC_SYSTEM}请求资源地址：'\{requestUri}'，错误信息：\{message}";
    }

}