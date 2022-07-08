package cn.srd.itcp.sugar.security.sa.token.core;

import cn.dev33.satoken.annotation.SaCheckLogin;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.*;

/**
 * <pre>
 *   该注解为：登录后可进入被该注解标记了的方法，如：
 *
 *     &#064;{@link SaLogin}
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
@SaCheckLogin
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SaLogin {

}
