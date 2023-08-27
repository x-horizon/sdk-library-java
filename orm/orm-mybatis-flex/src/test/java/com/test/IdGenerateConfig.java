package com.test;

import com.test.id.IdGenerateType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdGenerateConfig {

    IdGenerateType type() default IdGenerateType.NONE;

    String sql() default "";

}