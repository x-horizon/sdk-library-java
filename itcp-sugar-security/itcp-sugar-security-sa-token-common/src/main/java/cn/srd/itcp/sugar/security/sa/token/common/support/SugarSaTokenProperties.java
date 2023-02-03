package cn.srd.itcp.sugar.security.sa.token.common.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for Sugar Security Sa-Token
 *
 * @author wjm
 * @since 2022-07-16 18:16:22
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "sugar.sa-token")
public class SugarSaTokenProperties {

    /**
     * 拦截器相关属性
     */
    private Interceptor interceptor;

    /**
     * Properties for interceptor
     */
    @Getter
    @Setter
    public static class Interceptor {

        /**
         * 是否开启拦截（全局），需注意：即便关闭了全局拦截，但代码逻辑里若需要获取有关联到 {@link cn.dev33.satoken.stp.StpUtil#getLoginId()} 的相关方法，此时同样会抛出未通过登录认证的相关异常；
         */
        private Boolean enable;

    }

}
