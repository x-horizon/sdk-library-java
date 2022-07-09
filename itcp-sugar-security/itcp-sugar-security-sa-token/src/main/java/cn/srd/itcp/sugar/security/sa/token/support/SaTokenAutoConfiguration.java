package cn.srd.itcp.sugar.security.sa.token.support;

import cn.dev33.satoken.spring.SaBeanInject;
import cn.dev33.satoken.spring.SaBeanRegister;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Security Sa-Token
 *
 * @author wjm
 * @date 2022-07-07
 */
@Configuration
@ConditionalOnClass({SaBeanRegister.class, SaBeanInject.class})
public class SaTokenAutoConfiguration {

    @Bean
    public AnonymousSupporter saAnonymousSupporter() {
        return new AnonymousSupporter();
    }

    @Bean
    public SaTokenConfig saConfig() {
        return new SaTokenConfig();
    }

}
