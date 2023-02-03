package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.spring.SaBeanInject;
import cn.dev33.satoken.spring.SaBeanRegister;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.srd.itcp.sugar.security.sa.token.core.EnableSaTokenPreEachRequestAnnotationInterceptor;
import cn.srd.itcp.sugar.security.sa.token.core.EnableSaTokenWebExceptionHandler;
import cn.srd.itcp.sugar.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Security Sa-Token
 *
 * @author wjm
 * @since 2022-07-07
 */
@Configuration
@ConditionalOnClass({SaBeanRegister.class, SaBeanInject.class})
public class SugarSaTokenAutoConfiguration {

    /**
     * 功能增强
     */
    @PostConstruct
    public void enhanceSaToken() {
        // 增加注解合并功能
        SaStrategy.me.getAnnotation = AnnotatedElementUtils::getMergedAnnotation;
    }

    /**
     * 装配 {@link AnonymousSupporter}
     *
     * @return 装配对象
     */
    @Bean
    public AnonymousSupporter anonymousSupporter() {
        return new AnonymousSupporter();
    }

    /**
     * 装配自定义权限认证
     *
     * @return 装配对象
     */
    @Bean
    public StpLogic stpLogic() {
        // 注入整合了 jwt 的 StpLogic
        return new StpLogicJwtForSimple();
    }

    /**
     * 装配 {@link SaTokenWebMvcConfig}
     *
     * @return 装配对象
     */
    @Bean
    public SaTokenWebMvcConfig saTokenWebMvcConfig() {
        return new SaTokenWebMvcConfig();
    }

    /**
     * 装配 {@link SaTokenWebExceptionHandler}
     *
     * @return 装配对象
     */
    @Bean
    public SaTokenWebExceptionHandler saWebExceptionHandler() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableSaTokenWebExceptionHandler.class))) {
            return SpringsUtil.registerCapableBean(SaTokenWebExceptionHandler.class);
        }
        return null;
    }

    /**
     * 装配 {@link SaTokenPreEachRequestAnnotationInterceptor}
     *
     * @return 装配对象
     */
    @Bean
    public SaTokenPreEachRequestAnnotationInterceptor saTokenPreEachRequestAnnotationInterceptor() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableSaTokenPreEachRequestAnnotationInterceptor.class))) {
            return SpringsUtil.registerCapableBean(SaTokenPreEachRequestAnnotationInterceptor.class);
        }
        return null;
    }

}
