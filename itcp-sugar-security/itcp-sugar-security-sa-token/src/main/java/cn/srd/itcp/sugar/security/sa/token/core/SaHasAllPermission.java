package cn.srd.itcp.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：同时拥有所有权限时可进入被该注解标记了的方法，如：
 *
 *     // 同时拥有 "system:user:add"、"system:user:edit" 权限时可进入 test 方法
 *     &#064;{@link SaHasAllPermission}({"system:user:add","system:user:edit"})
 *     &#064;{@link PostMapping}
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
@SaCheckPermission(mode = SaMode.AND)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaHasAllPermission {

    /**
     * 需要校验的权限码，如：@{@link SaHasAllPermission}("system:config:add")
     *
     * @return 需要校验的权限码
     */
    @AliasFor(annotation = SaCheckPermission.class)
    String[] value() default {};

}
