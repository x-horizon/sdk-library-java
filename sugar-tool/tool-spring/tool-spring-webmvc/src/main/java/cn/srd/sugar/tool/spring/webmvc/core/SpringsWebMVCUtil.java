package cn.srd.sugar.tool.spring.webmvc.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Spring WebMVC 工具
 *
 * @author wjm
 * @since 2022-08-05 22:53:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringsWebMVCUtil {

    /**
     * 获取请求属性
     *
     * @return 请求属性
     */
    @NonNull
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
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
