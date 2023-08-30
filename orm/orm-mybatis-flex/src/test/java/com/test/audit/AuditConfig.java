package com.test.audit;

import cn.srd.library.java.tool.constant.core.StringPool;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditConfig {

    String enableAuditMessage() default StringPool.FALSE;

}