package cn.srd.itcp.sugar.security.sa.token.reactor.support;

import cn.srd.itcp.sugar.security.sa.token.reactor.core.EnableSaTokenWebReactorExceptionHandler;
import cn.srd.itcp.sugar.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Security Sa-Token Web Reactor
 *
 * @author wjm
 * @since 2023-02-04 17:49:16
 */
@Configuration
public class SugarSaTokenWebReactorAutoConfiguration {

    /**
     * 装配 {@link SaTokenWebReactorExceptionHandler}
     *
     * @return 装配对象
     */
    @Bean
    public SaTokenWebReactorExceptionHandler saTokenWebReactorExceptionHandler() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableSaTokenWebReactorExceptionHandler.class))) {
            return SpringsUtil.registerCapableBean(SaTokenWebReactorExceptionHandler.class);
        }
        return null;
    }

}
