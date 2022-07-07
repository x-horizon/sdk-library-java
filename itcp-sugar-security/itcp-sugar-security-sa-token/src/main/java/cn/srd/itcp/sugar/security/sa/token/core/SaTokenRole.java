package cn.srd.itcp.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 登录认证：必须具有指定的角色标识才能进入该方法；
 * <pre>
 *   可标记在函数上；
 *   可标记在类上，效果等同于标注在此类的所有方法上）；
 * </pre>
 *
 * @author wjm
 * @date 2022-07-07
 */
@SaCheckRole(type = SaTokens.USER_TYPE)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaTokenRole {

    /**
     * 需要校验的角色标识
     *
     * @return 需要校验的角色标识
     */
    @AliasFor(annotation = SaCheckRole.class)
    String[] value() default {};

    /**
     * 验证模式：AND | OR，默认 AND
     *
     * @return 验证模式
     */
    @AliasFor(annotation = SaCheckRole.class)
    SaMode mode() default SaMode.AND;

}
