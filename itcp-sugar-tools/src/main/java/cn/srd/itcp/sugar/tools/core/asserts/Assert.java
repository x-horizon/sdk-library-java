package cn.srd.itcp.sugar.tools.core.asserts;

import cn.srd.itcp.sugar.tools.core.Objects;
import cn.srd.itcp.sugar.tools.exceptions.ExceptionMessageTemplate;
import cn.srd.itcp.sugar.tools.exceptions.RunningException;
import cn.srd.itcp.sugar.tools.web.HttpStatusEnum;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.lang.Nullable;

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
public class Assert implements AssertSupport {

    private Assert() {
    }

    public static final Assert INSTANCE = of();
    public static final Assert NULL_CHECK = of(ExceptionMessageTemplate.NULL_CHECK);
    public static final Assert EMPTY_CHECK = of(ExceptionMessageTemplate.EMPTY_CHECK);
    public static final Assert BLANK_CHECK = of(ExceptionMessageTemplate.BLANK_CHECK);
    public static final Assert TRUE_CHECK = of(ExceptionMessageTemplate.TRUE_CHECK);
    public static final Assert FALSE_CHECK = of(ExceptionMessageTemplate.FALSE_CHECK);
    public static final Assert POSITIVE_CHECK = of(ExceptionMessageTemplate.POSITIVE_CHECK);
    public static final Assert EQUALS_CHECK = of(ExceptionMessageTemplate.EQUALS_CHECK);
    public static final Assert NULL_NEED = of(ExceptionMessageTemplate.NULL_NEED);
    public static final Assert EMPTY_NEED = of(ExceptionMessageTemplate.EMPTY_NEED);
    public static final Assert BLANK_NEED = of(ExceptionMessageTemplate.BLANK_NEED);
    public static final Assert TRUE_NEED = of(ExceptionMessageTemplate.TRUE_NEED);
    public static final Assert FALSE_NEED = of(ExceptionMessageTemplate.FALSE_NEED);
    public static final Assert POSITIVE_NEED = of(ExceptionMessageTemplate.POSITIVE_NEED);
    public static final Assert EQUALS_NEED = of(ExceptionMessageTemplate.EQUALS_NEED);
    public static final Assert UNSUPPORTED_OPERATE = of(ExceptionMessageTemplate.UNSUPPORTED_OPERATE);
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
