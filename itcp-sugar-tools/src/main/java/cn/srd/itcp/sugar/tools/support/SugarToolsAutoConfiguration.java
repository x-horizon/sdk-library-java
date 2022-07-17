package cn.srd.itcp.sugar.tools.support;

import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import cn.srd.itcp.sugar.tools.core.enums.autowired.EnableEnumAutowired;
import cn.srd.itcp.sugar.tools.core.enums.autowired.EnumAutowiredSupport;
import cn.srd.itcp.sugar.tools.web.EnableWebExceptionHandler;
import cn.srd.itcp.sugar.tools.web.EnableWebResponseHandlerInterceptor;
import cn.srd.itcp.sugar.tools.web.WebExceptionHandler;
import cn.srd.itcp.sugar.tools.web.WebResponseAdvice;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Tools
 *
 * @author wjm
 * @date 2022-07-14
 */
public class SugarToolsAutoConfiguration {

    @Bean
    public EnumAutowiredSupport enumAutowiredSupport() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableEnumAutowired.class))) {
            return new EnumAutowiredSupport();
        }
        return null;
    }

    @Bean
    public WebExceptionHandler webExceptionHandler() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableWebExceptionHandler.class))) {
            return SpringsUtil.registerCapableBean(WebExceptionHandler.class);
        }
        return null;
    }

    @Bean
    public WebResponseAdvice webResponseAdvice() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableWebResponseHandlerInterceptor.class))) {
            return SpringsUtil.registerCapableBean(WebResponseAdvice.class);
        }
        return null;
    }

}
