package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MybatisFlexCustomizerScanner {

    String[] value() default {};

}
