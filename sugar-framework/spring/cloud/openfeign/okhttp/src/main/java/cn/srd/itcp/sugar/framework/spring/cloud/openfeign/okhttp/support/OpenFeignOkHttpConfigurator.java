package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.okhttp.support;

import cn.srd.itcp.sugar.tool.web.ResponseModel;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * open feign okhttp 配置器
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
public interface OpenFeignOkHttpConfigurator {

    /**
     * get singleton {@link OkHttpClient.Builder}
     */
    OkHttpClient.Builder OKHTTP_CLIENT_BUILDER_INSTANCE = new OkHttpClient.Builder();

    /**
     * 要解析的 {@link ResponseModel} 集合
     */
    List<Class<? extends ResponseModel<?>>> RESPONSE_MODELS_TO_PARSE = new ArrayList<>();

    /**
     * 要解析的 {@link ResponseModel} 类名
     */
    List<String> RESPONSE_MODEL_NAMES_TO_PARSE = new ArrayList<>();

}