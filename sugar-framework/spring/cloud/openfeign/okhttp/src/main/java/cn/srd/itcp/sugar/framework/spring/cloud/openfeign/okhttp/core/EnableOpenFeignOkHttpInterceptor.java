package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.core;

import cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support.OpenFeignOkHttpInterceptor;

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
     * 指定拦截器集合
     *
     * @return 拦截器
     */
    Class<? extends OpenFeignOkHttpInterceptor<?>>[] value();

}
