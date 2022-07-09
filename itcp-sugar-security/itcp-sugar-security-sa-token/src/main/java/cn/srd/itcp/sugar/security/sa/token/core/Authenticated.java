package cn.srd.itcp.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckLogin;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：被该注解标记了的方法登录后可进入，如：
 *
 *     &#064;{@link Authenticated}
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
@SaCheckLogin
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authenticated {

}
