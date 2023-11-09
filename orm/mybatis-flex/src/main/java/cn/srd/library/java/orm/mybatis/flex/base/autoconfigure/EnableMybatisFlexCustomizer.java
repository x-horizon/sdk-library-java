package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(EnableMybatisFlexCustomizerFlag.class)
public @interface EnableMybatisFlexCustomizer {

    IdGenerateConfig globalIdGenerateConfig() default @IdGenerateConfig;

    // AuditConfig auditConfig() default @AuditConfig;

}
