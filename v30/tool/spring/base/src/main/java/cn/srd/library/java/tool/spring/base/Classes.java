// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.spring.base;

import cn.srd.library.java.contract.constant.annotation.AnnotationConstant;
import cn.srd.library.java.contract.constant.classes.ClassConstant;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.annotation.Annotations;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.BasePackagePath;
import cn.srd.library.java.tool.lang.text.Strings;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * toolkit for class
 *
 * @author wjm
 * @since 2023-10-06 10:48
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Classes extends cn.srd.library.java.tool.lang.object.Classes {

    private static final PathMatchingResourcePatternResolver PATH_MATCHING_RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();

    public static Class<?> ofName(String className) {
        return ofName(className, getDefaultClassLoader());
    }

    public static Class<?> ofName(String className, ClassLoader classLoader) {
        return ClassUtils.resolveClassName(className, classLoader);
    }

    public static ClassLoader getDefaultClassLoader() {
        return ClassUtils.getDefaultClassLoader();
    }

    /**
     * <pre>
     * convert class name to resource path
     *
     * for example:
     * the input is "Classes", the output is "Classes".
     * the input is "cn.library.java.tool.spring.base.Classes", the output is "cn/library/java/tool/spring/base/Classes".
     * </pre>
     *
     * @param className the class name
     * @return the resource path
     * @see ClassUtils#convertClassNameToResourcePath(String)
     */
    public static String convertClassNameToResourcePath(String className) {
        return ClassUtils.convertClassNameToResourcePath(className);
    }

    /**
     * <pre>
     * convert class name to class path
     *
     * for example:
     * the input is "Classes", the output is "classpath*:Classes".
     * the input is "cn.library.java.tool.spring.base.Classes", the output is "classpath*:cn/library/java/tool/spring/base/Classes".
     * </pre>
     *
     * @param className the class name
     * @return the class path
     */
    public static String convertClassNameToClassPath(String className) {
        return ClassConstant.CLASS_PATH_PREFIX + convertClassNameToResourcePath(className);
    }

    /**
     * <pre>
     * convert resource path to class name
     *
     * for example:
     * the input is "Classes", the output is "Classes".
     * the input is "cn/library/java/tool/spring/base/Classes", the output is "cn.library.java.tool.spring.base.Classes".
     * </pre>
     *
     * @param resourcePath the class name
     * @return the resource path
     * @see ClassUtils#convertResourcePathToClassName(String)
     */
    public static String convertResourcePathToClassName(String resourcePath) {
        return ClassUtils.convertResourcePathToClassName(resourcePath);
    }

    /**
     * see {@link #parseAntStylePackagePathToPackagePath(Collection)}
     *
     * @param antStylePackagePaths the ant style package paths
     * @return package paths
     */
    public static Set<String> parseAntStylePackagePathToPackagePath(String... antStylePackagePaths) {
        return parseAntStylePackagePathToPackagePath(Collections.ofHashSet(antStylePackagePaths));
    }

    /**
     * <pre>
     * parse ant style package paths to package paths.
     * the ant style see {@link AntPathMatcher}.
     *
     * for example:
     * the input is "cn.test.*.lang"
     * the output are ["cn.test.spring.lang", "cn.test.guava.lang", "cn.test.apache.lang"]
     * </pre>
     *
     * @param antStylePackagePaths the ant style package paths
     * @return package paths
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver
     */
    public static Set<String> parseAntStylePackagePathToPackagePath(Collection<String> antStylePackagePaths) {
        return antStylePackagePaths.stream()
                .map(Classes::convertClassNameToClassPath)
                .map(Classes::parseAntStyleClassPathToPackagePath)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * see {@link #parseAntStyleClassPathToPackagePath(Collection)}
     *
     * @param antStyleClassPaths the ant style class paths
     * @return package paths
     */
    public static Set<String> parseAntStyleClassPathToPackagePath(String... antStyleClassPaths) {
        return parseAntStyleClassPathToPackagePath(Collections.ofHashSet(antStyleClassPaths));
    }

    /**
     * <pre>
     * parse ant style class paths to package paths.
     * the ant style see {@link AntPathMatcher}.
     *
     * for example:
     * the input is "classpath*:cn/test/&#42;/lang"
     * the output are ["cn.test.spring.lang", "cn.test.guava.lang", "cn.test.apache.lang"]
     * </pre>
     *
     * @param antStyleClassPaths the ant style class paths
     * @return package paths
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver
     */
    public static Set<String> parseAntStyleClassPathToPackagePath(Collection<String> antStyleClassPaths) {
        return parseAntStyleClassPathToResourcePath(antStyleClassPaths)
                .stream()
                .filter(resourcePath -> Strings.notContains(resourcePath, ClassConstant.JAVA_TEST_PATH))
                .map(resourcePath -> Strings.removeIfEndWith(convertResourcePathToClassName(Strings.subAfter(resourcePath, ClassConstant.JAVA_MAIN_PATH)), SymbolConstant.DOT))
                .collect(Collectors.toSet());
    }

    /**
     * see {@link #parseAntStyleClassPathToResourcePath(Collection)}
     *
     * @param antStyleClassPaths the ant style class paths
     * @return resource paths
     */
    public static Set<String> parseAntStyleClassPathToResourcePath(String... antStyleClassPaths) {
        return parseAntStyleClassPathToResourcePath(Collections.ofHashSet(antStyleClassPaths));
    }

    /**
     * <pre>
     * parse ant style class paths to resource paths.
     * the ant style see {@link AntPathMatcher}.
     *
     * for example:
     * the input is "classpath*:cn/test/&#42;/lang"
     * the output are ["/xxx/xxx/xxx/cn/test/spring/lang/", "/xxx/xxx/xxx/cn/test/guava/lang/", "/xxx/xxx/xxx/cn/test/apache/lang/"]
     * </pre>
     *
     * @param antStyleClassPaths the ant style class paths
     * @return resource paths
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver
     */
    public static Set<String> parseAntStyleClassPathToResourcePath(Collection<String> antStyleClassPaths) {
        return antStyleClassPaths.stream()
                .map(antStyleClassPath -> Arrays.stream(Try.of(() -> PATH_MATCHING_RESOURCE_PATTERN_RESOLVER.getResources(antStyleClassPath)).get()).toList())
                .flatMap(Collection::stream)
                .map(resource -> Try.of(() -> resource.getURL().getPath()).get())
                .collect(Collectors.toSet());
    }

    /**
     * use {@link AnnotationConstant#DEFAULT_FIELD_NAME} to {@link #parseAnnotationAntStylePackagePathToPackagePath(Class, String)}
     *
     * @param annotationType the annotation class
     * @return package paths
     * @see #parseAnnotationAntStylePackagePathToPackagePath(Class, String)
     */
    public static Set<String> parseAnnotationAntStylePackagePathToPackagePath(Class<? extends Annotation> annotationType) {
        return parseAnnotationAntStylePackagePathToPackagePath(annotationType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * <pre>
     * parse ant style package paths specified in some annotation classes to package paths.
     * the ant style see {@link AntPathMatcher}.
     *
     * for example:
     *
     * {@code
     *     // define an annotation to set the ant style package paths
     *     @Retention(RetentionPolicy.RUNTIME)
     *     @Target(ElementType.TYPE)
     *     @Documented
     *     public @interface Scanner {
     *
     *         String[] scanPackagePaths();
     *
     *     }
     *
     *     // define a scanner marked with @Scanner("cn.test.*.lang")
     *     @Scanner("cn.test.*.lang")
     *     public class LangPackageScanner {
     *
     *     }
     *
     *     // define a scanner marked with @Scanner("cn.test.*.stream")
     *     @Scanner("cn.test.*.stream")
     *     public class StreamPackageScanner {
     *
     *     }
     *
     *     // then you can use this function to collection all actual package paths.
     *     // the output example: ["cn.test.spring.lang", "cn.test.guava.lang", "cn.test.apache.lang", "cn.test.spring.stream", "cn.test.guava.stream", "cn.test.apache.stream"]
     *     Classes.parseAnnotationAntStylePackagePathToPackagePath(Scanner.class, "scanPackagePaths");
     * }
     * </pre>
     *
     * @param annotationType the annotation class
     * @param fieldName      the field name in annotation class
     * @return package paths
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver
     * @see #parseAntStylePackagePathToPackagePath(Collection)
     */
    public static Set<String> parseAnnotationAntStylePackagePathToPackagePath(Class<? extends Annotation> annotationType, String fieldName) {
        return parseAntStylePackagePathToPackagePath(Springs.scanByTypeFilter(new AnnotationTypeFilter(annotationType), BasePackagePath.get(Springs.getSpringBootApplicationPackagePath()))
                .stream()
                .map(beanDefinition -> ofName(beanDefinition.getBeanClassName()))
                .map(annotatedClass -> getTheLargestRangePackagePath(Collections.add(Collections.ofArrayList(Annotations.getAnnotationValue(annotatedClass, annotationType, String[].class, fieldName)), Springs.getSpringBootApplicationPackagePath())))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
        );
    }

    /**
     * <pre>
     * get the largest range package paths, support ant style package paths.
     *
     * for example:
     *
     * {@code
     * // example 1:
     * // "cn.test"    is contain "cn.test.lang1", "cn.test.lang1",     so keep only "cn.test".
     * // "cn.library" is contain "cn.library.xxx", "cn.library.ss.xx", so keep only "cn.library".
     * // "cn.core"    not contain the other package path, so keep it.
     * // the output is ["cn.test", "cn.library", "cn.core"]
     * Classes.getTheMostLargerRangePackagePath(List.of("cn.test.lang1", "cn.test.lang1", "cn.test", "cn.library", "cn.library.xxx", "cn.library.ss.xx", "cn.core"));
     *
     * // example 2:
     * // there is a package path like ["cn.test.lang", "cn.test.lang.collection"].
     * // the output is ["cn.test.lang"]
     * Classes.getTheMostLargerRangePackagePath(List.of("cn.test.*", "cn.test.lang.collection"));
     *
     * // warning: this following is not the expected result:
     * // all package paths start with "cn.library", and although the second package name is inconsistent, it will also be filtered out.
     * // the output is ["cn.library"]
     * Classes.getTheMostLargerRangePackagePath(List.of("cn.library.lang1", "cn.library.lang1", "cn.library", "cn.library2", "cn.library2.xxx", "cn.library2.ss.xx", "cn.library3"));
     * }
     * </pre>
     *
     * @param packagePaths the package paths
     * @return the largest range package paths
     * @see cn.srd.library.java.tool.lang.object.Classes#getTheLargestRangePackagePath(Collection)
     */
    public static Set<String> getTheLargestRangePackagePath(Collection<String> packagePaths) {
        return cn.srd.library.java.tool.lang.object.Classes.getTheLargestRangePackagePath(parseAntStylePackagePathToPackagePath(packagePaths));
    }

}
