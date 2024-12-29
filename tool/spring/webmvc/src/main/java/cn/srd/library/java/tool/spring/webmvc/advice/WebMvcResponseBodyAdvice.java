package cn.srd.library.java.tool.spring.webmvc.advice;

import cn.srd.library.java.contract.constant.web.HttpStatus;
import cn.srd.library.java.contract.model.protocol.WebResponse;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.webmvc.support.SpringWebMvcs;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Set;
import java.util.function.Predicate;

import static cn.srd.library.java.contract.model.protocol.WebResponse.success;

/**
 * spring webmvc default response body advice
 *
 * @author wjm
 * @since 2020-06-13 20:05
 */
@RestControllerAdvice
public class WebMvcResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return Unsupported.support(methodParameter) && Comparators.notEquals(converterType, StringHttpMessageConverter.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType, @NonNull Class<? extends HttpMessageConverter<?>> httpMessageConverter, @NonNull ServerHttpRequest serverHttpRequest, @NonNull ServerHttpResponse serverHttpResponse) {
        if (serverHttpResponse instanceof ServletServerHttpResponse servletServerHttpResponse && Comparators.notEquals(HttpStatus.OK.getStatus(), servletServerHttpResponse.getServletResponse().getStatus())) {
            return body;
        }
        return body instanceof WebResponse ? body : success(body);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Unsupported {

        private static final Set<Class<?>> RESPONSE_BODY_MODEL_TYPES = Collections.newConcurrentHashSet();

        private static final Set<Class<?>> CONTROLLER_TYPES = Collections.newConcurrentHashSet();

        private static final Set<String> CONTAIN_MATCH_URIS = Collections.newConcurrentHashSet();

        private static final Set<String> FULL_MATCH_URIS = Collections.newConcurrentHashSet();

        private static final Set<Predicate<MethodParameter>> PREDICATIONS = Collections.newConcurrentHashSet();

        public static void registerResponseBodyModels(Class<?>... unsupportedResponseBodyModels) {
            RESPONSE_BODY_MODEL_TYPES.addAll(Collections.ofHashSet(unsupportedResponseBodyModels));
        }

        public static void registerControllers(Class<?>... unsupportedControllers) {
            CONTROLLER_TYPES.addAll(Collections.ofHashSet(unsupportedControllers));
        }

        public static void registerContainMatchURIs(String... containMatchURIs) {
            CONTAIN_MATCH_URIS.addAll(Collections.ofHashSet(containMatchURIs));
        }

        public static void registerFullMatchURIs(String... fullMatchURIs) {
            FULL_MATCH_URIS.addAll(Collections.ofHashSet(fullMatchURIs));
        }

        @SafeVarargs
        public static void registerPredications(Predicate<MethodParameter>... unsupportedPredications) {
            PREDICATIONS.addAll(Collections.ofHashSet(unsupportedPredications));
        }

        private static boolean support(MethodParameter methodParameter) {
            String requestURI = SpringWebMvcs.getHttpServletRequest().getRequestURI();
            return RESPONSE_BODY_MODEL_TYPES.stream().noneMatch(unsupportedType -> Comparators.equals(unsupportedType, methodParameter.getParameterType())) &&
                    CONTROLLER_TYPES.stream().noneMatch(unsupportedType -> Comparators.equals(unsupportedType, methodParameter.getContainingClass())) &&
                    CONTAIN_MATCH_URIS.stream().noneMatch(containMatchURI -> Strings.containsAny(requestURI, containMatchURI)) &&
                    FULL_MATCH_URIS.stream().noneMatch(fullMatchURI -> Comparators.equals(requestURI, fullMatchURI)) &&
                    PREDICATIONS.stream().noneMatch(predication -> predication.test(methodParameter));
        }

    }

}