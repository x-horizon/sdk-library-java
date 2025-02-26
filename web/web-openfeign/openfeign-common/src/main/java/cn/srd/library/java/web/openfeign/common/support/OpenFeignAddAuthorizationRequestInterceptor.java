package cn.srd.library.java.web.openfeign.common.support;

import cn.srd.library.java.tool.lang.core.object.Objects;
import cn.srd.library.java.tool.spring.webmvc.core.SpringsWebMVCUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;

/**
 * open feign okhttp 请求拦截器：为请求头添加 {@link HttpHeaders#AUTHORIZATION}
 *
 * @author wjm
 * @since 2023-03-06 15:07:11
 */
public class OpenFeignAddAuthorizationRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = SpringsWebMVCUtil.getHttpServletRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNotBlank(token)) {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, token);
        }
    }

}