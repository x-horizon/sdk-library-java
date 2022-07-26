package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.spring.SaBeanInject;
import cn.dev33.satoken.spring.SaBeanRegister;
import cn.srd.itcp.sugar.security.sa.token.core.EnableSaWebExceptionHandler;
import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Security Sa-Token
 *
 * @author wjm
 * @since 2022-07-07
 */
@Configuration
@ConditionalOnClass({SaBeanRegister.class, SaBeanInject.class})
public class SugarSaTokenAutoConfiguration {

    @Bean
    public SugarSaTokenProperties sugarSaTokenProperties() {
        return SpringsUtil.registerCapableBean(SugarSaTokenProperties.class);
    }

    @Bean
    @DependsOn("sugarSaTokenProperties")
    public SaTokenPreEachRequestAnnotationInterceptor saTokenPreEachRequestAnnotationInterceptor() {
        return SpringsUtil.registerCapableBean(SaTokenPreEachRequestAnnotationInterceptor.class);
    }

    @Bean
    public AnonymousSupporter anonymousSupporter() {
        return new AnonymousSupporter();
    }

    @Bean
    public SaTokenWebMvcConfig saTokenWebMvcConfig() {
        return new SaTokenWebMvcConfig();
    }

    @Bean
    public SaTokenWebExceptionHandler saWebExceptionHandler() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableSaWebExceptionHandler.class))) {
            return SpringsUtil.registerCapableBean(SaTokenWebExceptionHandler.class);
        }
        return null;
    }

}
