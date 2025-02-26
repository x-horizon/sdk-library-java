package cn.srd.library.java.security.hsweb.core;

import org.hswebframework.web.aop.MethodInterceptorContext;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.basic.aop.AopMethodAuthorizeDefinitionCustomizerParser;
import org.hswebframework.web.authorization.basic.aop.DefaultAopMethodAuthorizeDefinitionParser;
import org.hswebframework.web.authorization.basic.define.EmptyAuthorizeDefinition;
import org.hswebframework.web.authorization.define.AopAuthorizeDefinition;
import org.hswebframework.web.authorization.define.AuthorizeDefinition;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <pre>
 * 通过拦截 {@link Authorize#ignore()} 属性来自定义何时要让 {@link Authorize#ignore()} 生效；
 * 若实现了该接口并实现了 {@link #ignoreAuthorize()}，此时：
 *  1、如果 {@link #ignoreAuthorize()} 被设置为 true，此时将会应用 {@link Authorize#ignore()} 属性，例如：
 *     ①、{@link #ignoreAuthorize()} 为 true，{@link Authorize#ignore()} 也为 true，此时将会进行权限控制；
 *     ②、{@link #ignoreAuthorize()} 为 true，{@link Authorize#ignore()} 也为 false，此时将不会进行权限控制；
 *  2、如果 {@link #ignoreAuthorize()} 被设置为 false，此时将忽略 {@link Authorize#ignore()} 属性，无论 {@link Authorize#ignore()} 如何设置，都不会进行权限控制；
 * 这么做的原因是：
 *  1、不同环境中需要的权限控制不同，例如开发环境无需每个接口都进行登录校验；
 *  2、{@link Authorize#ignore()} 属性无法在运行时动态指定；
 * </pre>
 *
 * @author wjm
 * @see Authorize
 * @see AopMethodAuthorizeDefinitionCustomizerParser
 * @see AopAuthorizeDefinition
 * @see EmptyAuthorizeDefinition
 * @see DefaultAopMethodAuthorizeDefinitionParser#setParserCustomizers(List)
 * @since 2022/6/18 19:17
 */
public interface AuthorizeIgnoreInterceptor extends AopMethodAuthorizeDefinitionCustomizerParser {

    /**
     * 是否需要忽略权限控制
     *
     * @return 是否需要忽略权限控制
     */
    boolean ignoreAuthorize();

    /**
     * 解析权限控制定义：如果返回 {@link EmptyAuthorizeDefinition} 表示不进行权限控制，如果返回 null 表示使用默认的权限定义
     *
     * @param target  目标控制类
     * @param method  目标控制函数
     * @param context 函数拦截上下文
     * @return 权限控制定义对象
     */
    @Override
    default AuthorizeDefinition parse(Class<?> target, Method method, MethodInterceptorContext context) {
        return ignoreAuthorize() ? EmptyAuthorizeDefinition.instance : null;
    }

}
