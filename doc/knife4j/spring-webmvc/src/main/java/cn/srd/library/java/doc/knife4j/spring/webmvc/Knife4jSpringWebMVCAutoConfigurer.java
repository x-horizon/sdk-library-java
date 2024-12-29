package cn.srd.library.java.doc.knife4j.spring.webmvc;

import cn.srd.library.java.tool.spring.webmvc.advice.WebMvcResponseBodyAdvice;
import cn.srd.library.java.tool.spring.webmvc.autoconfigure.WebMvcResponseBodyAdviceRegistrar;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Doc Knife4j Spring WebMVC
 *
 * @author wjm
 * @since 2023-11-27 17:13
 */
@AutoConfiguration
@ConditionalOnBean(WebMvcResponseBodyAdviceRegistrar.class)
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
    }

}