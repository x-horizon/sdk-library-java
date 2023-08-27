package com.test;

import com.test.common.AnnotationScanner;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@AnnotationScanner
public @interface MybatisFlexCustomizerScanner {

    String[] value() default {};

}
