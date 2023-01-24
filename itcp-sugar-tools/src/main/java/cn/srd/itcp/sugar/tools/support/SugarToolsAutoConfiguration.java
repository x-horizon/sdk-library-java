package cn.srd.itcp.sugar.tools.support;

import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import cn.srd.itcp.sugar.tools.core.enums.autowired.EnableEnumAutowired;
import cn.srd.itcp.sugar.tools.core.enums.autowired.EnumAutowiredSupport;
import cn.srd.itcp.sugar.tools.web.EnableWebExceptionHandler;
import cn.srd.itcp.sugar.tools.web.EnableWebResponseAdvice;
import cn.srd.itcp.sugar.tools.web.WebExceptionHandler;
import cn.srd.itcp.sugar.tools.web.WebResponseAdvice;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Tools
 *
 * @author wjm
 * @since 2022-07-14
 */
public class SugarToolsAutoConfiguration {

    /**
     * 装配 {@link EnumAutowiredSupport}
     *
     * @return 装配对象
     */
    @Bean
    public EnumAutowiredSupport enumAutowiredSupport() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableEnumAutowired.class))) {
            return new EnumAutowiredSupport();
        }
        return null;
    }

    /**
     * 装配 {@link WebExceptionHandler}
     *
     * @return 装配对象
     */
    @Bean
    public WebExceptionHandler webExceptionHandler() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableWebExceptionHandler.class))) {
            return SpringsUtil.registerCapableBean(WebExceptionHandler.class);
        }
        return null;
    }

    /**
     * 装配 {@link WebResponseAdvice}
     *
     * @return 装配对象
     */
    @Bean
    public WebResponseAdvice webResponseAdvice() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableWebResponseAdvice.class))) {
            return SpringsUtil.registerCapableBean(WebResponseAdvice.class);
        }
        return null;
    }

}
