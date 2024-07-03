// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.webmvc.interceptor;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.web.HttpStatus;
import cn.srd.library.java.contract.model.protocol.WebResponse;
import cn.srd.library.java.contract.model.throwable.*;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.object.Classes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

import static cn.srd.library.java.contract.model.protocol.WebResponse.error;

/**
 * spring webmvc exception interceptor
 *
 * @author wjm
 * @since 2020-06-13 20:05
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@Slf4j
@Order
@RestControllerAdvice
public class WebMvcExceptionInterceptor {

    /**
     * <pre>
     * handle the exception sample as following:
     *
     *  1. define a controller.
     *  {@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(@RequestBody FooVO fooVO) {
     *             System.out.println(fooVO);
     *         }
     *
     *     }
     *  }
     *
     *  2. send a post request to /foo/sayHello2, will throw {@link NoResourceFoundException} and handled by this method.
     * </pre>
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public WebResponse<Void> handleNoResourceFoundException(HttpServletRequest httpServletRequest, NoResourceFoundException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.NOT_FOUND, STR."the resource path [\{exception.getResourcePath()}] not found");
    }

    /**
     * <pre>
     * handle the exception sample as following:
     *
     *  1. define a view object.
     *  {@code
     *     @Data
     *     @NoArgsConstructor
     *     @Accessors(chain = true)
     *     @SuperBuilder(toBuilder = true)
     *     public class FooVO implements Serializable {
     *
     *         @Serial private static final long serialVersionUID = -7576755740450265152L;
     *
     *         private Long id;
     *
     *         private String name;
     *
     *     }
     *  }
     *
     *  2. define a controller.
     *  {@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @RequestMapping(path = "/sayHello", method = {RequestMethod.GET, RequestMethod.POST})
     *         public void sayHello(@RequestBody FooVO fooVO) {
     *             System.out.println(fooVO);
     *         }
     *
     *     }
     *  }
     *
     *  3. send a put request to /foo/sayHello, will throw {@link HttpRequestMethodNotSupportedException} and handled by this method.
     * </pre>
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResponse<Void> handleHttpRequestMethodNotSupportedException(HttpServletRequest httpServletRequest, HttpRequestMethodNotSupportedException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.BAD_METHOD, STR."supported request methods are \{Converts.toList(exception.getSupportedMethods())}, but current request method is [\{exception.getMethod()}]");
    }

    /**
     * <pre>
     * handle the exception sample as following:
     *
     *  1. define a view object.
     *  {@code
     *     @Data
     *     @NoArgsConstructor
     *     @Accessors(chain = true)
     *     @SuperBuilder(toBuilder = true)
     *     public class FooVO implements Serializable {
     *
     *         @Serial private static final long serialVersionUID = -7576755740450265152L;
     *
     *         private Long id;
     *
     *         private String name;
     *
     *     }
     *  }
     *
     *  2. define a controller.
     *  {@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(@RequestBody FooVO fooVO) {
     *             System.out.println(fooVO);
     *         }
     *
     *     }
     *  }
     *  </pre>
     *
     * <p>3. send a post request to /foo/sayHello with null data, this means that the data cannot be deserialized to FooVO, will throw {@link HttpMessageNotReadableException} and handled by this method.
     * <p>4. send a post request to /foo/sayHello with data like:
     * <p>{@code
     * {
     * "id": "a wrong type id",
     * "name": "normal name"
     * }
     * }</p>
     * <p>this means that the id string value cannot be deserialized to FooVO long id, will throw {@link HttpMessageNotReadableException} and handled by this method.
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public WebResponse<Void> handleHttpMessageNotReadableException(HttpServletRequest httpServletRequest, HttpMessageNotReadableException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.MESSAGE_NOT_READABLE, "http message not readable");
    }

    /**
     * <pre>
     * handle the exception sample as following:
     *
     *  1. define a view object.
     *  {@code
     *     @Data
     *     @NoArgsConstructor
     *     @Accessors(chain = true)
     *     @SuperBuilder(toBuilder = true)
     *     public class FooVO implements Serializable {
     *
     *         @Serial private static final long serialVersionUID = -7576755740450265152L;
     *
     *         private Long id;
     *
     *         private String name;
     *
     *     }
     *  }
     *
     *  2. define a controller.
     *  {@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(@RequestParam(required = true) Long id, @RequestParam(required = true) String name) {
     *             System.out.println(id + name);
     *         }
     *
     *     }
     *  }
     * </pre>
     *
     * <p>sample 1: send a post request to /foo/sayHello,              will throw {@link MissingServletRequestParameterException} and handled by this method because of the parameter [id] is missing.
     * <p>sample 2: send a post request to /foo/sayHello?name="",      will throw {@link MissingServletRequestParameterException} and handled by this method because of the parameter [id] is missing.
     * <p>sample 3: send a post request to /foo/sayHello?id=1,         will throw {@link MissingServletRequestParameterException} and handled by this method because of the parameter [name] is missing.
     * <p>sample 4: send a post request to /foo/sayHello?id=1&name="", request success.
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public WebResponse<Void> handleMissingServletRequestParameterException(HttpServletRequest httpServletRequest, MissingServletRequestParameterException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.MISSING_REQUEST_PARAMETER, STR."the parameter [\{exception.getParameterName()}] with type [\{exception.getParameterType()}] is missing");
    }

    /**
     * <pre>
     * handle the exception sample as following:
     *
     *  1. define a view object.
     *  {@code
     *     @Data
     *     @NoArgsConstructor
     *     @Accessors(chain = true)
     *     @SuperBuilder(toBuilder = true)
     *     public class FooVO implements Serializable {
     *
     *         @Serial private static final long serialVersionUID = -7576755740450265152L;
     *
     *         private Long id;
     *
     *         private String name;
     *
     *     }
     *  }
     *
     *  2. define a controller.
     *  {@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(@RequestParam(required = true) Long id, @RequestParam(required = true) String name) {
     *             System.out.println(id + name);
     *         }
     *
     *     }
     *  }
     * </pre>
     *
     * <p>sample 1: send a post request to /foo/sayHello?id=null&name=null, the null value will be convert to "null", will throw {@link MethodArgumentTypeMismatchException} and handled by this method because of the parameter [id] type is wrong.
     * <p>sample 2: send a post request to /foo/sayHello?id=null&name="",   the null value will be convert to "null", will throw {@link MethodArgumentTypeMismatchException} and handled by this method because of the parameter [id] type is wrong.
     * <p>sample 3: send a post request to /foo/sayHello?id=1&name=null,    the null value will be convert to "null", request success.
     * <p>sample 4: send a post request to /foo/sayHello?id=1&name="",      request success.
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public WebResponse<Void> handleMethodArgumentTypeMismatchException(HttpServletRequest httpServletRequest, MethodArgumentTypeMismatchException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.WRONG_REQUEST_PARAMETER_TYPE, STR."failed to convert [\{exception.getValue()}] to parameter [\{exception.getName()}] with type [\{Classes.getClassSimpleName(exception.getRequiredType())}]");
    }

    /**
     * <pre>
     * handle the exception sample as following:
     *
     *  1. define a view object and marked the validation annotation like @{@link NotNull} on the specified field.
     *  {@code
     *     @Data
     *     @NoArgsConstructor
     *     @Accessors(chain = true)
     *     @SuperBuilder(toBuilder = true)
     *     public class FooVO implements Serializable {
     *
     *         @Serial private static final long serialVersionUID = -7576755740450265152L;
     *
     *         @NotNull(message = "the field [id] value is not allow to be null")
     *         private Long id;
     *
     *         @NotBlank(message = "the field [name] value is not allow to be blank")
     *         private String name;
     *
     *     }
     *  }
     *
     *  2. define a controller and marked @{@link Validated} on the view object.
     *  {@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(@Validated @RequestBody FooVO fooVO) {
     *             System.out.println(fooVO);
     *         }
     *
     *     }
     *  }
     *
     *  3. send a post request to /foo/sayHello with data like:
     *  {@code
     *     {
     *         "id": null,
     *         "name": "  "
     *     }
     *  }
     *
     *  4. will throw {@link MethodArgumentNotValidException} and handled by this method.
     * </pre>
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<Void> handleMethodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).distinct().collect(Collectors.joining(", "));
        log.warn(formatMessage(httpServletRequest.getRequestURI(), message));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, message);
    }

    /**
     * handle the exception when throw {@link InvalidIdException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(InvalidIdException.class)
    public WebResponse<Void> handleInvalidIdException(HttpServletRequest httpServletRequest, InvalidIdException exception) {
        String message = "操作失败：未提供 id";
        log.warn(formatMessage(httpServletRequest.getRequestURI(), message), exception);
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, message);
    }

    /**
     * handle the exception when throw {@link InvalidArgumentException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(InvalidArgumentException.class)
    public WebResponse<Void> handleInvalidArgumentException(HttpServletRequest httpServletRequest, InvalidArgumentException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception.getMessage());
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, exception.getMessage());
    }

    /**
     * handle the exception when throw {@link UnsupportedException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(UnsupportedException.class)
    public WebResponse<Void> handleUnsupportedException(HttpServletRequest httpServletRequest, UnsupportedException exception) {
        String message = "操作失败：不支持该操作";
        log.warn(formatMessage(httpServletRequest.getRequestURI(), message), exception);
        return error(HttpStatus.NOT_IMPLEMENTED, message);
    }

    /**
     * handle the exception when throw {@link DataNotFoundException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(DataNotFoundException.class)
    public WebResponse<Void> handleDataNotFoundException(HttpServletRequest httpServletRequest, DataNotFoundException exception) {
        String message = "操作失败：数据不存在";
        log.warn(formatMessage(httpServletRequest.getRequestURI(), message), exception);
        return error(HttpStatus.DATA_NOT_FOUND, message);
    }

    /**
     * handle the exception when throw {@link ClientException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(ClientException.class)
    public WebResponse<Void> handleClientException(HttpServletRequest httpServletRequest, ClientException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return error(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * handle the exception when throw {@link RunningException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(RunningException.class)
    public WebResponse<Void> handleRunningException(HttpServletRequest httpServletRequest, RunningException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return error(exception.getStatus(), exception.getMessage());
    }

    /**
     * all exceptions handled when no matching exception by this class other methods
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(Throwable.class)
    public WebResponse<Void> handleThrowable(HttpServletRequest httpServletRequest, Throwable exception) {
        log.error(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()), exception);
        return error(HttpStatus.INTERNAL_ERROR);
    }

    private String formatMessage(String requestUri, String message) {
        return STR."\{ModuleView.TOOL_SPRING_WEBMVC_SYSTEM}请求资源地址：'\{requestUri}'，错误信息：\{message}";
    }

}