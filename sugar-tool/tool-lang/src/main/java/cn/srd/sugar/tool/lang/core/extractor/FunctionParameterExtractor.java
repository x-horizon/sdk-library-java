package cn.srd.sugar.tool.lang.core.extractor;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法形参列表提取器
 * <pre>
 * 支持以下功能：
 * 1、提取方法形参列表中某个形参的值作为返回结果；
 * 2、提取方法形参列表中某个形参内的某个字段值作为返回结果；
 *
 * 参数说明：
 * 1、若显式指定了 {@link #extractIndex()}、{@link #extractFieldName()}，此时将从方法形参列表中的第 {@link #extractIndex()} 个形参中，使用该形参中字段名为 {@link #extractFieldName()} 的值作为提取结果返回；
 * 2、若显式指定了 {@link #extractIndex()}，未指定 {@link #extractFieldName()}，此时将从方法形参列表中的第 {@link #extractIndex()} 个形参中，使用该形参的值作为提取结果返回；
 * 3、若显式指定了 {@link #extractFieldName()}，未指定 {@link #extractIndex()}，此时将使用方法形参列表中的第 1 个形参中的字段名为 {@link #extractFieldName()} 的值作为提取结果返回；
 * 4、若 {@link #extractIndex()}、{@link #extractFieldName()} 两者均未指定，此时将使用方法形参列表中的第 1 个形参的值作为提取结果返回；
 * 5、在 1、3 中，若在方法形参列表中提取的形参为 {@link Iterable} 类型，此时将提取该形参里每个元素中字段名为 {@link #extractFieldName()} 的值并封装为 {@link ArrayList} 作为提取结果返回，在该情况下，{@link FunctionParameterExtractorHandler} 的子类必须将泛型类型指定为 {@link List} 或 {@link ArrayList} 类型；
 *
 * 用法：
 * 1、通过继承该注解，使用 org.springframework.core.annotation.AliasFor 指定该注解中的参数即可；
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
