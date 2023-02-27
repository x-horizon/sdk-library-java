package cn.srd.itcp.sugar.security.sa.token.webmvc.support;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * sa-token web mvc 配置
 *
 * @author wjm
 * @since 2022-07-07
 */
public class SaTokenWebMVCConfig implements WebMvcConfigurer {

    /**
     * 代表所有 URI 的路径匹配符
     */
    private static final String MATCH_ALL_ENDPOINTS_PATTER = "/**";

    /**
     * 注册拦截器
     *
     * @param interceptorRegistry 拦截器注册器
     */
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry interceptorRegistry) {
        SaTokenPreEachRequestWebMVCAnnotationInterceptor saTokenPreEachRequestWebMVCAnnotationInterceptor = SpringsUtil.getBean(SaTokenPreEachRequestWebMVCAnnotationInterceptor.class);
        if (Objects.isNotNull(saTokenPreEachRequestWebMVCAnnotationInterceptor)) {
            interceptorRegistry
                    // 注册每次请求前的注解式拦截器
                    .addInterceptor(saTokenPreEachRequestWebMVCAnnotationInterceptor)
                    // 对所有路径生效
                    .addPathPatterns(MATCH_ALL_ENDPOINTS_PATTER);
        }
    }

}
