// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.client.interceptor;

import cn.srd.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.srd.library.java.contract.constant.web.HttpStatus;
import cn.srd.library.java.contract.model.protocol.WebResponse;
import io.grpc.StatusRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.srd.library.java.contract.model.protocol.WebResponse.error;

/**
 * @author wjm
 * @since 2024-06-17 17:46
 */
@Slf4j
@Order(SpringInitializeConstant.LOWER_INITIALIZE_PRIORITY)
@RestControllerAdvice
public class GrpcWebMvcExceptionInterceptor {

    @ExceptionHandler(StatusRuntimeException.class)
    public WebResponse<Void> handleStatusRuntimeException(HttpServletRequest httpServletRequest, StatusRuntimeException exception) {
        // log.error(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return error(HttpStatus.INTERNAL_ERROR);
    }

}