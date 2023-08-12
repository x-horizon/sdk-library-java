package cn.srd.library.web.openfeign.okhttp.core;

import cn.srd.library.tool.lang.web.ResponseModel;
import cn.srd.library.web.openfeign.okhttp.support.OpenFeignOkHttpHandleRepsonseInterceptor;

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
