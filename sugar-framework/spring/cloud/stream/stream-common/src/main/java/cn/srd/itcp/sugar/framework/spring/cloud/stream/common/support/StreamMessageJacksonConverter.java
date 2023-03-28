package cn.srd.itcp.sugar.framework.spring.cloud.stream.common.support;

import cn.srd.itcp.sugar.component.convert.all.core.Converts;
import cn.srd.itcp.sugar.framework.spring.cloud.stream.common.core.EnableStreamMessageJacksonConverter;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.*;
import jakarta.annotation.PostConstruct;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

import java.util.*;

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
    @PostConstruct
    public void init() {
        List<Class<?>> supportedClasses = new ArrayList<>();

        Set<Class<? extends StreamMessageConvertWithJacksonSupporter>> supporters = SpringsUtil.scanPackagesBySuper(StreamMessageConvertWithJacksonSupporter.class);
        supporters.forEach(supporter -> {
            supportedClasses.addAll(ReflectsUtil.newInstance(supporter).getSupportedClasses());
        });

        Set<Class<?>> classesWithEnableStreamMessageJacksonConverter = SpringsUtil.scanPackageByAnnotation(EnableStreamMessageJacksonConverter.class);
        classesWithEnableStreamMessageJacksonConverter.forEach(classWithEnableStreamMessageJacksonConverter -> {
            EnableStreamMessageJacksonConverter streamMessageJacksonConverter = AnnotationsUtil.getAnnotation(classWithEnableStreamMessageJacksonConverter, EnableStreamMessageJacksonConverter.class);
            supportedClasses.addAll(List.of(streamMessageJacksonConverter.supportedClasses()));
        });

        supportedClasses.forEach(supportedClass -> SUPPORTED_NAMING_CLASSES.put(ClassesUtil.getClassSimpleName(supportedClass), supportedClass));
        supportedClasses.forEach(supportedClass -> SUPPORTED_LOWERCASE_NAMING_CLASSES.put(StringsUtil.lowerCase(ClassesUtil.getClassSimpleName(supportedClass)), supportedClass));
    }

    @Override
    protected boolean supports(@NonNull Class<?> clazz) {
        return Objects.isNotNull(SUPPORTED_NAMING_CLASSES.get(ClassesUtil.getClassSimpleName(clazz)));
    }

    @Override
    protected Object convertFromInternal(Message<?> message, @NonNull Class<?> targetClass, Object conversionHint) {
        return Converts.withJackson().toBean((byte[]) message.getPayload(), SUPPORTED_LOWERCASE_NAMING_CLASSES.get(((MimeType) message.getHeaders().get(MessageHeaders.CONTENT_TYPE)).getSubtype()));
    }

}