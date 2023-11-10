package cn.srd.library.java.orm.mybatis.flex.base.id;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdConfig {

    IdGenerateType type() default IdGenerateType.UNCONTROLLED;

    Class<? extends IdGenerator> generator() default IdInvalidGenerator.class;

    String generateSQL() default "";

}