// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.openfeign.autoconfigue;

import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.web.openfeign.EnableFeignClientResponseModelResolver;
import cn.srd.library.java.web.openfeign.FeignClientResponseInterceptor;
import cn.srd.library.java.web.openfeign.FeignClientResponseModelCache;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.Set;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Web Open Feign
 *
 * @author wjm
 * @since 2023-03-04 16:48
 */
@AutoConfiguration
@ConditionalOnProperty(name = "spring.cloud.openfeign.okhttp.enabled", havingValue = BooleanConstant.TRUE_STRING_LOWER_CASE)
public class OpenFeignAutoConfigurer {

    /**
     * get singleton {@link OkHttpClient.Builder}
     */
    private static final OkHttpClient.Builder OKHTTP_CLIENT_BUILDER_INSTANCE = new OkHttpClient.Builder();

    /**
     * rebuild {@link OkHttpClient.Builder}
     *
     * @return 装配对象
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Bean
    public OkHttpClient.Builder okHttpClientBuilder() {
        Set<Class> resolvedModels = Annotations.getAnnotationNestValues(EnableFeignClientResponseModelResolver.class, Class[].class);
        if (Nil.isNotEmpty(resolvedModels)) {
            FeignClientResponseModelCache.set(resolvedModels);
            OKHTTP_CLIENT_BUILDER_INSTANCE.addInterceptor(new FeignClientResponseInterceptor());
        }
        return OKHTTP_CLIENT_BUILDER_INSTANCE;
    }

}