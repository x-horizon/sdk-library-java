package com.test;

import com.test.id.IdGenerateConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MybatisFlexCustomizerFlag.class)
public @interface EnableMybatisFlexCustomizer {

    IdGenerateConfig globalIdGenerateConfig() default @IdGenerateConfig;

}
