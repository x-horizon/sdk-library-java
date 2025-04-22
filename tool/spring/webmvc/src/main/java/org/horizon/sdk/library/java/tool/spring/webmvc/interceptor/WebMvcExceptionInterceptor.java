package org.horizon.sdk.library.java.tool.spring.webmvc.interceptor;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.web.HttpStatus;
import org.horizon.sdk.library.java.contract.model.protocol.WebResponse;
import org.horizon.sdk.library.java.contract.model.throwable.*;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.object.Classes;
import org.horizon.sdk.library.java.tool.spring.contract.interceptor.WebExceptionInterceptor;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.stream.Collectors;

import static org.horizon.sdk.library.java.contract.model.protocol.WebResponse.error;

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
public class WebMvcExceptionInterceptor extends WebExceptionInterceptor {

    @Override
    protected String getModuleView() {
        return ModuleView.TOOL_SPRING_WEBMVC_SYSTEM;
    }

    /**
     * <p>handle the exception sample as following:</p>
     *
     * <ol>
     *   <li>
     *     define a controller:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *   <li>
     *     send a POST request to {@code /foo/sayHello2}, will throw {@link NoResourceFoundException}
     *     and handled by this method
     *   </li>
     * </ol>
     *
     * @param httpServletRequest the HTTP servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public WebResponse<Void> handleNoResourceFoundException(HttpServletRequest httpServletRequest, NoResourceFoundException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.NOT_FOUND, STR."the resource path [\{exception.getResourcePath()}] not found");
    }

    /**
     * <p>handle the exception sample as following:</p>
     *
     * <ol>
     *   <li>
     *     define a view object:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     define a controller:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     send a PUT request to {@code /foo/sayHello}, will throw
     *     {@link HttpRequestMethodNotSupportedException} and handled by this method
     *   </li>
     * </ol>
     *
     * @param httpServletRequest the HTTP servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResponse<Void> handleHttpRequestMethodNotSupportedException(HttpServletRequest httpServletRequest, HttpRequestMethodNotSupportedException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.BAD_METHOD, STR."supported request methods are \{Converts.toArrayList(exception.getSupportedMethods())}, but current request method is [\{exception.getMethod()}]");
    }

    /**
     * <p>handle the exception sample as following:</p>
     *
     * <ol>
     *   <li>
     *     define a view object:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     define a controller:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     send a POST request to {@code /foo/sayHello} with null data (cannot deserialize to FooVO):
     *     <pre>{@code
     *     // Request body: null
     *     }</pre>
     *     will throw {@link HttpMessageNotReadableException} and handled by this method
     *   </li>
     *
     *   <li>
     *     send a POST request to {@code /foo/sayHello} with type mismatch data:
     *     <pre>{@code
     *     {
     *       "id": "invalid string id",
     *       "name": "valid name"
     *     }
     *     }</pre>
     *     will throw {@link HttpMessageNotReadableException} when deserializing to long id
     *   </li>
     * </ol>
     *
     * @param httpServletRequest the HTTP servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public WebResponse<Void> handleHttpMessageNotReadableException(HttpServletRequest httpServletRequest, HttpMessageNotReadableException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.MESSAGE_NOT_READABLE, "http message not readable");
    }

    /**
     * <p>handle the exception sample as following:</p>
     *
     * <ol>
     *   <li>
     *     define a view object:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     define a controller:
     *     <pre>{@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(
     *             @RequestParam(required = true) Long id,
     *             @RequestParam(required = true) String name
     *         ) {
     *             System.out.println(id + name);
     *         }
     *
     *     }
     *     }</pre>
     *   </li>
     * </ol>
     *
     * <p>test cases:</p>
     * <ul>
     *   <li>
     *     sample 1: send POST to {@code /foo/sayHello} (missing both parameters)<br>
     *     → throws {@link MissingServletRequestParameterException} (parameter [id] missing)
     *   </li>
     *   <li>
     *     sample 2: send POST to {@code /foo/sayHello?name=""} <br>
     *     → throws {@link MissingServletRequestParameterException} (parameter [id] missing)
     *   </li>
     *   <li>
     *     sample 3: send POST to {@code /foo/sayHello?id=1} <br>
     *     → throws {@link MissingServletRequestParameterException} (parameter [name] missing)
     *   </li>
     *   <li>
     *     sample 4: send POST to {@code /foo/sayHello?id=1&name=""} <br>
     *     → request success
     *   </li>
     * </ul>
     *
     * @param httpServletRequest the HTTP servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public WebResponse<Void> handleMissingServletRequestParameterException(HttpServletRequest httpServletRequest, MissingServletRequestParameterException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.MISSING_REQUEST_PARAMETER, STR."the parameter [\{exception.getParameterName()}] with type [\{exception.getParameterType()}] is missing");
    }

    /**
     * <p>handle the exception sample as following:</p>
     *
     * <ol>
     *   <li>
     *     define a view object:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     define a controller with required parameters:
     *     <pre>{@code
     *     @RestController
     *     @RequestMapping("/foo")
     *     public class FooController {
     *
     *         @PostMapping("/sayHello")
     *         public void sayHello(
     *             @RequestParam(required = true) Long id,
     *             @RequestParam(required = true) String name
     *         ) {
     *             System.out.println(id + name);
     *         }
     *
     *     }
     *     }</pre>
     *   </li>
     * </ol>
     *
     * <p>type conversion test cases:</p>
     * <ul>
     *   <li>
     *     sample 1: POST {@code /foo/sayHello?id=null&name=null}<br>
     *     → parameter converts to {@code id="null"} (String → Long conversion failed) → throws {@link MethodArgumentTypeMismatchException}
     *   </li>
     *   <li>
     *     sample 2: POST {@code /foo/sayHello?id=null&name=""}<br>
     *     → parameter converts to {@code id="null"} (String → Long conversion failed) → throws {@link MethodArgumentTypeMismatchException}
     *   </li>
     *   <li>
     *     sample 3: POST {@code /foo/sayHello?id=1&name=null}<br>
     *     → {@code name="null"} is acceptable for String type → request success
     *   </li>
     *   <li>
     *     sample 4: POST {@code /foo/sayHello?id=1&name=""}<br>
     *     → empty string is valid for String parameter → request success
     *   </li>
     * </ul>
     *
     * @param httpServletRequest the HTTP servlet request
     * @param exception          the type conversion exception
     * @return the web response
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public WebResponse<Void> handleMethodArgumentTypeMismatchException(HttpServletRequest httpServletRequest, MethodArgumentTypeMismatchException exception) {
        log.warn(formatMessage(httpServletRequest.getRequestURI(), exception.getMessage()));
        return error(HttpStatus.WRONG_REQUEST_PARAMETER_TYPE, STR."failed to convert [\{exception.getValue()}] to parameter [\{exception.getName()}] with type [\{Classes.getClassSimpleName(exception.getRequiredType())}]");
    }

    /**
     * <p>handle the exception sample as following:</p>
     *
     * <ol>
     *   <li>
     *     define a view object with validation annotations:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     define a controller with validation:
     *     <pre>{@code
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
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     send a POST request with invalid data:
     *     <pre>{@code
     *     {
     *       "id": null,
     *       "name": "  "
     *     }
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     throws {@link MethodArgumentNotValidException} when validation fails
     *   </li>
     * </ol>
     *
     * @param httpServletRequest the HTTP servlet request
     * @param exception          the validation exception
     * @return the web response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<Void> handleMethodArgumentNotValidException(HttpServletRequest httpServletRequest, MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).distinct().collect(Collectors.joining(", "));
        log.warn(formatMessage(httpServletRequest.getRequestURI(), message));
        return error(HttpStatus.WRONG_REQUEST_MESSAGE_VALUE, message);
    }

    /**
     * handle the exception when throw {@link UndeclaredThrowableException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    public WebResponse<Void> whenUndeclaredThrowableException(HttpServletRequest httpServletRequest, UndeclaredThrowableException exception) {
        return whenUndeclaredThrowableException(httpServletRequest.getRequestURI(), exception);
    }

    /**
     * handle the exception when throw {@link UnrecognizedPropertyException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(UnrecognizedPropertyException.class)
    public WebResponse<Void> handleUnrecognizedPropertyException(HttpServletRequest httpServletRequest, UnrecognizedPropertyException exception) {
        return whenUnrecognizedPropertyException(httpServletRequest.getRequestURI(), exception);
    }

    /**
     * handle the exception when throw {@link InvalidIdException}
     *
     * @param httpServletRequest the http servlet request
     * @return the web response
     */
    @ExceptionHandler(InvalidIdException.class)
    public WebResponse<Void> handleInvalidIdException(HttpServletRequest httpServletRequest, InvalidIdException ignore) {
        return whenInvalidIdException(httpServletRequest.getRequestURI());
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
        return whenInvalidArgumentException(httpServletRequest.getRequestURI(), exception);
    }

