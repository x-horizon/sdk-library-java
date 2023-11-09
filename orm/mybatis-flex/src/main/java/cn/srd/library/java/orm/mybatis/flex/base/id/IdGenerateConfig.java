package cn.srd.library.java.orm.mybatis.flex.base.id;

import com.mybatisflex.core.keygen.IKeyGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdGenerateConfig {

    IdGenerateType type() default IdGenerateType.UNCONTROLLED;

    Class<? extends IKeyGenerator> generator() default IdInvalidGenerator.class;

    String sql() default "";

}