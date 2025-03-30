package org.horizon.sdk.library.java.tool.spring.contract.support;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.annotation.AnnotationConstant;
import org.horizon.sdk.library.java.contract.constant.classes.ClassConstant;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.constant.web.ProtocolConstant;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.object.BasePackagePath;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.net.URL;
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
public class Classes extends org.horizon.sdk.library.java.tool.lang.object.Classes {

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
     * return the base package path in spring project
     *
     * @return the base package path in spring project
     * @see Springs#getSpringBootApplicationPackagePath()
     * @see BasePackagePath#get()
     */
    public static Set<String> getBasePackagePath() {
        return BasePackagePath.register(Springs.getSpringBootApplicationPackagePath());
    }

    /**
     * get resources by ant style class path
     *
     * @param antStyleClassPath the ant style class path
     * @return the set of {@link Resource}
     * @apiNote if the resource url is a jar url,
     * then call the method {@link PathMatchingResourcePatternResolver#doFindPathMatchingJarResources(Resource, URL, String) doFindPathMatchingJarResources} to {@link AntPathMatcher#match(String, String) match} two paths like "&#42;/xx1/xx2", "test/xx1/xx2/",
     * it will always match failed because only match success by two paths like "&#42;/xx1/xx2/", "test/xx1/xx2/",
     * so in this way, will add prefix "/" to the ant style class path and {@link PathMatchingResourcePatternResolver#getResources(String) get resource} again to avoid this case.
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver#getResources(String)
     */
    public static Set<Resource> getResourceByAntStyleClassPath(String antStyleClassPath) {
        Set<Resource> resources = Arrays.stream(Try.of(() -> PATH_MATCHING_RESOURCE_PATTERN_RESOLVER.getResources(antStyleClassPath)).get()).collect(Collectors.toSet());
        if (Nil.isEmpty(resources) && Strings.notEndWith(antStyleClassPath, SymbolConstant.SLASH)) {
            resources = Arrays.stream(Try.of(() -> PATH_MATCHING_RESOURCE_PATTERN_RESOLVER.getResources(antStyleClassPath + SymbolConstant.SLASH)).get()).collect(Collectors.toSet());
        }
        return resources;
    }

    /**
     * <p>get the largest range package paths with Ant-style pattern support.</p>
     *
     * <p>sample usage:</p>
     * <ol>
     *   <li>
     *     basic filtering:
     *     <pre>{@code
     *     // input: ["cn.test.lang1", "cn.test.lang1", "cn.test", "cn.core", "org.horizon.sdk.library", "org.horizon.sdk.library.xxx", "org.horizon.sdk.library.ss.xx"]
     *     // output: ["cn.test", "cn.core", "org.horizon.sdk.library"]
     *     Classes.getTheMostLargerRangePackagePath(packagePaths);
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     pattern matching:
     *     <pre>{@code
     *     // input: ["cn.test.*", "cn.test.lang.collection"]
     *     // output: ["cn.test.lang"]
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     edge case handling:
     *     <pre>{@code
     *     // input: ["org.horizon.sdk.library.lang1", "org.horizon.sdk.library", ...]
     *     // output: ["org.horizon.sdk.library"]
     *     }</pre>
     *   </li>
     * </ol>
     *
     * <p>implementation rules:</p>
     * <ul>
     *   <li>returns the most specific parent package that contains multiple sub-packages</li>
     *   <li>Ant-style patterns (e.g., "cn.test.*") are resolved before comparison</li>
     *   <li>duplicate entries are automatically deduplicated</li>
     * </ul>
     *
     * @param packagePaths the collection of package paths (supports Ant-style patterns)
     * @return the filtered list containing only the largest scope packages
     * @see org.horizon.sdk.library.java.tool.lang.object.Classes#getTheLargestRangePackagePath(Collection)
     */
    public static Set<String> getTheLargestRangePackagePath(Collection<String> packagePaths) {
        return org.horizon.sdk.library.java.tool.lang.object.Classes.getTheLargestRangePackagePath(parseAntStylePackagePathsToPackagePaths(packagePaths));
    }

