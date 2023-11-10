package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisFlexCustomizerSwitcher.class)
public @interface EnableMybatisFlexCustomizer {

    IdConfig globalIdGenerateConfig() default @IdConfig;

    // AuditConfig auditConfig() default @AuditConfig;

}
