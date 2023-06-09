package cn.srd.itcp.sugar.tool.core.asserts;

import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import cn.srd.itcp.sugar.tool.exceptions.ExceptionMessageTemplate;
import cn.srd.itcp.sugar.tool.exceptions.RunningException;
import cn.srd.itcp.sugar.tool.web.HttpStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * 断言工具
 * <pre>
 *   用于快速校验：
 *     1、校验失败时默认抛出{@link RunningException}异常；
 *     2、如果是在接口里调用，在抛出异常的同时响应客户端的默认状态码、描述为{@link HttpStatusEnum#INTERNAL_ERROR}
 *   使用示例：
 *     {@code
 *         Object object = null;
 *         // 校验 object 是否为 null，校验不通过时，抛出默认异常，异常信息为 null is not allowed here，响应客户端默认信息
 *         Assert.I.set("null is not allowed here").throwsIfNull(object);
 *         Assert.I.set(() -> "null is not allowed here").throwsIfNull(object);
 *         Assert.I.set(ExceptionMessageTemplate.NULL_CHECK).throwsIfNull(object);
 *
 *         // 校验 object 是否为 null，校验不通过时，抛出默认异常，异常信息为 Internal Server Error，响应客户端 500 + Internal Server Error
 *         Assert.I.set(HttpStatusEnum.INTERNAL_SERVER_ERROR).throwsIfNull(object);
 *         // 校验 object 是否为 null，校验不通过时，抛出默认异常，异常信息为 null is not allowed here，响应客户端 500 + null is not allowed here
 *         Assert.I.set(HttpStatusEnum.INTERNAL_SERVER_ERROR, "null is not allowed here").throwsIfNull(object);
 *         Assert.I.set(HttpStatusEnum.INTERNAL_SERVER_ERROR, () -> "null is not allowed here").throwsIfNull(object);
 *         // 校验 object 是否为 null，校验不通过时，抛出默认异常，异常信息为 null is not allowed here，响应客户端 500 + null is not allowed here
 *         Assert.I.set(500, "null is not allowed here").throwsIfNull(object);
 *
 *         // 校验 object 是否为 null，校验不通过时，抛出 RuntimeException 异常，异常信息为 null is not allowed here，响应客户端 500 + null is not allowed here
 *         Assert.I.set(new RuntimeException("null is not allowed here")).throwsIfNull(object);
 *         Assert.I.set("null is not allowed here", new RuntimeException("null is not allowed here")).throwsIfNull(object);
 *         Assert.I.set(500, "null is not allowed here", new RuntimeException("null is not allowed here")).throwsIfNull(object);
 *         Assert.I.set(HttpStatusEnum.INTERNAL_SERVER_ERROR, "null is not allowed here", new RuntimeException("null is not allowed here")).throwsIfNull(object);
 *         // 校验 object 是否为 null，校验不通过时，抛出 RunningException 异常，其下级堆栈为 RuntimeException，异常信息为 Internal Server Error，响应客户端 500 + Internal Server Error
 *         Assert.I.set(HttpStatusEnum.INTERNAL_SERVER_ERROR, new RuntimeException("null is not allowed here")).throwsIfNull(object);
 *
 *         // 校验 object 是否为 null，校验不通过时，抛出 RuntimeException 异常，异常信息为 null is not allowed here，响应客户端 500 + null is not allowed here
 *         Assert.I.set("null is not allowed here", RuntimeException.class).throwsIfNull(object);
 *         Assert.I.set(500, "null is not allowed here", RuntimeException.class).throwsIfNull(object);
 *         // 校验 object 是否为 null，校验不通过时，抛出 RunningException 异常，其下级堆栈为 RuntimeException，异常信息为 Internal Server Error，响应客户端 500 + Internal Server Error
 *         Assert.I.set(HttpStatusEnum.INTERNAL_SERVER_ERROR, RuntimeException.class).throwsIfNull(object);
 *         Assert.I.set(HttpStatusEnum.INTERNAL_SERVER_ERROR, "null is not allowed here", RuntimeException.class).throwsIfNull(object);
 *     }
 * </pre>
 *
 * @author wjm
 * @since 2020/6/13 20:05
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assert implements AssertSupport {

    /**
     * 空实例构造
     */
    public static final Assert INSTANCE = of();

    /**
     * 必须不为 null 异常实例构造
     */
    public static final Assert NULL_CHECK = of(ExceptionMessageTemplate.NULL_CHECK);

    /**
     * 必须不为 empty 异常实例构造
     */
    public static final Assert EMPTY_CHECK = of(ExceptionMessageTemplate.EMPTY_CHECK);

    /**
     * 必须不为 blank 异常实例构造
     */
    public static final Assert BLANK_CHECK = of(ExceptionMessageTemplate.BLANK_CHECK);

    /**
     * 必须不为 true 异常实例构造
     */
    public static final Assert TRUE_CHECK = of(ExceptionMessageTemplate.TRUE_CHECK);

    /**
     * 必须不为 false 异常实例构造
     */
    public static final Assert FALSE_CHECK = of(ExceptionMessageTemplate.FALSE_CHECK);

    /**
     * 必须不为正整数 异常实例构造
     */
    public static final Assert POSITIVE_CHECK = of(ExceptionMessageTemplate.POSITIVE_CHECK);

    /**
     * 必须不相等 异常实例构造
     */
    public static final Assert EQUALS_CHECK = of(ExceptionMessageTemplate.EQUALS_CHECK);

    /**
     * 必须为 null 异常实例构造
     */
    public static final Assert NULL_NEED = of(ExceptionMessageTemplate.NULL_NEED);

    /**
     * 必须为 empty 异常实例构造
     */
    public static final Assert EMPTY_NEED = of(ExceptionMessageTemplate.EMPTY_NEED);

    /**
     * 必须为 blank 异常实例构造
     */
    public static final Assert BLANK_NEED = of(ExceptionMessageTemplate.BLANK_NEED);

    /**
     * 必须为 true 异常实例构造
     */
    public static final Assert TRUE_NEED = of(ExceptionMessageTemplate.TRUE_NEED);

    /**
     * 必须为 false 异常实例构造
     */
    public static final Assert FALSE_NEED = of(ExceptionMessageTemplate.FALSE_NEED);

    /**
     * 必须为正整数 异常实例构造
     */
    public static final Assert POSITIVE_NEED = of(ExceptionMessageTemplate.POSITIVE_NEED);

    /**
     * 必须相等 异常实例构造
     */
    public static final Assert EQUALS_NEED = of(ExceptionMessageTemplate.EQUALS_NEED);

    /**
     * 不支持的操作 异常实例构造
     */
    public static final Assert UNSUPPORTED_OPERATE = of(ExceptionMessageTemplate.UNSUPPORTED_OPERATE);

    /**
     * 不支持的参数 异常实例构造
     */
    public static final Assert UNSUPPORTED_PARAMETERS = of(ExceptionMessageTemplate.UNSUPPORTED_PARAMETERS);

    private static Assert of() {
        return new Assert();
    }

    private static Assert of(ExceptionMessageTemplate exceptionMessageTemplate) {
        return (Assert) of().set(exceptionMessageTemplate);
    }

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String description;

    /**
     * 自定义抛出的异常
     */
    private Exception exception;

    @Override
    public Assert set(@Nullable Integer code, @Nullable String msg, @Nullable Exception exception) {
        Assert asserts = of();
        asserts.code = Objects.setIfNull(code, HttpStatusEnum.INTERNAL_ERROR.getCode());
        asserts.description = Objects.emptyIfNull(msg);
        asserts.exception = exception;
        return asserts;
    }

    @Override
    @SneakyThrows
    public void throwsNow() {
        if (Objects.isNotEmpty(this.getException())) {
            throw this.getException();
        }
        throw new RunningException(this);
    }

}
