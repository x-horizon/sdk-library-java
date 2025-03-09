package org.horizon.sdk.library.java.doc.knife4j.spring.webmvc;

import com.github.xiaoymin.knife4j.spring.extension.Knife4jOpenApiCustomizer;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import org.horizon.sdk.library.java.tool.spring.webmvc.advice.WebMvcResponseBodyAdvice;
import org.springdoc.api.AbstractOpenApiResource;
import org.springdoc.core.customizers.SpringDocCustomizers;
import org.springdoc.webmvc.api.MultipleOpenApiActuatorResource;
import org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource;
import org.springdoc.webmvc.api.OpenApiActuatorResource;
import org.springdoc.webmvc.api.OpenApiWebMvcResource;
import org.springdoc.webmvc.ui.SwaggerConfigResource;
import org.springdoc.webmvc.ui.SwaggerUiHome;
import org.springdoc.webmvc.ui.SwaggerWelcomeActuator;
import org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Doc Knife4j Spring WebMVC
 *
 * @author wjm
 * @since 2023-11-27 17:13
 */
@AutoConfiguration
public class Knife4jSpringWebMVCAutoConfigurer implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        WebMvcResponseBodyAdvice.Unsupported.registerControllers(
                SwaggerUiHome.class,
                SwaggerWelcomeWebMvc.class,
                SwaggerWelcomeActuator.class,
                SwaggerConfigResource.class,
                OpenApiWebMvcResource.class,
                OpenApiActuatorResource.class,
                MultipleOpenApiWebMvcResource.class,
                MultipleOpenApiActuatorResource.class
        );

        // TODO wjm 20250310 current knife4j version 4.5.0 incompatible with springdoc-openapi version 2.8.3
        // TODO wjm 20250310 the key point is that the code {properties.getGroupConfigs()} in Knife4jOpenApiCustomizer.class code line 75 return data type expected to List but now is Set
        // TODO wjm 20250310 need to hack this, see Knife4jOpenApiCustomizerHacker
        SpringDocCustomizers hackSpringDocCustomizers = Reflects.getFieldValue(Springs.getBean(AbstractOpenApiResource.class), "springDocCustomizers");
        hackSpringDocCustomizers.getOpenApiCustomizers().ifPresent(openApiCustomizers -> openApiCustomizers.removeIf(openApiCustomizer -> Comparators.equals(openApiCustomizer.getClass(), Knife4jOpenApiCustomizer.class)));
    }

}