package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.spring.SaBeanInject;
import cn.dev33.satoken.spring.SaBeanRegister;
import cn.srd.itcp.sugar.security.sa.token.common.support.SugarSaTokenProperties;
import cn.srd.itcp.sugar.security.sa.token.core.EnableSaWebExceptionHandler;
import cn.srd.itcp.sugar.spring.common.tool.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
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

    /**
     * 装配 {@link SugarSaTokenProperties}
     *
     * @return 装配对象
     */
    @Bean
    public SugarSaTokenProperties sugarSaTokenProperties() {
        return SpringsUtil.registerCapableBean(SugarSaTokenProperties.class);
    }

    /**
     * 装配 {@link SaTokenPreEachRequestAnnotationInterceptor}
     *
     * @return 装配对象
     */
    @Bean
    @DependsOn("sugarSaTokenProperties")
    public SaTokenPreEachRequestAnnotationInterceptor saTokenPreEachRequestAnnotationInterceptor() {
        return SpringsUtil.registerCapableBean(SaTokenPreEachRequestAnnotationInterceptor.class);
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
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableSaWebExceptionHandler.class))) {
            return SpringsUtil.registerCapableBean(SaTokenWebExceptionHandler.class);
        }
        return null;
    }

}
