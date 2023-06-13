package cn.srd.itcp.sugar.component.lock.redis.webmvc.support;

import cn.srd.itcp.sugar.component.lock.redis.common.exception.RedisGenerateLockNameFailedException;
import cn.srd.itcp.sugar.component.lock.redis.common.exception.RedisLockIllegalArgumentException;
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
 * spring web mvc redis global exception handler
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@RestControllerAdvice
public class RedisLockWebMVCExceptionHandler {

    /**
     * handle {@link RedisGenerateLockNameFailedException}
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(RedisGenerateLockNameFailedException.class)
    public WebResponse<Void> handleRedisExecuteException(HttpServletRequest httpServletRequest, RedisGenerateLockNameFailedException exception) {
        String message = exception.getCause().getMessage();
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(HttpInfo.HTTP_INTERNAL_ERROR, message);
    }

    /**
     * handle {@link RedisLockIllegalArgumentException}
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(RedisLockIllegalArgumentException.class)
    public WebResponse<Void> handleRedisExecuteException(HttpServletRequest httpServletRequest, RedisLockIllegalArgumentException exception) {
        String message = exception.getCause().getMessage();
        log.warn("请求资源地址：'{}'，错误信息：'{}'", httpServletRequest.getRequestURI(), exception);
        return error(HttpInfo.HTTP_INTERNAL_ERROR, message);
    }

}
