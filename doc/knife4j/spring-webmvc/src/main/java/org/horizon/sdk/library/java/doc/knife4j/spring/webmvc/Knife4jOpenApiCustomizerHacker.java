package org.horizon.sdk.library.java.doc.knife4j.spring.webmvc;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.github.xiaoymin.knife4j.core.conf.ExtensionsConstants;
import com.github.xiaoymin.knife4j.core.conf.GlobalConstants;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties;
import com.github.xiaoymin.knife4j.spring.configuration.Knife4jSetting;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

/**
 * copy from {@link com.github.xiaoymin.knife4j.spring.extension.Knife4jOpenApiCustomizer}, modify code line 62 to 63: Nil.isEmpty
 *
 * @author wjm
 * @since 2025-03-10 04:55
 */
@Slf4j
@AutoConfigureBefore(Knife4jSpringWebMVCAutoConfigurer.class)
public class Knife4jOpenApiCustomizerHacker implements GlobalOpenApiCustomizer {

    @Override
    public void customise(OpenAPI openApi) {
        Knife4jProperties knife4jProperties = Springs.getBean(Knife4jProperties.class);
        log.debug("Knife4j OpenApiCustomizer");
        if (knife4jProperties.isEnable()) {
            Knife4jSetting setting = knife4jProperties.getSetting();
            OpenApiExtensionResolver openApiExtensionResolver = new OpenApiExtensionResolver(setting, knife4jProperties.getDocuments());
            // 解析初始化
            openApiExtensionResolver.start();
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put(GlobalConstants.EXTENSION_OPEN_SETTING_NAME, setting);
            objectMap.put(GlobalConstants.EXTENSION_OPEN_MARKDOWN_NAME, openApiExtensionResolver.getMarkdownFiles());
            openApi.addExtension(GlobalConstants.EXTENSION_OPEN_API_NAME, objectMap);
            addOrderExtension(openApi);
        }
    }

    /**
     * 往OpenAPI内tags字段添加x-order属性
     *
     * @param openApi openApi
     */
    private void addOrderExtension(OpenAPI openApi) {
        SpringDocConfigProperties properties = Springs.getBean(SpringDocConfigProperties.class);
        // if (CollectionUtils.isEmpty(properties.getGroupConfigs())) {
        if (Nil.isEmpty(properties.getGroupConfigs())) {
            return;
        }
        // 获取包扫描路径
        Set<String> packagesToScan =
                properties.getGroupConfigs().stream()
                        .map(SpringDocConfigProperties.GroupConfig::getPackagesToScan)
                        .filter(toScan -> !CollectionUtils.isEmpty(toScan))
                        .flatMap(List::stream)
                        .collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(packagesToScan)) {
            return;
        }
        // 扫描包下被ApiSupport注解的RestController Class
        Set<Class<?>> classes =
                packagesToScan.stream()
                        .map(packageToScan -> scanPackageByAnnotation(packageToScan, RestController.class))
                        .flatMap(Set::stream)
                        .filter(clazz -> clazz.isAnnotationPresent(ApiSupport.class))
                        .collect(Collectors.toSet());
        if (!CollectionUtils.isEmpty(classes)) {
            // ApiSupport oder值存入tagSortMap<Tag.name,ApiSupport.order>
            Map<String, Integer> tagOrderMap = new HashMap<>();
            classes.forEach(
                    clazz -> {
                        Tag tag = getTag(clazz);
                        if (Objects.nonNull(tag)) {
                            ApiSupport apiSupport = clazz.getAnnotation(ApiSupport.class);
                            tagOrderMap.putIfAbsent(tag.name(), apiSupport.order());
                        }
                    });
            // 往openApi tags字段添加x-order增强属性
            if (openApi.getTags() != null) {
                openApi
                        .getTags()
                        .forEach(
                                tag -> {
                                    if (tagOrderMap.containsKey(tag.getName())) {
                                        tag.addExtension(
                                                ExtensionsConstants.EXTENSION_ORDER, tagOrderMap.get(tag.getName()));
                                    }
                                });
            }
        }
    }

    private Tag getTag(Class<?> clazz) {
        // 从类上获取
        Tag tag = clazz.getAnnotation(Tag.class);
        if (Objects.isNull(tag)) {
            // 从接口上获取
            Class<?>[] interfaces = clazz.getInterfaces();
            if (ArrayUtils.isNotEmpty(interfaces)) {
                for (Class<?> interfaceClazz : interfaces) {
                    Tag anno = interfaceClazz.getAnnotation(Tag.class);
                    if (Objects.nonNull(anno)) {
                        tag = anno;
                        break;
                    }
                }
            }
        }
        return tag;
    }

    private Set<Class<?>> scanPackageByAnnotation(
            String packageName, final Class<? extends Annotation> annotationClass) {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
        Set<Class<?>> classes = new HashSet<>();
        for (BeanDefinition beanDefinition : scanner.findCandidateComponents(packageName)) {
            try {
                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                classes.add(clazz);
            } catch (ClassNotFoundException ignore) {

            }
        }
        return classes;
    }

}