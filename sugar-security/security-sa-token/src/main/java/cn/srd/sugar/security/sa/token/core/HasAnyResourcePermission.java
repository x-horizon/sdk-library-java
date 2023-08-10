package cn.srd.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：拥有任一权限时可进入被该注解标记了的方法，如：
 *
 *     // 拥有 "system:user:save"、"system:user:update" 任一权限时可进入 test 方法
 *     &#064;{@link HasAnyResourcePermission}({"system:user:save","system:user:update"})
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
@SaCheckPermission(mode = SaMode.OR)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasAnyResourcePermission {

    /**
     * 需要校验的权限码，如：@{@link HasAnyResourcePermission}("system:param:save")
     *
     * @return 需要校验的权限码
     */
    @AliasFor(annotation = SaCheckPermission.class, attribute = "value")
    String[] value() default {};

    /**
     * 在权限认证不通过时的次要选择
     * <pre>
     * 例1：@{@link HasAllResourcePermissions}(value = "system:param:save", orRole = "admin")，表示本次请求需要拥有 system:param:save 权限或 admin 角色时，可通过校验；
     * 例2：@{@link HasAllResourcePermissions}(value = "system:param:save", orRole = "admin", "manager", "staff")，表示本次请求需要拥有 system:param:save 权限或 admin、manager、staff 中任一角色时，可通过校验；
     * 例3：@{@link HasAllResourcePermissions}(value = "system:param:save", orRole = {"admin, manager, staff"})，表示本次请求需要拥有 system:param:save 权限或同时拥有 admin、manager、staff 这三个角色时，可通过校验；
     * </pre>
     *
     * @return 需要校验的权限码
     */
    @AliasFor(annotation = SaCheckPermission.class, attribute = "orRole")
    String[] orRole() default {};

}