    /**
     * handle the exception when throw {@link UnauthenticatedException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public WebResponse<Void> handleUnauthenticatedException(HttpServletRequest httpServletRequest, UnauthenticatedException exception) {
        return whenUnauthenticatedException(httpServletRequest.getRequestURI(), exception);
    }

    /**
     * handle the exception when throw {@link UnauthenticatedException}
     *
     * @param httpServletRequest the http servlet request
     * @param exception          the exception
     * @return the web response
     */
    @ExceptionHandler(UnauthorizedException.class)
    public WebResponse<Void> handleUnauthorizedException(HttpServletRequest httpServletRequest, UnauthorizedException exception) {
        return whenUnauthorizedException(httpServletRequest.getRequestURI(), exception);
    }

    /**
     * handle the exception when throw {@link UnsupportedException}
     *
     * @param httpServletRequest the http servlet request
     * @return the web response
     */
    @ExceptionHandler(UnsupportedException.class)
    public WebResponse<Void> handleUnsupportedException(HttpServletRequest httpServletRequest, UnsupportedException ignore) {
        return whenUnsupportedException(httpServletRequest.getRequestURI());
    }

    /**
     * handle the exception when throw {@link DataNotFoundException}
     *
     * @param httpServletRequest the http servlet request
     * @return the web response
     */
    @ExceptionHandler(DataNotFoundException.class)
    public WebResponse<Void> handleDataNotFoundException(HttpServletRequest httpServletRequest, DataNotFoundException exception) {
        return whenDataNotFoundException(httpServletRequest.getRequestURI(), exception);
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
        return whenClientException(httpServletRequest.getRequestURI(), exception);
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
        return whenRunningException(httpServletRequest.getRequestURI(), exception);
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
        return whenThrowable(httpServletRequest.getRequestURI(), exception);
    }

}