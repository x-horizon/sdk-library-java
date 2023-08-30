package com.test.api;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MybatisFlexCustomizerScanner {

    String[] value() default {};

}
