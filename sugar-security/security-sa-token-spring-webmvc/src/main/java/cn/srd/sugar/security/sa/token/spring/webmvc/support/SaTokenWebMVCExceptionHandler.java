package cn.srd.sugar.security.sa.token.spring.webmvc.support;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.srd.sugar.tool.lang.web.HttpStatusEnum;
import cn.srd.sugar.tool.lang.web.WebResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.srd.sugar.tool.lang.web.WebResponse.error;

/**
 * spring web mvc sa-token global exception handler
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 2)
@RestControllerAdvice
public class SaTokenWebMVCExceptionHandler {

    /**
     * 未能通过登录认证时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(NotLoginException.class)
    public WebResponse<Void> handleNotLoginException(HttpServletRequest httpServletRequest, NotLoginException exception) {
        log.warn("请求资源地址：'{}'，错误信息：未能通过登录认证", httpServletRequest.getRequestURI());
        return error(HttpStatusEnum.NOT_LOGIN, "未能通过登录认证");
    }

    /**
     * 未能通过权限认证时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(NotPermissionException.class)
    public WebResponse<Void> handleNotPermissionException(HttpServletRequest httpServletRequest, NotPermissionException exception) {
        log.warn("请求资源地址：'{}'，错误信息：未能通过权限认证", httpServletRequest.getRequestURI());
        return error(HttpStatusEnum.NOT_PERMISSION, "未能通过权限认证");
    }

    /**
     * 未能通过角色认证时的处理；
     *
     * @param httpServletRequest Servlet 上下文信息
     * @param exception          抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(NotRoleException.class)
    public WebResponse<Void> handleNotRoleException(HttpServletRequest httpServletRequest, NotRoleException exception) {
        log.warn("请求资源地址：'{}'，错误信息：未能通过角色认证", httpServletRequest.getRequestURI());
        return error(HttpStatusEnum.NOT_ROLE, "未能通过角色认证");
    }

}
