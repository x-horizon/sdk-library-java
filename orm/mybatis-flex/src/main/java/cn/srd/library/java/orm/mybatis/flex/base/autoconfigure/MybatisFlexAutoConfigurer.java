package cn.srd.library.java.orm.mybatis.flex.base.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@Slf4j
// @Order(Ordered.HIGHEST_PRECEDENCE)
@AutoConfiguration
// @Import({PostgresqlMetadataInjector.class})
// @EnableConfigurationProperties(OrmMybatisPlusProperties.class)
// @AutoConfigureBefore(MybatisFlexAutoConfiguration.class)
// @EnableConfigurationProperties(RedisCacheProperties.class)
public class MybatisFlexAutoConfigurer {

    // @Bean
    // @ConditionalOnBean(EnableMybatisFlexCustomizerFlag.class)
    // public MybatisFlexCustomizer mybatisFlexCapableCustomizer() {
    //     return new MybatisFlexCustomizer();
    // }
    //
    // 在Spring中，可以通过自定义注解和编写自定义注解处理器来实现对于一个注解的两个字段都设置了值的时候，提示报错或者编译报错的功能。
    //
    // 首先，你需要定义一个自定义注解，使用@Target和@Retention注解来指定注解的使用范围和生命周期。然后，在注解中定义两个字段，并使用@Constraint注解来指定对这两个字段的校验规则。
    //
    // 接下来，你需要编写一个自定义注解处理器，实现javax.annotation.processing.Processor接口。在处理器中，你可以通过javax.annotation.processing.RoundEnvironment对象获取到被注解标记的元素，并对这些元素进行校验。如果发现两个字段都设置了值，你可以抛出一个自定义的异常或者使用javax.annotation.processing.Messager对象输出编译错误信息。
    //
    // 最后，你需要在Spring配置文件中配置自定义注解处理器，让Spring在编译时自动调用处理器进行校验。
    //
    // 以下是一个简单的示例代码，演示了如何实现这个功能：

    // // 定义自定义注解
    // @Target(ElementType.TYPE)
    // @Retention(RetentionPolicy.RUNTIME)
    // public @interface MyAnnotation {
    //     String field1() default "";
    //     String field2() default "";
    // }
    //
    // // 编写自定义注解处理器
    // @SupportedAnnotationTypes("com.example.MyAnnotation")
    // @SupportedSourceVersion(SourceVersion.RELEASE_8)
    // public class MyAnnotationProcessor extends AbstractProcessor {
    //     @Override
    //     public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    //         for (Element element : roundEnv.getElementsAnnotatedWith(MyAnnotation.class)) {
    //             MyAnnotation annotation = element.getAnnotation(MyAnnotation.class);
    //             String field1 = annotation.field1();
    //             String field2 = annotation.field2();
    //
    //             if (!field1.isEmpty() && !field2.isEmpty()) {
    //                 // 抛出异常或输出编译错误信息
    //                 throw new RuntimeException("两个字段都设置了值");
    //             }
    //         }
    //         return true;
    //     }
    // }
    //
    // // 在Spring配置文件中配置自定义注解处理器
    // @Configuration
    // public class MyAnnotationProcessorConfig {
    //     @Bean
    //     public MyAnnotationProcessor myAnnotationProcessor() {
    //         return new MyAnnotationProcessor();
    //     }
    // }

}