    /**
     * scan all classes containing the specified annotation in the package path of class marked {@link SpringBootApplication}.
     *
     * @param annotationClass the specified annotation class
     * @return all classes containing the specified annotation in the package path of class marked {@link SpringBootApplication}
     * @apiNote this function scans every time and has poor performance, recommended to use {@link #scanByTypeFilter(Collection, Collection)} function.
     * @see org.horizon.sdk.library.java.tool.lang.object.Classes#scanByAnnotation(Class, String...)
     */
    public static Set<Class<?>> scanByAnnotation(Class<? extends Annotation> annotationClass) {
        return Try.of(() -> org.horizon.sdk.library.java.tool.lang.object.Classes.scanByAnnotation(annotationClass, Springs.getSpringBootApplicationPackagePath())).getOrElse(Collections.newHashSet());
    }

    /**
     * scan all subclasses or implementation classes of the specified class or interface in the package path of class marked {@link SpringBootApplication}.
     *
     * @param rootClass the specified class
     * @param <T>       the specified class type
     * @return all subclasses or implementation classes of the specified class or interface in the package path of class marked {@link SpringBootApplication}
     * @apiNote this function scans every time and has poor performance, recommended to use {@link #scanByTypeFilter(Collection, Collection)} function.
     * @see org.horizon.sdk.library.java.tool.lang.object.Classes#scanBySuper(Class, String...)
     */
    public static <T> Set<Class<? extends T>> scanBySuper(Class<T> rootClass) {
        return Try.of(() -> org.horizon.sdk.library.java.tool.lang.object.Classes.scanBySuper(rootClass, Springs.getSpringBootApplicationPackagePath())).getOrElse(Collections.newHashSet());
    }

    /**
     * scan all classes in the {@link #getBasePackagePath() base package path}
     *
     * @return all classes in the {@link #getBasePackagePath() base package path}
     * @see #getBasePackagePath()
     */
    public static Set<Class<?>> scanByBasePackagePath() {
        return scanByPackagePath(getBasePackagePath());
    }

    /**
     * use default package path to scan bean definition
     *
     * @param annotationType the specified annotation to match
     * @return all {@link BeanDefinition}s by the specified {@link AnnotationTypeFilter} in default packages paths.
     * @see #scanByAnnotationTypeFilter(Class, Collection)
     * @see #getBasePackagePath()
     */
    public static Set<BeanDefinition> scanByAnnotationTypeFilter(Class<? extends Annotation> annotationType) {
        return scanByAnnotationTypeFilter(annotationType, getBasePackagePath());
    }

