package cn.srd.itcp.sugar.security.sa.token.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties for Sugar Security Sa-Token
 *
 * @author wjm
 * @date 2022-07-16 18:16:22
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "sugar.sa-token")
public class SugarSaTokenProperties {

    /**
     * 拦截器相关属性
     */
    private Interceptor interceptor;

    @Getter
    @Setter
    public static class Interceptor {

        /**
         * 是否开启拦截（全局）
         */
        private Boolean enable;
        
    }

}
