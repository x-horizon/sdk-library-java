package cn.library.java.tool.spring.contract.interceptor;

import cn.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.library.java.contract.constant.web.HttpStatus;
import cn.library.java.contract.model.protocol.WebResponse;
import cn.library.java.contract.model.throwable.ClientException;
import cn.library.java.contract.model.throwable.DataNotFoundException;
import cn.library.java.contract.model.throwable.InvalidArgumentException;
import cn.library.java.contract.model.throwable.RunningException;
import cn.library.java.tool.lang.object.Nil;
import cn.library.java.tool.lang.text.Strings;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;

import static cn.library.java.contract.model.protocol.WebResponse.error;

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

    protected WebResponse<Void> whenUnrecognizedPropertyException(String uri, UnrecognizedPropertyException exception) {
        String message = STR."操作失败：遇到错误的字段名“\{exception.getPropertyName()}”，正确的字段名可能为“\{Strings.joinWithCommaAndSpace(exception.getKnownPropertyIds())}”，请检查！";
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, message);
    }

    protected WebResponse<Void> whenInvalidIdException(String uri) {
        String message = "操作失败：未提供 id";
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, message);
    }

    protected WebResponse<Void> whenInvalidArgumentException(String uri, InvalidArgumentException exception) {
        log.warn(formatMessage(uri, exception.getMessage()));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, exception.getMessage());
    }

    protected WebResponse<Void> whenUnsupportedException(String uri) {
        String message = "操作失败：不支持该操作";
        log.warn(formatMessage(uri, message));
        return error(HttpStatus.NOT_IMPLEMENTED, message);
    }

    protected WebResponse<Void> whenDataNotFoundException(String uri, DataNotFoundException exception) {
        String message = Nil.isBlank(exception.getMessage()) ? "操作失败：数据不存在" : exception.getMessage();
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