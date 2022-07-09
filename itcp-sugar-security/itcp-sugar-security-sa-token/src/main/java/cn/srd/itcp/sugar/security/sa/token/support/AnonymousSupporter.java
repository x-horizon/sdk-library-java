package cn.srd.itcp.sugar.security.sa.token.support;

import cn.hutool.core.util.ReUtil;
import cn.srd.itcp.sugar.security.sa.token.core.Anonymous;
import cn.srd.itcp.sugar.tools.constant.StringPool;
import cn.srd.itcp.sugar.tools.core.SpringsUtil;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author wjm
 * @date 2022-07-07
 * @see Anonymous
 * @see SaTokenConfig#addInterceptors(InterceptorRegistry)
 */
public class AnonymousSupporter implements InitializingBean {

    /**
     * 收集标记了 {@link Anonymous} 的类或方法对应的所有接口，这些接口无需鉴权，用于匿名访问；
     */
    @Getter
    private static List<String> saAnonymousUrlsToExcludes = new ArrayList<>();

    /**
     * 用于将标记了 {@link PathVariable} 的 URI 中的占用符替换为 * 的正则表达式
     */
    private static final Pattern URI_PATH_VARIABLE_REPLACE_TO_ASTERISK_PATTERN = Pattern.compile("\\{(.*?)}");

    @Override
    public void afterPropertiesSet() {
        SpringsUtil.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().forEach((requestMappingInfo, requestMappingMethod) -> {
            // 获取标记了 SaAnonymous 注解的方法，将其 URI 中的占位符替换为 *
            Optional.ofNullable(AnnotationUtils.findAnnotation(requestMappingMethod.getMethod(), Anonymous.class)).ifPresent(anonymous -> {
                Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
                patterns.forEach(url -> saAnonymousUrlsToExcludes.add(ReUtil.replaceAll(url, URI_PATH_VARIABLE_REPLACE_TO_ASTERISK_PATTERN, StringPool.ASTERISK)));
            });
            // 获取标记了 SaAnonymous 注解的类，将其 URI 中的占位符替换为 *
            Optional.ofNullable(AnnotationUtils.findAnnotation(requestMappingMethod.getBeanType(), Anonymous.class)).ifPresent(anonymous -> {
                Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
                patterns.forEach(url -> saAnonymousUrlsToExcludes.add(ReUtil.replaceAll(url, URI_PATH_VARIABLE_REPLACE_TO_ASTERISK_PATTERN, StringPool.ASTERISK)));
            });
        });
        saAnonymousUrlsToExcludes = Collections.unmodifiableList(saAnonymousUrlsToExcludes);
    }

}
