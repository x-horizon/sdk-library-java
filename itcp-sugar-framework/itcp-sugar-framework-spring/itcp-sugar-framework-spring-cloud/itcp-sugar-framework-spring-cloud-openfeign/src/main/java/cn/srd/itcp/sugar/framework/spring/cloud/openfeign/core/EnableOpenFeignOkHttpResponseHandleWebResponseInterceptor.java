package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.core;

import cn.srd.itcp.sugar.framework.spring.cloud.openfeign.support.OpenFeignOkHttpResponseHandleWebResponseInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link OpenFeignOkHttpResponseHandleWebResponseInterceptor} 的功能
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(OpenFeignOkHttpResponseHandleWebResponseInterceptor.class)
public @interface EnableOpenFeignOkHttpResponseHandleWebResponseInterceptor {

}
