package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.core;

import cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support.OpenFeignOkHttpHandleWebResponseInterceptor;
import cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support.OpenFeignOkHttpInterceptor;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 启用 {@link OpenFeignOkHttpInterceptor}
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableOpenFeignOkHttpInterceptor {

    /**
     * Alias for {@link #interceptors}.
     *
     * @return 拦截器
     */
    @AliasFor("interceptors")
    Class<? extends OpenFeignOkHttpInterceptor<?>>[] value() default OpenFeignOkHttpHandleWebResponseInterceptor.class;

    /**
     * 指定拦截器
     *
     * @return 拦截器
     */
    @AliasFor("value")
    Class<? extends OpenFeignOkHttpInterceptor<?>>[] interceptors() default OpenFeignOkHttpHandleWebResponseInterceptor.class;

}
