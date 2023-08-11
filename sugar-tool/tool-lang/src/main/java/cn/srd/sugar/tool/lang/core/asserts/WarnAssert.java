package cn.srd.sugar.tool.lang.core.asserts;

import cn.srd.sugar.contract.throwable.core.ExceptionMessageTemplate;
import cn.srd.sugar.contract.throwable.core.WarnOperationException;
import cn.srd.sugar.tool.lang.core.object.Objects;
import cn.srd.sugar.tool.lang.core.validation.Nullable;
import cn.srd.sugar.tool.lang.web.HttpStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 断言工具，与 {@link Assert} 区别在于
 * <pre>
 *  1、WarnAssert 抛出的是 {@link WarnOperationException}；
 *  2、用于 SpringMVC 时拦截，此时后台用 log.warn 打印异常信息，但不输出堆栈；
 *  3、不属于接口调用时，后台抛出 {@link WarnOperationException} 堆栈；
 *  4、不支持设置异常
 * </pre>
 *
 * @author wjm
 * @since 2021/5/6 17:27
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WarnAssert implements AssertSupport {

    /**
     * 空实例构造
     */
    public static final WarnAssert INSTANCE = of();

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
    private WarnOperationException exception;

    /**
     * 构造实例
     *
     * @param exceptionMessageTemplate 异常模板信息
     * @return instance
     */
    private static WarnAssert of(ExceptionMessageTemplate exceptionMessageTemplate) {
        return (WarnAssert) of().set(exceptionMessageTemplate);
    }

    /**
     * 构造空实例
     *
     * @return 空实例
     */
    private static WarnAssert of() {
        return new WarnAssert();
    }

    @Override
    public WarnAssert set(Integer code, String msg, Exception exception) {
        return set(code, msg);
    }

    @Override
    public WarnAssert set(@Nullable Integer code, @Nullable String msg) {
        WarnAssert anAssert = of();
        anAssert.code = Objects.setIfNull(code, HttpStatusEnum.INTERNAL_ERROR.getCode());
        anAssert.description = Objects.emptyIfNull(msg);
        anAssert.exception = new WarnOperationException(anAssert);
        return anAssert;
    }

    @Override
    public void throwsNow() {
        throw new WarnOperationException(this);
    }

}
