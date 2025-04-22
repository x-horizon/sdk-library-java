package org.horizon.sdk.library.java.tool.spring.contract.interceptor;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.contract.constant.prompt.PromptConstant;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.web.HttpStatus;
import org.horizon.sdk.library.java.contract.model.protocol.WebResponse;
import org.horizon.sdk.library.java.contract.model.throwable.*;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.lang.reflect.UndeclaredThrowableException;

import static org.horizon.sdk.library.java.contract.model.protocol.WebResponse.error;

/**
 * generic spring exception interceptor
 *
 * @author wjm
 * @since 2024-07-04 11:32
 */
@Slf4j
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
public abstract class WebExceptionInterceptor {

    protected abstract String getModuleView();

    protected WebResponse<Void> whenUndeclaredThrowableException(String uri, UndeclaredThrowableException exception) {
        return switch (exception.getCause()) {
            case UnrecognizedPropertyException unrecognizedPropertyException -> whenUnrecognizedPropertyException(uri, unrecognizedPropertyException);
            default -> whenThrowable(uri, exception);
        };
    }

    protected WebResponse<Void> whenUnrecognizedPropertyException(String uri, UnrecognizedPropertyException exception) {
        String message = STR."\{PromptConstant.OPERATION_FAILED}：发现错误的字段名[\{exception.getPropertyName()}]，正确的字段名可能为[\{Strings.getMostSimilar(exception.getPropertyName(), exception.getKnownPropertyIds().stream().map(Object::toString).toList())}]，当前所有字段名：[\{Strings.joinWithCommaAndSpace(exception.getKnownPropertyIds())}]，请检查！";
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, message);
    }

    protected WebResponse<Void> whenInvalidIdException(String uri) {
        String message = STR." \{PromptConstant.OPERATION_FAILED}：未提供 id";
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, message);
    }

    protected WebResponse<Void> whenInvalidArgumentException(String uri, InvalidArgumentException exception) {
        log.warn(formatMessage(uri, exception.getMessage()));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, exception.getMessage());
    }

    protected WebResponse<Void> whenUnauthenticatedException(String uri, UnauthenticatedException exception) {
        String message = Nil.isBlank(exception.getMessage()) ? STR."\{PromptConstant.OPERATION_FAILED}：未认证" : exception.getMessage();
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.UNAUTHENTICATED, exception.getMessage());
    }

    protected WebResponse<Void> whenUnauthorizedException(String uri, UnauthorizedException exception) {
        String message = Nil.isBlank(exception.getMessage()) ? STR."\{PromptConstant.OPERATION_FAILED}：未授权" : exception.getMessage();
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    protected WebResponse<Void> whenUnsupportedException(String uri) {
        String message = STR."\{PromptConstant.OPERATION_FAILED}：不支持该操作";
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.NOT_IMPLEMENTED, message);
    }

    protected WebResponse<Void> whenDataNotFoundException(String uri, DataNotFoundException exception) {
        String message = Nil.isBlank(exception.getMessage()) ? STR."\{PromptConstant.OPERATION_FAILED}：数据不存在" : exception.getMessage();
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.DATA_NOT_FOUND, message);
    }

    protected WebResponse<Void> whenClientException(String uri, ClientException exception) {
        log.warn(formatMessage(uri, exception.getMessage()), exception);
        return error(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    protected WebResponse<Void> whenRunningException(String uri, RunningException exception) {
        log.warn(formatMessage(uri, exception.getMessage()), exception);
        return error(exception.getStatus(), exception.getMessage());
    }

    protected WebResponse<Void> whenThrowable(String uri, Throwable exception) {
        log.error(formatMessage(uri, exception.getMessage()), exception);
        return error(HttpStatus.INTERNAL_ERROR, "服务繁忙，请稍后再试！");
    }

    protected String formatMessage(String requestUri, String message) {
        return STR."\{getModuleView()}请求资源地址：'\{requestUri}'，错误信息：\{message}";
    }

}