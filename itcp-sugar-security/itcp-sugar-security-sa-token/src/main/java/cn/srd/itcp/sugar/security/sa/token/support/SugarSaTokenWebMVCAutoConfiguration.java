package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.spring.SaBeanInject;
import cn.dev33.satoken.spring.SaBeanRegister;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.strategy.SaStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Security Sa-Token WebMVC
 *
 * @author wjm
 * @since 2022-07-07
 */
@AutoConfiguration
@ConditionalOnClass({SaBeanRegister.class, SaBeanInject.class})
public class SugarSaTokenWebMVCAutoConfiguration {

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
     * 装配 {@link SaTokenWebMVCConfig}
     *
     * @return 装配对象
     */
    @Bean
    @ConditionalOnBean(SaTokenPreEachRequestWebMVCAnnotationInterceptor.class)
    public SaTokenWebMVCConfig saTokenWebMvcConfig() {
        return new SaTokenWebMVCConfig();
    }

}
