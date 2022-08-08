package cn.srd.itcp.sugar.tools.core;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring Web 工具
 *
 * @author wjm
 * @since 2022-08-05 22:53:11
 */
public class SpringsWebUtil {

    private SpringsWebUtil() {
    }

    public static ServletRequestAttributes getServletRequestAttributes() {
        return Objects.requireNotNull(() -> "当前为非web上下文环境，无法获取，请检查！", (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
    }

    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

}
