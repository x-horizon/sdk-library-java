package cn.srd.sugar.web.okhttps.core;

import cn.srd.itcp.sugar.tool.core.ReflectsUtil;
import cn.srd.itcp.sugar.tool.core.StringsUtil;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import cn.srd.sugar.contract.model.core.HttpStatusEnum;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.function.Consumer;

/**
 * the util of {@link OkHttps}
 *
 * @author wjm
 * @since 2023-07-01 10:15:26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OkHttpsUtil {

    /**
     * the {@link HttpResult} is healthy or not
     *
     * @param httpResult the {@link HttpResult}
     * @return the {@link HttpResult} is healthy or not
     */
    public static boolean isHttpResultHealthy(HttpResult httpResult) {
        return isHttpStateHealthy(httpResult.getState()) && HttpStatusEnum.isSuccess(httpResult.getStatus());
    }

    /**
     * the {@link HttpResult.State} is {@link HttpResult.State#RESPONSED} or not
     *
     * @param state the {@link HttpResult.State}
     * @return the {@link HttpResult.State} is {@link HttpResult.State#RESPONSED} or not
     */
    public static boolean isHttpStateHealthy(HttpResult.State state) {
        return Objects.equals(HttpResult.State.RESPONSED, state);
    }

    /**
     * see {@link #isHttpResultHealthy(HttpResult)}
     *
     * @param httpResult the {@link HttpResult}
     * @return the {@link HttpResult} is not healthy or not
     */
    public static boolean isHttpResultNotHealthy(HttpResult httpResult) {
        return !isHttpResultHealthy(httpResult);
    }

    /**
     * see {@link #isHttpStateHealthy(HttpResult.State)}
     *
     * @param state the {@link HttpResult.State}
     * @return the {@link HttpResult.State} is not {@link HttpResult.State#RESPONSED} or not
     */
    public static boolean isHttpStateNotHealthy(HttpResult.State state) {
        return !isHttpStateHealthy(state);
    }

    /**
     * throw exception if {@link #isHttpResultNotHealthy(HttpResult)}
     *
     * @param httpResult   the {@link HttpResult}
     * @param errorMessage the specified message to throw if {@link #isHttpResultNotHealthy(HttpResult)}
     * @param throwable    the specified exception to throw if {@link #isHttpResultNotHealthy(HttpResult)}
     */
    @SneakyThrows
    public static void requiredHttpResultHealthy(HttpResult httpResult, String errorMessage, Class<? extends Throwable> throwable) {
        if (isHttpResultNotHealthy(httpResult)) {
            throw ReflectsUtil.newInstance(throwable, errorMessage);
        }
    }

    /**
     * throw exception if {@link #isHttpStateNotHealthy(HttpResult.State)}
     *
     * @param state the {@link HttpResult.State}
     */
    public static void requiredHttpStateHealthy(HttpResult.State state) {
        if (isHttpStateNotHealthy(state)) {
            throw new RuntimeException(StringsUtil.format("okhttps failed because of the http state is [{}]", state.name()));
        }
    }

    /**
     * throw exception if {@link #isHttpStateNotHealthy(HttpResult.State)}
     *
     * @param state        the {@link HttpResult.State}
     * @param errorMessage the specified message to throw if {@link #isHttpStateNotHealthy(HttpResult.State)}
     */
    public static void requiredHttpStateHealthy(HttpResult.State state, String errorMessage) {
        if (isHttpStateNotHealthy(state)) {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * throw exception if {@link #isHttpStateNotHealthy(HttpResult.State)}
     *
     * @param state        the {@link HttpResult.State}
     * @param errorMessage the specified message to throw if {@link #isHttpStateNotHealthy(HttpResult.State)}
     * @param throwable    the specified exception to throw if {@link #isHttpStateNotHealthy(HttpResult.State)}
     */
    @SneakyThrows
    public static void requiredHttpStateHealthy(HttpResult.State state, String errorMessage, Class<? extends Throwable> throwable) {
        if (isHttpStateNotHealthy(state)) {
            throw ReflectsUtil.newInstance(throwable, errorMessage);
        }
    }

    /**
     * do something after {@link #isHttpStateNotHealthy(HttpResult.State)}
     *
     * @param state the {@link HttpResult.State}
     * @param logic the logic
     */
    public static void httpStateNotHealthyAndThen(HttpResult.State state, Consumer<HttpResult.State> logic) {
        if (isHttpStateNotHealthy(state)) {
            logic.accept(state);
        }
    }

}
