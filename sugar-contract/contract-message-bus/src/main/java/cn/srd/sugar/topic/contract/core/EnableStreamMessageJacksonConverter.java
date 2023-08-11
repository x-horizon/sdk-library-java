package cn.srd.sugar.topic.contract.core;

import cn.srd.sugar.topic.contract.support.StreamMessageJacksonConverter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 {@link StreamMessageJacksonConverter}
 *
 * @author wjm
 * @since 2023-03-28 19:41:33
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({StreamMessageJacksonConverter.class})
public @interface EnableStreamMessageJacksonConverter {

    /**
     * 获取可支持使用 Jackson 转换器转换的类
     *
     * @return 可支持使用 Jackson 转换器转换的类
     */
    Class<?>[] supportedClasses() default {};

}
