package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * sa-token web mvc 配置
 *
 * @author wjm
 * @since 2022-07-07
 */
public class SaTokenWebMvcConfig implements WebMvcConfigurer {

    /**
     * 代表所有 URI 的路径匹配符
     */
    private static final String MATCH_ALL_ENDPOINTS_PATTER = "/**";

    @PostConstruct
    public void enhanceSaToken() {
        // 增加注解合并功能
        SaStrategy.me.getAnnotation = AnnotatedElementUtils::getMergedAnnotation;
    }

    @Bean
    public StpLogic stpLogic() {
        // 注入整合了 jwt 的 StpLogic
        return new StpLogicJwtForSimple();
    }

    /**
     * 注册拦截器
     */
    @DependsOn("sugarSaTokenAutoConfiguration")
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        // 注册每次请求前的注解式拦截器
        interceptorRegistry
                .addInterceptor(SpringsUtil.getBean(SaTokenPreEachRequestAnnotationInterceptor.class))
                .addPathPatterns(MATCH_ALL_ENDPOINTS_PATTER);
    }

}
