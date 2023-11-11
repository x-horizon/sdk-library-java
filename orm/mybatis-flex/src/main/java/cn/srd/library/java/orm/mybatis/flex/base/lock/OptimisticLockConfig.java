package cn.srd.library.java.orm.mybatis.flex.base.lock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptimisticLockConfig {

    String columnName() default "";

}