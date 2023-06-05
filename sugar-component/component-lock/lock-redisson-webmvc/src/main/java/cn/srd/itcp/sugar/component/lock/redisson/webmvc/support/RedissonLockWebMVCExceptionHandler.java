package cn.srd.itcp.sugar.component.lock.redisson.webmvc.support;

import cn.srd.itcp.sugar.component.lock.redisson.common.exception.RedissonGenerateLockNameFailedException;
import cn.srd.itcp.sugar.component.lock.redisson.common.exception.RedissonLockIllegalArgumentException;
import cn.srd.itcp.sugar.tool.constant.HttpInfo;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.srd.itcp.sugar.tool.web.WebResponse.error;

/**
 * spring web mvc redisson global exception handler
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class RedissonLockWebMVCExceptionHandler {

    /**
     * handle {@link RedissonGenerateLockNameFailedException}
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(RedissonGenerateLockNameFailedException.class)
    public WebResponse<Void> handleRedissonExecuteException(HttpServletRequest httpServletRequest, RedissonGenerateLockNameFailedException exception) {
        String message = exception.getCause().getMessage();
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(HttpInfo.HTTP_INTERNAL_ERROR, message);
    }

    /**
     * handle {@link RedissonLockIllegalArgumentException}
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(RedissonLockIllegalArgumentException.class)
    public WebResponse<Void> handleRedissonExecuteException(HttpServletRequest httpServletRequest, RedissonLockIllegalArgumentException exception) {
        String message = exception.getCause().getMessage();
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(HttpInfo.HTTP_INTERNAL_ERROR, message);
    }

}
