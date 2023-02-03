package cn.srd.itcp.sugar.spring.web.mvc.tool.support;

import cn.srd.itcp.sugar.spring.common.tool.core.SpringsUtil;
import cn.srd.itcp.sugar.spring.web.mvc.tool.core.web.EnableWebExceptionHandler;
import cn.srd.itcp.sugar.spring.web.mvc.tool.core.web.EnableWebResponseAdvice;
import cn.srd.itcp.sugar.spring.web.mvc.tool.core.web.WebExceptionHandler;
import cn.srd.itcp.sugar.spring.web.mvc.tool.core.web.WebResponseAdvice;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Tools
 *
 * @author wjm
 * @since 2022-07-14
 */
public class SugarSpringWebMVCToolAutoConfiguration {

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
