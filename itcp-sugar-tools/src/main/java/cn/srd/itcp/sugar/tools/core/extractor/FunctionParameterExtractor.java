package cn.srd.itcp.sugar.tools.core.extractor;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 方法形参列表提取器
 * <pre>
 * 支持以下功能：
 * 1、提取方法形参列表中某个形参的值作为返回结果；
 * 2、提取方法形参列表中某个形参内的某个字段值作为返回结果；
 *
 * 参数说明：
 * 1、若显式指定了 {@link #extractIndex()}、{@link #extractFieldName()}，此时将从方法形参列表中的第 {@link #extractIndex()} 个形参中，使用该形参中字段名为 {@link #extractFieldName()} 的值作为操作对象；
 * 2、若显式指定了 {@link #extractIndex()}，未指定 {@link #extractFieldName()}，此时将从方法形参列表中的第 {@link #extractIndex()} 个形参中，使用该形参的值作为操作对象；
 * 3、若显式指定了 {@link #extractFieldName()}，未指定 {@link #extractIndex()}，此时将使用方法形参列表中的第 1 个形参中的字段名为 {@link #extractFieldName()} 的值作为操作对象；
 * 4、若 {@link #extractIndex()}、{@link #extractFieldName()} 两者均未指定，此时将使用方法形参列表中的第 1 个形参的值作为操作对象；
 *
 * 用法：
 * 1、通过继承该注解，使用 {@link AliasFor} 指定该注解中的参数即可；
 * 2、该注解的 {@link ElementType#TYPE} 属性是为了让该注解可以被其他注解标记在类上来继承，并不代表从类上提取值；
 * </pre>
 *
 * @author wjm
 * @see FunctionParameterExtractorHandler
 * @since 2022-07-30 22:29:14
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FunctionParameterExtractor {

    /**
     * @return 形参列表索引，0 或 1 表示从方法形参列表中的第一个参数进行提取，n 表示从方法形参列表中的第 n 个参数进行提取
     * @see FunctionParameterExtractor
     */
    int extractIndex() default 0;

    /**
     * @return 形参列表中某个形参内的字段名
     * @see FunctionParameterExtractor
     */
    String extractFieldName() default "";

}
