package cn.srd.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：同时拥有所有角色时可进入被该注解标记了的方法，如：
 *
 *     // 同时拥有 "user1"、"user2" 角色时可进入 test 方法
 *     &#064;{@link HasAllRoles}({"user1","user2"})
 *     &#064;PostMapping
 *     public void test(Test test) {
 *     }
 *
 *   可标记在函数上；
 *   可标记在类上，效果等同于标记在此类的所有方法上；
 * </pre>
 *
 * @author wjm
 * @since 2022-07-07
 */
@SaCheckRole(mode = SaMode.AND)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasAllRoles {

    /**
     * 需要校验的权限码，如：@{@link HasAllRoles}("admin")
     *
     * @return 需要校验的角色标识
     */
    @AliasFor(annotation = SaCheckRole.class, attribute = "value")
    String[] value() default {};

}
