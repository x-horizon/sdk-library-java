package cn.srd.library.java.contract.component.message.spring.support;

import cn.srd.library.java.contract.component.message.spring.EnableStreamMessageJacksonConverter;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.Annotations;
import cn.srd.library.java.tool.spring.contract.Classes;
import jakarta.annotation.PostConstruct;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于在管道中流动数据的 Jackson 转换器
 * <pre>
 * 1、必须显式标记 {@link EnableStreamMessageJacksonConverter}，该类才生效；
 * 2、可在 {@link EnableStreamMessageJacksonConverter#supportedClasses()} 中添加类，这些模型在管道流时会使用 Jackson 处理器进行转换；
 * 3、可实现 {@link StreamMessageConvertWithJacksonSupporter#getSupportedClasses()} 来添加类，这些模型在管道流时会使用 Jackson 处理器进行转换；
 * 4、同时，在配置文件中，需要指定 bindings 中每个 binding 的 content-type 参数，如：
 *
 *    spring:
 *      cloud:
 *        function:
 *          definition: consume
 *        stream:
 *          bindings:
 *            consume-in-0:
 *              destination: topic-name
 *              content-type: application/ModelName #此处，配置 application/要转换的类名
 *              group: consumer-group-id
 *
 * </pre>
 *
 * @author wjm
 * @since 2023-03-28 19:41:33
 */
public class StreamMessageJacksonConverter extends AbstractMessageConverter {

    /**
     * 支持转换的类名 mapping 类
     */
    private static final Map<String, Class<?>> SUPPORTED_NAMING_CLASSES = new HashMap<>();

    /**
     * 支持转换的类名（小写） mapping 类
     */
    private static final Map<String, Class<?>> SUPPORTED_LOWERCASE_NAMING_CLASSES = new HashMap<>();

    /**
     * init
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @PostConstruct
    public void init() {
        List<Class> supportedClasses = new ArrayList<>();
        Classes.scanBySuper(StreamMessageConvertWithJacksonSupporter.class).forEach(supporter -> supportedClasses.addAll(Reflects.newInstance(supporter).getSupportedClasses()));
        supportedClasses.addAll(Annotations.getAnnotationNestValue(EnableStreamMessageJacksonConverter.class, Class[].class, "supportedClasses"));
        supportedClasses.forEach(supportedClass -> SUPPORTED_NAMING_CLASSES.put(Classes.getClassSimpleName(supportedClass), supportedClass));
        supportedClasses.forEach(supportedClass -> SUPPORTED_LOWERCASE_NAMING_CLASSES.put(Strings.lowerCase(Classes.getClassSimpleName(supportedClass)), supportedClass));
    }

    @Override
    protected boolean supports(@NonNull Class<?> clazz) {
        return Nil.isNotNull(SUPPORTED_NAMING_CLASSES.get(Classes.getClassSimpleName(clazz)));
    }

    @Override
    protected Object convertFromInternal(Message<?> message, @NonNull Class<?> targetClass, Object conversionHint) {
        return Converts.withJackson().toBean((byte[]) message.getPayload(), targetClass);
    }

    @Override
    protected Object convertToInternal(Object payload, MessageHeaders headers, Object conversionHint) {
        return Converts.withJackson().toString(payload);
    }

}