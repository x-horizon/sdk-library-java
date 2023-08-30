package com.test;

import com.test.audit.AuditConfig;
import com.test.id.IdGenerateConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableMybatisFlexCapableCustomizerFlag.class)
public @interface EnableMybatisFlexCapableCustomizer {

    IdGenerateConfig globalIdGenerateConfig() default @IdGenerateConfig;

    AuditConfig auditConfig() default @AuditConfig;

}
