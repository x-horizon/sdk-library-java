package cn.srd.itcp.sugar.tools.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Spring Web 工具
 *
 * @author wjm
 * @since 2022-08-05 22:53:11
 */
public class SpringsWebUtil {

    /**
     * private block constructor
     */
    private SpringsWebUtil() {
    }

    /**
     * 获取请求属性
     *
     * @return 请求属性
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        return Objects.requireNotNull(() -> "当前为非web上下文环境，无法获取，请检查！", (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
    }

    /**
     * 获取请求对象
     *
     * @return 请求对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    public static HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

}
