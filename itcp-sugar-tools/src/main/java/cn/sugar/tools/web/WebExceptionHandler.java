// package cn.commons.tools.web;
//
// import cn.commons.tools.core.CollectionsUtil;
// import cn.commons.tools.exceptions.RunningException;
// import cn.commons.tools.exceptions.WarnOperationException;
// import cn.commons.tools.core.StringsUtil;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.context.support.DefaultMessageSourceResolvable;
// import org.springframework.http.converter.HttpMessageNotReadableException;
// import org.springframework.validation.BindException;
// import org.springframework.validation.FieldError;
// import org.springframework.web.HttpRequestMethodNotSupportedException;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.MissingServletRequestParameterException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
// import javax.security.sasl.AuthenticationException;
// import javax.validation.ConstraintViolation;
// import javax.validation.ConstraintViolationException;
// import java.util.stream.Collectors;
//
// import static cn.commons.tools.web.WebResponse.error;
//
// /**
//  * Web 全局异常处理器（示例代码，本项目用不到 spring-boot-starter-web，故注释，用作参考用）
//  *
//  * @author wjm
//  * @date 2020/6/13 20:05
//  */
// @Slf4j
// @RestControllerAdvice
// public class WebExceptionHandler {
//
//     /**
//      * 处理 SpringMVC 参数校验不正确
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public WebResponse<MethodArgumentNotValidException> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
//         String msg = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).distinct().collect(Collectors.joining("，"));
//         log.warn(msg);
//         return error(HttpStatusEnum.BAD_PARAMS, msg);
//     }
//
//     /**
//      * 处理 Validator 校验不通过产生的异常
//      * <pre>
//      *   适用于 body 校验，也就是标记了 {@link org.springframework.web.bind.annotation.ResponseBody} 注解的实体类校验
//      *
//      *   example：
//      *
//      *      {@code
//      *         @PostMapping(value = "/test")
//      *         public WebResponse<\Entity> test(@Validated @RequestBody Entity entity) {
//      *             return success();
//      *         }
//      *      }
//      * </pre>
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(ConstraintViolationException.class)
//     public WebResponse<ConstraintViolationException> handleValidationException(ConstraintViolationException exception) {
//         String msg = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).distinct().collect(Collectors.joining("，"));
//         log.warn(msg);
//         return error(HttpStatusEnum.BAD_PARAMS, msg);
//     }
//
//     /**
//      * 处理 Validator 校验不通过产生的异常
//      * <pre>
//      *   适用于 表单数据 校验
//      *
//      *   example：
//      *
//      *      {@code
//      *         @PostMapping(value = "/test")
//      *         public WebResponse<\Entity> test(@Validated Entity entity) {
//      *             return success();
//      *         }
//      *      }
//      * </pre>
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(BindException.class)
//     public WebResponse<BindException> handleBindException(BindException exception) {
//         String msg = StringsUtil.pretty(CollectionsUtil.collectList(exception.getFieldErrors(), DefaultMessageSourceResolvable::getDefaultMessage));
//         log.warn(msg);
//         return error(HttpStatusEnum.BAD_PARAMS, msg);
//     }
//
//     /**
//      * 处理 SpringMVC 请求参数缺失，例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(MissingServletRequestParameterException.class)
//     public WebResponse<MissingServletRequestParameterException> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
//         String msg = String.format("【%s】参数缺失", exception.getParameterName());
//         log.warn(msg);
//         return error(HttpStatusEnum.BAD_PARAMS, msg);
//     }
//
//     /**
//      * 处理 SpringMVC 请求参数类型错误，例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//     public WebResponse<MethodArgumentTypeMismatchException> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
//         String msg = String.format("【%s】参数类型错误", exception.getName());
//         log.warn(msg);
//         return error(HttpStatusEnum.BAD_PARAMS, msg);
//     }
//
//     /**
//      * 处理格式错误的异常，例如接收数据的JSON格式错误
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(HttpMessageNotReadableException.class)
//     public WebResponse<HttpMessageNotReadableException> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
//         log.warn("参数格式错误，请检查");
//         return error(HttpStatusEnum.BAD_PARAMS, "参数格式错误，请检查");
//     }
//
//     /**
//      * 处理 SpringMVC 请求方法不正确，例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//     public WebResponse<HttpRequestMethodNotSupportedException> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
//         String msg = exception.getMessage();
//         log.warn(msg);
//         return error(HttpStatusEnum.METHOD_NOT_ALLOWED, msg);
//     }
//
//     /**
//      * 处理 Spring Security 权限认证未通过异常
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(AuthenticationException.class)
//     public WebResponse<AuthenticationException> handleAuthenticationException(AuthenticationException exception) {
//         String msg = exception.getMessage();
//         log.warn(msg);
//         return error(HttpStatusEnum.FORBIDDEN);
//     }
//
//     /**
//      * WarnOperationException
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(WarnOperationException.class)
//     public WebResponse<WarnOperationException> handleWarnOperationException(WarnOperationException exception) {
//         String msg = exception.getExceptionTemplate().getDescription();
//         log.warn(msg);
//         return error(exception.getExceptionTemplate().getCode(), StringsUtil.subBefore(msg, WebResponse.MARKED_STACK_TRACE_MSG, true));
//     }
//
//     /**
//      * RunningException
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(RunningException.class)
//     public WebResponse<RunningException> handleBaseException(RunningException exception) {
//         String msg = exception.getExceptionTemplate().getDescription();
//         log.warn(msg, exception);
//         return error(exception.getExceptionTemplate().getCode(), msg);
//     }
//
//     /**
//      * RuntimeException
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(RuntimeException.class)
//     public WebResponse<RuntimeException> handleRuntimeException(RuntimeException exception) {
//         String msg = exception.getMessage();
//         log.warn(msg, exception);
//         return error(HttpStatusEnum.INTERNAL_SERVER_ERROR);
//     }
//
//     /**
//      * 兜底处理所有未翻译异常
//      *
//      * @param exception
//      */
//     @ExceptionHandler(Exception.class)
//     public WebResponse<Exception> handleException(Exception exception) {
//         log.error("系统异常, errMsg: ", exception);
//         return error(HttpStatusEnum.INTERNAL_SERVER_ERROR);
//     }
//
//     /**
//      * 兜底处理所有未翻译异常，该处理主要是针对调用 RPC、二方包、或动态生成类的相关方法时，可能直接抛出的是 Error，而 catch Exception 无法捕获
//      *
//      * @param exception
//      */
//     @ExceptionHandler(Throwable.class)
//     public WebResponse<Throwable> handleThrowable(Throwable exception) {
//         log.error("系统异常, errMsg: ", exception);
//         return error(HttpStatusEnum.INTERNAL_SERVER_ERROR);
//     }
//
// }
