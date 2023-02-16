package cn.srd.itcp.sugar.framework.spring.tool.webmvc.core.web;

import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.exceptions.RunningException;
import cn.srd.itcp.sugar.tool.exceptions.WarnOperationException;
import cn.srd.itcp.sugar.tool.web.HttpStatusEnum;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

import static cn.srd.itcp.sugar.tool.web.WebResponse.error;

/**
 * spring mvc 全局异常处理器
 *
 * @author wjm
 * @since 2020/6/13 20:05
 */
@Slf4j
@Order
@RestControllerAdvice
public class WebMVCExceptionHandler {

    /**
     * 格式错误的处理，如：接收数据的 JSON 格式错误；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public WebResponse<Void> handleHttpMessageNotReadableException(HttpServletRequest httpServletRequest, HttpMessageNotReadableException exception) {
        log.warn("请求资源地址：'{}'，错误信息：参数格式错误，相关信息：", httpServletRequest.getRequestURI(), exception);
        return WebResponse.error(HttpStatusEnum.BAD_REQUEST, "参数格式错误，请检查，相关信息：" + exception.getMessage());
    }

    /**
     * Validator：{@link org.springframework.validation.annotation.Validated Validated} 校验不通过时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(BindException.class)
    public WebResponse<Void> handleBindException(HttpServletRequest httpServletRequest, BindException exception) {
        String msg = StringsUtil.pretty(CollectionsUtil.toList(exception.getFieldErrors(), DefaultMessageSourceResolvable::getDefaultMessage));
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), msg);
        return error(HttpStatusEnum.BAD_REQUEST, msg);
    }

    /**
     * Validator：{@link org.springframework.validation.annotation.Validated Validated} + {@link org.springframework.web.bind.annotation.RequestBody RequestBody} 校验不通过时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public WebResponse<Void> handleValidationException(HttpServletRequest httpServletRequest, ConstraintViolationException exception) {
        String msg = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).distinct().collect(Collectors.joining("，"));
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), msg);
        return error(HttpStatusEnum.BAD_REQUEST, msg);
    }

    /**
     * SpringMVC 参数校验不正确的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<Void> handleMethodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException exception) {
        String msg = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).distinct().collect(Collectors.joining("，"));
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), msg);
        return error(HttpStatusEnum.BAD_REQUEST, msg);
    }

    /**
     * SpringMVC 请求参数缺失的处理，如：设置了 &#064;{@link org.springframework.web.bind.annotation.RequestParam RequestParam}("xx") 参数，结果未传递 xx 参数；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public WebResponse<Void> handleMissingServletRequestParameterException(HttpServletRequest httpServletRequest, MissingServletRequestParameterException exception) {
        String msg = String.format("【%s】参数缺失", exception.getParameterName());
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), msg);
        return error(HttpStatusEnum.BAD_REQUEST, msg);
    }

    /**
     * SpringMVC 请求参数类型错误的处理，如：设置了 &#064;{@link org.springframework.web.bind.annotation.RequestParam RequestParam}("xx") 参数类型为 Integer，结果传递 xx 参数的类型为 String；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public WebResponse<Void> handleMethodArgumentTypeMismatchException(HttpServletRequest httpServletRequest, MethodArgumentTypeMismatchException exception) {
        String msg = String.format("【%s】参数类型错误", exception.getName());
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), msg);
        return error(HttpStatusEnum.BAD_REQUEST, msg);
    }

    /**
     * SpringMVC 请求方法不正确时的处理，如：接口方法为 GET，请求时为 POST；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResponse<Void> handleHttpRequestMethodNotSupportedException(HttpServletRequest httpServletRequest, HttpRequestMethodNotSupportedException exception) {
        String msg = exception.getMessage();
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), msg);
        return error(HttpStatusEnum.METHOD_NOT_ALLOWED, msg);
    }

    /**
     * 出现 {@link WarnOperationException} 时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(WarnOperationException.class)
    public WebResponse<Void> handleWarnOperationException(HttpServletRequest httpServletRequest, WarnOperationException exception) {
        String msg = exception.getExceptionTemplate().getDescription();
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), msg);
        return error(exception.getExceptionTemplate().getCode(), msg);
    }

    /**
     * 出现 {@link RunningException} 时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(RunningException.class)
    public WebResponse<Void> handleBaseException(HttpServletRequest httpServletRequest, RunningException exception) {
        String msg = exception.getExceptionTemplate().getDescription();
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(exception.getExceptionTemplate().getCode(), msg);
    }

    /**
     * 出现 {@link RuntimeException} 时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    public WebResponse<Void> handleRuntimeException(HttpServletRequest httpServletRequest, RuntimeException exception) {
        log.error("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(HttpStatusEnum.INTERNAL_ERROR);
    }

    /**
     * 兜底处理所有未翻译异常；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(Exception.class)
    public WebResponse<Void> handleException(HttpServletRequest httpServletRequest, Exception exception) {
        log.error("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(HttpStatusEnum.INTERNAL_ERROR);
    }

    /**
     * 兜底处理所有未翻译异常，该处理主要是针对调用 RPC、二方包、或动态生成类的相关方法时，可能直接抛出的是 Error，而 catch Exception 无法捕获；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(Throwable.class)
    public WebResponse<Void> handleThrowable(HttpServletRequest httpServletRequest, Throwable exception) {
        log.error("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(HttpStatusEnum.INTERNAL_ERROR);
    }

}
