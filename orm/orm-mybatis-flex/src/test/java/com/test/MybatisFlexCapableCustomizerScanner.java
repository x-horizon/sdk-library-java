package com.test;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MybatisFlexCapableCustomizerScanner {

    String[] value() default {};

}
