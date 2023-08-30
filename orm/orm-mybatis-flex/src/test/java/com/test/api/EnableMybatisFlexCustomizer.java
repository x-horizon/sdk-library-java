package com.test.api;

import com.test.core.audit.AuditConfig;
import com.test.core.id.IdGenerateConfig;
import com.test.starter.EnableMybatisFlexCustomizerFlag;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableMybatisFlexCustomizerFlag.class)
public @interface EnableMybatisFlexCustomizer {

    IdGenerateConfig globalIdGenerateConfig() default @IdGenerateConfig;

    AuditConfig auditConfig() default @AuditConfig;

}
