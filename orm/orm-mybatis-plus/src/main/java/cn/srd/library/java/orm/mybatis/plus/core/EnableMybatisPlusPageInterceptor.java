package cn.srd.library.java.orm.mybatis.plus.core;

import cn.srd.library.java.orm.mybatis.plus.interceptor.MybatisPlusPageInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link MybatisPlusPageInterceptor}
 *
 * @author wjm
 * @since 2023-03-14 15:39:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MybatisPlusPageInterceptor.class)
public @interface EnableMybatisPlusPageInterceptor {

}
