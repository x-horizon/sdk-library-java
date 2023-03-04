package cn.srd.itcp.sugar.framework.spring.cloud.openfeign.support;

import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Open Feign Ok Http
 *
 * @author wjm
 * @since 2023-03-04 16:48:19
 */
@AutoConfiguration
public class SugarOpenFeignOkHttpAutoConfiguration {

    @Bean
    public OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder().addInterceptor(new OpenFeignOkHttpResponseHandleWebResponseInterceptor());
    }

}
