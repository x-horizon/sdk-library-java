package cn.srd.itcp.sugar.spring.tool.web.mvc.support;

import cn.srd.itcp.sugar.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.spring.tool.web.mvc.core.web.EnableWebMVCExceptionHandler;
import cn.srd.itcp.sugar.spring.tool.web.mvc.core.web.EnableWebMVCResponseAdvice;
import cn.srd.itcp.sugar.spring.tool.web.mvc.core.web.WebMVCExceptionHandler;
import cn.srd.itcp.sugar.spring.tool.web.mvc.core.web.WebMVCResponseAdvice;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Spring WebMVC Tool
 *
 * @author wjm
 * @since 2022-07-14
 */
public class SugarSpringWebMVCToolAutoConfiguration {

    /**
     * 装配 {@link WebMVCExceptionHandler}
     *
     * @return 装配对象
     */
    @Bean
    public WebMVCExceptionHandler webExceptionHandler() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableWebMVCExceptionHandler.class))) {
            return SpringsUtil.registerCapableBean(WebMVCExceptionHandler.class);
        }
        return null;
    }

    /**
     * 装配 {@link WebMVCResponseAdvice}
     *
     * @return 装配对象
     */
    @Bean
    public WebMVCResponseAdvice webResponseAdvice() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableWebMVCResponseAdvice.class))) {
            return SpringsUtil.registerCapableBean(WebMVCResponseAdvice.class);
        }
        return null;
    }

}
