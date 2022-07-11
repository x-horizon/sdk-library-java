package cn.srd.itcp.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：拥有任一角色时可进入被该注解标记了的方法，如：
 *
 *     // 拥有 "user1"、"user2" 任一角色时可进入 test 方法
 *     &#064;{@link HasAnyRole}({"user1","user2"})
 *     &#064;PostMapping
 *     public void test(Test test) {
 *     }
 *
 *   可标记在函数上；
 *   可标记在类上，效果等同于标记在此类的所有方法上；
 * </pre>
 *
 * @author wjm
 * @date 2022-07-07
 */
@SaCheckRole(mode = SaMode.OR)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasAnyRole {

    /**
     * 需要校验的权限码，如：@{@link HasAnyRole}("admin")
     *
     * @return 需要校验的角色标识
     */
    @AliasFor(annotation = SaCheckRole.class, attribute = "value")
    String[] value() default {};

}
