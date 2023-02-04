package cn.srd.itcp.sugar.security.sa.token.tmp;

import cn.dev33.satoken.context.SaTokenContext;
import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.servlet.model.SaRequestForServlet;
import cn.dev33.satoken.servlet.model.SaResponseForServlet;
import cn.dev33.satoken.servlet.model.SaStorageForServlet;
import cn.dev33.satoken.spring.SaPathMatcherHolder;

/**
 * TODO wjm copy from {@link cn.dev33.satoken.spring.SaTokenContextForSpring}，修改 javax 为 Jakarta，暂时用于兼容 JDK 17，后续官方版本支持后去掉
 *
 * @author wjm
 * @since 2023-01-28 00:29:11
 */
public class SaTokenContextForSpringMVC implements SaTokenContext {

    /**
     * 获取当前请求的Request对象
     */
    @Override
    public SaRequest getRequest() {
        return new SaRequestForServlet(SpringMVCUtil.getRequest());
    }

    /**
     * 获取当前请求的Response对象
     */
    @Override
    public SaResponse getResponse() {
        return new SaResponseForServlet(SpringMVCUtil.getResponse());
    }

    /**
     * 获取当前请求的 [存储器] 对象
     */
    @Override
    public SaStorage getStorage() {
        return new SaStorageForServlet(SpringMVCUtil.getRequest());
    }

    /**
     * 校验指定路由匹配符是否可以匹配成功指定路径
     */
    @Override
    public boolean matchPath(String pattern, String path) {
        return SaPathMatcherHolder.getPathMatcher().match(pattern, path);
    }

    /**
     * 此上下文是否有效
     */
    @Override
    public boolean isValid() {
        return SpringMVCUtil.isWeb();
    }

}
