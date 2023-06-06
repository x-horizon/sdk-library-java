package cn.srd.itcp.sugar.component.web.openfeign.okhttp.core;

import cn.srd.itcp.sugar.component.web.openfeign.okhttp.support.OpenFeignOkHttpHandleRepsonseInterceptor;
import cn.srd.itcp.sugar.tool.web.ResponseModel;

import java.lang.annotation.*;

/**
 * 启用 {@link OpenFeignOkHttpHandleRepsonseInterceptor}
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableOpenFeignOkHttpHandleResponseInterceptor {

    /**
     * 指定要解析的 response model 集合（按指定顺序进行解析）
     *
     * @return response model 集合
     */
    Class<? extends ResponseModel>[] models();

}
