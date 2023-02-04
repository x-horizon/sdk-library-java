package cn.srd.itcp.sugar.security.sa.token.reactor.support;

import cn.dev33.satoken.exception.NotLoginException;
import cn.srd.itcp.sugar.spring.tool.webflux.core.SpringsWebFluxUtil;
import cn.srd.itcp.sugar.tool.web.HttpStatusEnum;
import cn.srd.itcp.sugar.tool.web.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static cn.srd.itcp.sugar.tool.web.WebResponse.error;

/**
 * spring web reactor sa-token 全局异常处理器
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@Slf4j
@RestControllerAdvice
public class SaTokenWebReactorExceptionHandler {

    /**
     * 未能通过登录认证时的处理；
     *
     * @param exception 抛出的异常
     * @return 响应结果
     */
    @ExceptionHandler(NotLoginException.class)
    public WebResponse<Void> handleNotLoginException(NotLoginException exception) {
        log.warn("请求资源地址：'{}'，错误信息：未能通过登录认证", (Object) SpringsWebFluxUtil.getServerHttpRequest().map(ServerHttpRequest::getURI).as(Objects::toString));
        return error(HttpStatusEnum.NOT_LOGIN, "未能通过登录认证");
    }

    // /**
    //  * 未能通过权限认证时的处理；
    //  *
    //  * @param exception         抛出的异常
    //  * @return 响应结果
    //  */
    // @ExceptionHandler(NotPermissionException.class)
    // public WebResponse<Void> handleNotPermissionException(NotPermissionException exception) {
    //     log.warn("请求资源地址：'{}'，错误信息：未能通过权限认证", serverHttpRequest.getRequestURI());
    //     return error(HttpStatusEnum.NOT_PERMISSION, "未能通过权限认证");
    // }
    //
    // /**
    //  * 未能通过角色认证时的处理；
    //  *
    //  * @param exception         抛出的异常
    //  * @return 响应结果
    //  */
    // @ExceptionHandler(NotRoleException.class)
    // public WebResponse<Void> handleNotRoleException(NotRoleException exception) {
    //     log.warn("请求资源地址：'{}'，错误信息：未能通过角色认证", serverHttpRequest.getRequestURI());
    //     return error(HttpStatusEnum.NOT_ROLE, "未能通过角色认证");
    // }

}