    /**
     * see {@link #scanByAnnotationTypeFilter(Class, Collection)}
     *
     * @param annotationType   the specified annotation to match
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link AnnotationTypeFilter} in the specified packages paths.
     * @see #scanByAnnotationTypeFilter(Class, Collection)
     */
    public static Set<BeanDefinition> scanByAnnotationTypeFilter(Class<? extends Annotation> annotationType, String... scanPackagePaths) {
        return scanByAnnotationTypeFilter(annotationType, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * use {@link AnnotationTypeFilter} to {@link #scanByTypeFilter(TypeFilter, Collection)}
     *
     * @param annotationType   the specified annotation to match
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link AnnotationTypeFilter} in the specified packages paths.
     * @see #scanByTypeFilter(Collection, Collection)
     */
    public static Set<BeanDefinition> scanByAnnotationTypeFilter(Class<? extends Annotation> annotationType, Collection<String> scanPackagePaths) {
        return scanByTypeFilter(new AnnotationTypeFilter(annotationType), scanPackagePaths);
    }

    /**
     * use default package path to scan bean definition
     *
     * @param targetType the specified class to match
     * @return all {@link BeanDefinition}s by the specified {@link AssignableTypeFilter} in default packages paths.
     * @see #scanByAssignableTypeFilter(Class, Collection)
     * @see #getBasePackagePath()
     */
    public static Set<BeanDefinition> scanByAssignableTypeFilter(Class<?> targetType) {
        return scanByAssignableTypeFilter(targetType, getBasePackagePath());
    }

    /**
     * see {@link #scanByAssignableTypeFilter(Class, Collection)}
     *
     * @param targetType       the specified class to match
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link AssignableTypeFilter} in the specified packages paths.
     * @see #scanByAssignableTypeFilter(Class, Collection)
     */
    public static Set<BeanDefinition> scanByAssignableTypeFilter(Class<?> targetType, String... scanPackagePaths) {
        return scanByAssignableTypeFilter(targetType, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * use {@link AssignableTypeFilter} to {@link #scanByTypeFilter(TypeFilter, Collection)}
     *
     * @param targetType       the specified class to match
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link AssignableTypeFilter} in the specified packages paths.
     * @see #scanByTypeFilter(Collection, Collection)
     */
    public static Set<BeanDefinition> scanByAssignableTypeFilter(Class<?> targetType, Collection<String> scanPackagePaths) {
        return scanByTypeFilter(new AssignableTypeFilter(targetType), scanPackagePaths);
    }

    /**
     * use default package path to scan bean definition
     *
     * @param includeFilter the specified {@link TypeFilter}s
     * @return all {@link BeanDefinition}s by the specified {@link TypeFilter} in default packages paths.
     * @see #scanByTypeFilter(Collection, Collection)
     * @see #getBasePackagePath()
     */
    public static Set<BeanDefinition> scanByTypeFilter(TypeFilter includeFilter) {
        return scanByTypeFilter(Collections.ofImmutableList(includeFilter), getBasePackagePath());
    }

    /**
     * see {@link #scanByTypeFilter(Collection, Collection)}
     *
     * @param includeFilter    the specified {@link TypeFilter}
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link TypeFilter}s in the specified packages paths.
     * @see #scanByTypeFilter(Collection, Collection)
     */
    public static Set<BeanDefinition> scanByTypeFilter(TypeFilter includeFilter, String... scanPackagePaths) {
        return scanByTypeFilter(includeFilter, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * see {@link #scanByTypeFilter(Collection, Collection)}
     *
     * @param includeFilter    the specified {@link TypeFilter}
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link TypeFilter}s in the specified packages paths.
     * @see #scanByTypeFilter(Collection, Collection)
     */
    public static Set<BeanDefinition> scanByTypeFilter(TypeFilter includeFilter, Collection<String> scanPackagePaths) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(includeFilter);
        // note: all blank package paths must be filtered out, otherwise, it will globally scan all paths, causing the entire program to get stuck.
        return scanPackagePaths.stream().filter(Nil::isNotBlank).map(scanner::findCandidateComponents).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    /**
     * see {@link #scanByTypeFilter(Collection, Collection)}
     *
     * @param includeFilters   the specified {@link TypeFilter}s
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link TypeFilter}s in the specified packages paths.
     * @see #scanByTypeFilter(Collection, Collection)
     */
    public static Set<BeanDefinition> scanByTypeFilter(Collection<TypeFilter> includeFilters, String... scanPackagePaths) {
        return scanByTypeFilter(includeFilters, Collections.ofHashSet(scanPackagePaths));
    }

    /**
     * <p>scan {@link BeanDefinition}s using specified {@link TypeFilter}s within given package paths.</p>
     *
     * <p>example implementation flow:</p>
     * <ol>
     *   <li>
     *     define interface and implementations:
     *     <pre>{@code
     *     public interface CustomerService {}
     *
     *     public class CustomerServiceImpl1 implements CustomerService {}
     *
     *     public class CustomerServiceImpl2 implements CustomerService {}
     *
     *     public class CustomerServiceImpl3 implements CustomerService {}
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     create annotated enum:
     *     <pre>{@code
     *     @CustomerAnnotation
     *     public enum GenderType {
     *         WOMAN, MAN, UNKNOWN
     *     }
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     configure type filters:
     *     <pre>{@code
     *     List<TypeFilter> filters = Arrays.asList(
     *         // the annotation type filter, it will scan all objects containing the specified annotation in the specified package paths.
     *         new AnnotationTypeFilter(CustomerAnnotation.class),
     *         // the annotation type filter, it will scan all subclasses or implementation classes in the specified package paths.(not contains parent class)
     *         new AssignableTypeFilter(CustomerService.class)
     *     );
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     execute scanning:
     *     <pre>{@code
     *     // the output is a set {@link BeanDefinition} contains [CustomerServiceImpl1, CustomerServiceImpl2, CustomerServiceImpl3, GenderType]
     *     Set<BeanDefinition> definitions = Springs.scanByTypeFilter(
     *         filters,
     *         // the specified package paths
     *         "cn.xx1", "cn.xx2", "cn.xx3"
     *     );
     *     }</pre>
     *   </li>
     * </ol>
     *
     * @param includeFilters   type filters for scanning (combination supported)
     * @param scanPackagePaths the specified package paths
     * @return all {@link BeanDefinition}s by the specified {@link TypeFilter}s in the specified packages paths.
     */
    public static Set<BeanDefinition> scanByTypeFilter(Collection<TypeFilter> includeFilters, Collection<String> scanPackagePaths) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        includeFilters.forEach(scanner::addIncludeFilter);
        // note: all blank package paths must be filtered out, otherwise, it will globally scan all paths, causing the entire program to get stuck.
        return scanPackagePaths.stream()
                .filter(Nil::isNotBlank)
                .map(scanner::findCandidateComponents)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * <p>convert class name to resource path with following rules:</p>
     *
     * <ul>
     *   <li>simple class name remains unchanged
     *     <pre>{@code
     *     "Classes" → "Classes"
     *     }</pre>
     *   </li>
     *   <li>fully-qualified names convert dots to path separators
     *     <pre>{@code
     *     "org.horizon...Classes" → "org/horizon/.../Classes"
     *     }</pre>
     *   </li>
     * </ul>
     *
     * @param className the fully-qualified class name (e.g. "java.lang.String")
     * @return corresponding resource path for class loading
     * @see ClassUtils#convertClassNameToResourcePath(String)
     */
    public static String convertClassNameToResourcePath(String className) {
        return ClassUtils.convertClassNameToResourcePath(className);
    }

    /**
     * <p>convert class name to classpath resource pattern with following rules:</p>
     *
     * <ul>
     *   <li>
     *     simple class name conversion:
     *     <pre>{@code
     *     "Classes" → "classpath*:Classes"
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     fully-qualified class name conversion:
     *     <pre>{@code
     *     "org.horizon.sdk...Classes" → "classpath*:org/horizon/sdk/.../Classes"
     *     }</pre>
     *   </li>
     * </ul>
     *
     * @param className the class name (supports both simple and fully-qualified names)
     * @return classpath resource pattern with wildcard prefix
     */
    public static String convertClassNameToClassPath(String className) {
        return ClassConstant.CLASS_PATH_PREFIX + convertClassNameToResourcePath(className);
    }

    /**
     * <p>convert resource path to fully-qualified class name with following rules:</p>
     *
     * <ul>
     *   <li>
     *     simple resource path conversion:
     *     <pre>{@code
     *     "Classes" → "Classes"
     *     }</pre>
     *   </li>
     *
     *   <li>
     *     hierarchical path conversion:
     *     <pre>{@code
     *     "org/horizon/sdk/.../Classes" → "org.horizon.sdk....Classes"
     *     }</pre>
     *   </li>
     * </ul>
     *
     * @param resourcePath the file system path or resource path (using '/' separators)
     * @return corresponding Java class name with package structure
     * @see ClassUtils#convertResourcePathToClassName(String)
     */
    public static String convertResourcePathToClassName(String resourcePath) {
        return ClassUtils.convertResourcePathToClassName(resourcePath);
    }

    /**
     * see {@link #parseAntStylePackagePathsToPackagePaths(Collection)}
     *
     * @param antStylePackagePaths the ant style package paths
     * @return package paths
     */
    public static Set<String> parseAntStylePackagePathsToPackagePaths(String... antStylePackagePaths) {
        return parseAntStylePackagePathsToPackagePaths(Collections.ofHashSet(antStylePackagePaths));
    }

    /**
     * <p>expand Ant-style package patterns to concrete package paths using {@link AntPathMatcher} semantics.</p>
     *
     * <p>conversion examples:</p>
     * <pre>{@code
     * // matches any subpackage ending with "lang"
     * "cn.test.*.lang" → ["cn.test.spring.lang", "cn.test.guava.lang", "cn.test.apache.lang"]
     * }</pre>
     *
     * @param antStylePackagePaths package patterns with Ant-style wildcards (*, **)
     * @return concrete package paths existing in classpath that match patterns
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver
     */
    public static Set<String> parseAntStylePackagePathsToPackagePaths(Collection<String> antStylePackagePaths) {
        return antStylePackagePaths.stream()
                .map(Classes::convertClassNameToClassPath)
                .map(Classes::parseAntStyleClassPathsToPackagePaths)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    /**
     * see {@link #parseAntStyleClassPathsToPackagePaths(Collection)}
     *
     * @param antStyleClassPaths package patterns with Ant-style wildcards (*, **)
     * @return concrete package paths existing in classpath that match patterns
     */
    public static Set<String> parseAntStyleClassPathsToPackagePaths(String... antStyleClassPaths) {
        return parseAntStyleClassPathsToPackagePaths(Collections.ofHashSet(antStyleClassPaths));
    }

    /**
     * <p>parses ant-style class paths into package paths.</p>
     *
     * <p>this uses ant-style patterns as implemented in {@link AntPathMatcher}.</p>
     *
     * <p>conversion examples:</p>
     * <pre>{@code
     * "classpath*:cn/test/* /lang" → ["cn.test.spring.lang", "cn.test.guava.lang", "cn.test.apache.lang"]
     * }</pre>
     *
     * @param antStyleClassPaths package patterns with Ant-style wildcards (*, **)
     * @return concrete package paths existing in classpath that match patterns
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver
     */
    public static Set<String> parseAntStyleClassPathsToPackagePaths(Collection<String> antStyleClassPaths) {
        return parseAntStyleClassPathsToResourcePaths(antStyleClassPaths)
                .stream()
                .filter(resourcePath -> Strings.notContains(resourcePath, ClassConstant.JAVA_TEST_PATH))
                .map(resourcePath -> {
                    String relativeResourcePath;
                    // parse the relative resource path:
                    if (Strings.containsAny(resourcePath, ClassConstant.JAVA_MAIN_PATH)) {
                        // if the resource path like "/absolutePath/projectName/build/classes/java/main/cn/test/lang/", then parse it to "cn/test/lang/"
                        relativeResourcePath = Strings.subAfter(resourcePath, ClassConstant.JAVA_MAIN_PATH);
                    } else if (Strings.containsAny(resourcePath, ClassConstant.RESOURCE_MAIN_PATH)) {
                        // if the resource path like "/absolutePath/projectName/build/resources/main/cn/test/lang/", then parse it to "cn/test/lang/"
                        relativeResourcePath = Strings.subAfter(resourcePath, ClassConstant.RESOURCE_MAIN_PATH);
                    } else if (Strings.containsAny(resourcePath, ClassConstant.DOCKER_APP_CLASS_PATH)) {
                        // if the resource path like "/absolutePath/app/classes/cn/test/lang/", then parse it to "cn/test/lang/"
                        relativeResourcePath = Strings.subAfter(resourcePath, ClassConstant.DOCKER_APP_CLASS_PATH);
                    } else if (Strings.containsAny(resourcePath, ClassConstant.DOCKER_APP_RESOURCE_PATH)) {
                        // if the resource path like "/absolutePath/app/resources/cn/test/lang/", then parse it to "cn/test/lang/"
                        relativeResourcePath = Strings.subAfter(resourcePath, ClassConstant.DOCKER_APP_RESOURCE_PATH);
                    } else if (Strings.startWith(resourcePath, ProtocolConstant.FILE) && Strings.containsAny(resourcePath, ClassConstant.REFERENCE_JAR_PATH)) {
                        // if the resource path like "file:/absolutePath/xxx.jar!/cn/test/lang/", then parse it to "cn/test/lang/"
                        relativeResourcePath = Strings.subAfter(resourcePath, ClassConstant.REFERENCE_JAR_PATH);
                    } else {
                        throw new LibraryJavaInternalException(Strings.format("{}parse ant style class path to package path failed because of the unsupported resource path [{}]", ModuleView.TOOL_CLASS_SYSTEM, resourcePath));
                    }
                    return Strings.removeIfEndWith(convertResourcePathToClassName(relativeResourcePath), SymbolConstant.DOT);
                })
                .collect(Collectors.toSet());
    }

    /**
     * see {@link #parseAntStyleClassPathsToResourcePaths(Collection)}
     *
     * @param antStyleClassPaths package patterns with Ant-style wildcards (*, **)
     * @return concrete package paths existing in classpath that match patterns
     */
    public static Set<String> parseAntStyleClassPathsToResourcePaths(String... antStyleClassPaths) {
        return parseAntStyleClassPathsToResourcePaths(Collections.ofHashSet(antStyleClassPaths));
    }

    /**
     * <p>parses ant-style class paths into resolved resource paths.</p>
     *
     * <p>this uses ant-style patterns as implemented in {@link AntPathMatcher}.</p>
     *
     * <p>example:</p>
     * <pre>{@code
     * input:  "classpath*:cn/test/* /lang"
     * output: [
     *     "/xxx/xxx/xxx/cn/test/spring/lang/",
     *     "/xxx/xxx/xxx/cn/test/guava/lang/",
     *     "/xxx/xxx/xxx/cn/test/apache/lang/"
     * ]
     * }</pre>
     *
     * @param antStyleClassPaths package patterns with Ant-style wildcards (*, **)
     * @return concrete package paths existing in classpath that match patterns
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver#getResources(String)
     * @see #getResourceByAntStyleClassPath(String)
     */
    public static Set<String> parseAntStyleClassPathsToResourcePaths(Collection<String> antStyleClassPaths) {
        return antStyleClassPaths.stream()
                .map(Classes::getResourceByAntStyleClassPath)
                .flatMap(Collection::stream)
                .map(resource -> Try.of(() -> resource.getURL().getPath()).get())
                .collect(Collectors.toSet());
    }

    /**
     * use {@link AnnotationConstant#DEFAULT_FIELD_NAME} to {@link #parseAnnotationAntStylePackagePathsToPackagePaths(Class, String)}
     *
     * @param annotationType the annotation class
     * @return package paths
     * @see #parseAnnotationAntStylePackagePathsToPackagePaths(Class, String)
     */
    public static Set<String> parseAnnotationAntStylePackagePathsToPackagePaths(Class<? extends Annotation> annotationType) {
        return parseAnnotationAntStylePackagePathsToPackagePaths(annotationType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * <p>parses ant-style package paths defined in annotation class fields.</p>
     *
     * <p>this uses ant-style patterns as implemented in {@link AntPathMatcher}.</p>
     *
     * <p>example usage:</p>
     * <pre>{@code
     * // Define annotation with path configuration
     * @Retention(RetentionPolicy.RUNTIME)
     * @Target(ElementType.TYPE)
     * @Documented
     * public @interface Scanner {
     *     String[] scanPackagePaths();
     * }
     *
     * // Apply annotations to scanners
     * @Scanner("cn.test.*.lang")
     * public class LangPackageScanner {}
     *
     * @Scanner("cn.test.*.stream")
     * public class StreamPackageScanner {}
     *
     * // Parse actual paths
     * List<String> paths = Classes.parseAnnotationAntStylePackagePathToPackagePath(
     *     Scanner.class,
     *     "scanPackagePaths"
     * );
     * // Result: [
     * //   "cn.test.spring.lang", "cn.test.guava.lang",
     * //   "cn.test.apache.lang", "cn.test.spring.stream",
     * //   "cn.test.guava.stream", "cn.test.apache.stream"
     * // ]
     * }</pre>
     *
     * @param annotationType the annotation class containing ant-style path definitions
     * @param fieldName      the field name in the annotation that specifies package paths
     * @return package paths
     * @see AntPathMatcher
     * @see PathMatchingResourcePatternResolver
     * @see #parseAntStylePackagePathsToPackagePaths(Collection)
     */
    public static Set<String> parseAnnotationAntStylePackagePathsToPackagePaths(Class<? extends Annotation> annotationType, String fieldName) {
        return parseAntStylePackagePathsToPackagePaths(Annotations.getAnnotationNestValues(annotationType, String[].class, fieldName));
    }

    /**
     * use {@link AnnotationConstant#DEFAULT_FIELD_NAME} to {@link #optimizeAnnotationAntStylePackagePaths(Class, String)}
     *
     * @param annotationType the annotation class
     * @return package paths
     * @see #optimizeAnnotationAntStylePackagePaths(Class, String)
     */
    public static Set<String> optimizeAnnotationAntStylePackagePaths(Class<? extends Annotation> annotationType) {
        return optimizeAnnotationAntStylePackagePaths(annotationType, AnnotationConstant.DEFAULT_FIELD_NAME);
    }

    /**
     * <p>finds optimal package paths through multi-step resolution:</p>
     * <ol>
     *   <li>calls {@link #parseAnnotationAntStylePackagePathsToPackagePaths(Class, String)} to resolve annotation paths</li>
     *   <li>determines coverage range via {@link #getTheLargestRangePackagePath(Collection)}</li>
     *   <li>combines with {@link Springs#getSpringBootApplicationPackagePath()} for final optimization</li>
     * </ol>
     *
     * <p>typical workflow:</p>
     * <pre>{@code
     * // annotation -> parsed paths -> coverage analysis -> combined with Spring Boot path
     * Set<String> paths = parseAnnotationAntStylePackagePathsToPackagePaths(Scanner.class, "scanPaths");
     * String largestPath = getTheLargestRangePackagePath(paths);
     * String finalPath = largestPath + Springs.getSpringBootApplicationPackagePath();
     * }</pre>
     *
     * @param annotationType the annotation class containing ant-style path definitions
     * @param fieldName      the field name in annotation that stores package paths
     * @return optimized package paths combining annotation config and Spring Boot context
     * @see #parseAnnotationAntStylePackagePathsToPackagePaths(Class, String)
     * @see #getTheLargestRangePackagePath(Collection)
     * @see Springs#getSpringBootApplicationPackagePath()
     */
    public static Set<String> optimizeAnnotationAntStylePackagePaths(Class<? extends Annotation> annotationType, String fieldName) {
        return getTheLargestRangePackagePath(Collections.add(
                parseAnnotationAntStylePackagePathsToPackagePaths(annotationType, fieldName),
                Springs.getSpringBootApplicationPackagePath()
        ));
    }

}