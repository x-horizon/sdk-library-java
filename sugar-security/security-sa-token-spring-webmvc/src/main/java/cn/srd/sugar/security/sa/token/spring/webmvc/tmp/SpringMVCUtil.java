package cn.srd.sugar.security.sa.token.spring.webmvc.tmp;

import cn.dev33.satoken.error.SaSpringBootErrorCode;
import cn.dev33.satoken.exception.NotWebContextException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * TODO wjm copy from {@link cn.dev33.satoken.spring.SpringMVCUtil}，修改 javax 为 Jakarta，暂时用于兼容 JDK 17，后续官方版本支持后去掉
 *
 * @author wjm
 * @since 2023-01-28 00:29:11
 */
public class SpringMVCUtil {

    private SpringMVCUtil() {
    }

    /**
     * 获取当前会话的 request
     *
     * @return request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new NotWebContextException("非Web上下文无法获取Request").setCode(SaSpringBootErrorCode.CODE_20101);
        }
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取当前会话的 response
     *
     * @return response
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new NotWebContextException("非Web上下文无法获取Response").setCode(SaSpringBootErrorCode.CODE_20101);
        }
        return servletRequestAttributes.getResponse();
    }

    /**
     * 判断当前是否处于 Web 上下文中
     *
     * @return request
     */
    public static boolean isWeb() {
        return RequestContextHolder.getRequestAttributes() != null;
    }

}
