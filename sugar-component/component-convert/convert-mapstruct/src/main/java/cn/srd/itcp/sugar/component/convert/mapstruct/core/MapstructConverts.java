package cn.srd.itcp.sugar.component.convert.mapstruct.core;

import cn.srd.itcp.sugar.component.convert.mapstruct.exception.MapstructConvertMethodNotFoundException;
import cn.srd.itcp.sugar.component.convert.mapstruct.exception.MapstructConvertMethodUnsupportedException;
import cn.srd.itcp.sugar.component.convert.mapstruct.support.MapstructConvertsSupporter;
import cn.srd.itcp.sugar.component.convert.mapstruct.support.MapstructConvertsSupporterManager;
import cn.srd.itcp.sugar.component.convert.mapstruct.support.MapstructListBeanConvertsSupporter;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.constant.StringPool;
import cn.srd.itcp.sugar.tool.core.Objects;
import cn.srd.itcp.sugar.tool.core.*;
import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import cn.srd.itcp.sugar.tool.core.convert.Converts;
import cn.srd.itcp.sugar.tool.core.validation.Nullable;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.ImmutableMap;
import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mapstruct 转换器
 *
 * @author wjm
 * @since 2021/5/1 14:13
 */
@Slf4j
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("unchecked")
@Deprecated
public class MapstructConverts {

    /**
     * singleton pattern
     */
    private static final class SingleTonHolder {
        private static final MapstructConverts INSTANCE = SpringsUtil.getBean(MapstructConverts.class);
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static MapstructConverts getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    /**
     * 默认目标值
     */
    private static final Object DEFAULT_TARGET_VALUE = null;

    /**
     * 是否静默转换，即报错不抛出异常，只打印日志，默认不静默转换
     */
    private static final boolean DEFAULT_CONVERT_QUIETLY = Converts.DEFAULT_CONVERT_QUIETLY;

    /**
     * Mapstruct 中所有方法
     * <pre>
     *      该 map 仅用于帮助构建
     *          {@link #mapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap}
     *          {@link #mapstructSupportedMethodInputParameterNameMappingMethodMap}
     *      初始化完成后置为 null；
     * </pre>
     */
    private List<Method> mapstructAllMethods;

    /**
     * Mapstruct 中所有方法的入参全路径类名拼接出参全路径类名字符串 映射 其对应的所有方法
     * <pre>
     *      例如 Mapstruct 定义了如下方法：
     *        1、StudentVO toVO(StudentDO studentDO)
     *        2、StudentVO toVO(StudentDO studentDO, GradeDO gradeDO)
     *        3、GradeVO toVO(GradeDO gradeDO)
     *        4、List< StudentVO> toVOs(List< StudentDO> studentDOs)
     *        5、List< GradeVO> toVOs1(List< GradeDO> gradeDOs)
     *        6、List< GradeVO> toVOs2(List< GradeDO> gradeDOs)
     *      收集为该 map 时，其值为：
     *        1、xxx.StudentDO/xxx.StudentVO =&gt; [Method(toVO)]
     *        2、xxx.StudentDO/xxx.GradeDO/xxx.StudentVO =&gt; [Method(toVOMethod)]
     *        3、xxx.GradeDO =&gt; [Method(toVO)]
     *        4、java.util.Listxxx.StudentDO/java.util.Listxxx.StudentVO =&gt; [Method(toVOsMethod)]
     *        5、6、java.util.Listxxx.GradeDO/java.util.Listxxx.GradeVO =&gt; [Method(toVOs1Method), Method(toVOs2Method)]
     *      该 map 仅用于帮助构建
     *          {@link #mapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap}
     *          {@link #mapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap}
     *      初始化完成后置为 null；
     * </pre>
     */
    private Map<String, List<Method>> mapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap;

    /**
     * Mapstruct 中支持使用统一转换器进行转换，且只有一个待转换类，且为普通 Bean 类型的方法中该转换方法的待转换类全路径类名字符串 映射 其对应的方法
     * <pre>
     *      例如 Mapstruct 定义了如下方法：
     *        1、StudentVO toVO(StudentDO studentDO)
     *        2、StudentVO toVO(StudentDO studentDO, GradeDO gradeDO)
     *        3、GradeVO toVO(GradeDO gradeDO)
     *        4、List< StudentVO> toVOs(List< StudentDO> studentDOs)
     *        5、List< GradeVO> toVOs1(List< GradeDO> gradeDOs)
     *        6、List< GradeVO> toVOs2(List< GradeDO> gradeDOs)
     *      收集为该 map 时，其值为：
     *        xxx.GradeDO =&gt; [Method(toVO)]
     *      其中，第 1、2、4、5、6 个方法不会被收集到该 map 中，原因如下：
     *        该 map 用作实际转换时，若只有一个待转换类，比如第 3 个方法，则直接根据该待转换类的类名来匹配转换方法；
     *          如：将 GradeDO 类的全路径类名 xxx.GradeDO 作为 key 在该 map 中匹配到对应的转换方法进行转换；
     *        若待转换类有多个，比如第 2 个方法，或者待转换类不是普通 Bean 类型，比如第 4、5、6 个方法，此时在匹配对应的转换方法时，会发生字符串拼接；
     *        字符串拼接是指 将待转换类和目标类的全路径类名 进行拼接；
     *          如：
     *           第 2 个方法 xxx.StudentDO/xxx.GradeDO/xxx.StudentVO
     *           第 4 个方法 java.util.Listxxx.StudentDO/java.util.Listxxx.StudentVO
     *           第 5、6 个方法属于 {@link #mapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap}
     *        第 1 个方法由于和第 2 个方法的第一个入参相同，所以第 1 个方法也不会加入到该 map 中；
     *        由于进行转换时，是使用反射的方式执行匹配到的转换方法，而反射使用了 ASM，并且在 ASM 基础上再做了一层缓存 {@link #MAPSTRUCT_CLASS_NAME_MAPPING_ASM_REFLECT_MAP}，避免了每次执行方法时都需要加悲观锁；
     *        因此真正耗时不在于反射，而在于字符串拼接，ASM 反射以及字符串拼接的基准性能测试可参考测试类；
     *        考虑到大部分的转换方法都只会有一个待转换类，所以使用该 map 作为转换时匹配转换方法的第一层缓存，避免发生字符串拼接，提升性能；
     * </pre>
     */
    private Map<String, Method> mapstructSupportedMethodInputParameterNameMappingMethodMap;

    /**
     * Mapstruct 中支持使用统一转换器进行转换的所有转换方法的入参全路径类名拼接出参全路径类名字符串 映射 其对应的方法
     * <pre>
     *      例如 Mapstruct 定义了如下方法：
     *        1、StudentVO toVO(StudentDO studentDO)
     *        2、StudentVO toVO(StudentDO studentDO, GradeDO gradeDO)
     *        3、GradeVO toVO(GradeDO gradeDO)
     *        4、List< StudentVO> toVOs(List< StudentDO> studentDOs)
     *        5、List< GradeVO> toVOs1(List< GradeDO> gradeDOs)
     *        6、List< GradeVO> toVOs2(List< GradeDO> gradeDOs)
     *      收集为该 map 时，其值为：
     *        1、xxx.StudentDO/xxx.StudentVO =&gt; [Method(toVO)]
     *        2、xxx.StudentDO/xxx.GradeDO/xxx.StudentVO =&gt; [Method(toVOMethod)]
     *        3、xxx.GradeDO =&gt; [Method(toVO)]
     *        4、java.util.Listxxx.StudentDO/java.util.Listxxx.StudentVO =&gt; [Method(toVOsMethod)]
     *      其中，第 5、6 个方法不会被收集到该 map 中，原因如下：
     *        首先，在匹配转换方法时，第一层缓存 {@link #mapstructSupportedMethodInputParameterNameMappingMethodMap} 中找不到时，会进入该 map 中进行匹配；
     *        该 map 用作匹配转换方法的第二层缓存，常用于待转换类有多个，比如第 2 个方法，或者待转换类不是普通 Bean 类型，比如第 4 个方法的情况；
     * </pre>
     */
    private Map<String, Method> mapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap;

    /**
     * Mapstruct 中不支持使用统一转换器 {@link MapstructConverts} 进行转换的方法的入参全路径类名拼接出参全路径类名字符串 映射 其对应的所有方法
     * <pre>
     *      例如 Mapstruct 定义了如下方法：
     *        1、StudentVO toVO(StudentDO studentDO)
     *        2、StudentVO toVO(StudentDO studentDO, GradeDO gradeDO)
     *        3、GradeVO toVO(GradeDO gradeDO)
     *        4、List< StudentVO> toVOs(List< StudentDO> studentDOs)
     *        5、List< GradeVO> toVOs1(List< GradeDO> gradeDOs)
     *        6、List< GradeVO> toVOs2(List< GradeDO> gradeDOs)
     *      收集为该 map 时，其值为：
     *        4、5、java.util.Listxxx.GradeDO/java.util.Listxxx.GradeVO =&gt; [Method(toVOs1Method), Method(toVOs2Method)]
     *      其中，第 1、2、3、4 个方法不会被收集到该 map 中，原因如下：
     *        统一转换器进行转换前需要根据待转换类以及目标类匹配到对应的转换方法，当待转换类、目标类完全一致，但其对应的转换方法有多个，且为不同的方法名时，
     *        统一转换器无法知道具体要使用哪个方法进行转换，因此定义了该 map 用于收集这种不支持的方法，用于转换时实时提示；
     *        当出现了这种情况时，可通过直接调用 Mapstruct 的转换方法进行转换，而不使用统一转换器；
     *        但其实该种情况基本不会出现；
     *        TODO wjm 考虑一个兜底策略，当出现这种情况时，是否能直接调到对应的方法？
     * </pre>
     */
    private Map<String, List<Method>> mapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap;

    /**
     * Mapstruct 转换类全路径类名 映射 其对应的ASM
     * <pre>
     *      由于在执行转换方法时需要先获取到其转换类对应的 ASM，而在获取时会使用 synchronized 悲观锁，因此此处做一层缓存，避免每次获取时加锁
     * </pre>
     */
    private static final Map<String, MethodAccess> MAPSTRUCT_CLASS_NAME_MAPPING_ASM_REFLECT_MAP = new ConcurrentHashMap<>();

    /**
     * Mapstruct 转换类全路径类名 映射 其实例
     * <pre>
     *      该缓存是在 SpringBean 基础上做的缓存，避免每次都要从 IOC 容器中获取
     * </pre>
     */
    private static final Map<String, Object> MAPSTRUCT_CLASS_NAME_MAPPING_MAPSTRUCT_CLASS_MAP = new ConcurrentHashMap<>();

    /**
     * Mapstruct 转换组件初始化
     */
    @PostConstruct
    private void init() {
        addConvertSupporter();
        collectMapstructAllMethods();
        collectMapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap();
        collectMapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap();
        collectMapstructSupportedMethodInputParameterNameMappingMethodMap();
        collectMapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap();
        handleHaveDifferentParametersMapstructMethods(getMapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap(), true);
        AfterInit();
    }

    /**
     * 添加 Mapstruct 转换器支持
     */
    private void addConvertSupporter() {
        addListBeanConvertSupporter();
    }

    /**
     * 添加 Mapstruct List Bean 转换器支持
     */
    private void addListBeanConvertSupporter() {
        MapstructConvertsSupporterManager.addConvertSupporter(object -> {
            if (List.class.isAssignableFrom(object.getClass())) {
                return MapstructListBeanConvertsSupporter.INSTANCE;
            }
            return null;
        });
    }

    /**
     * 收集 {@link #mapstructAllMethods}
     */
    private void collectMapstructAllMethods() {
        /**
         * 若使用 {@link MapstructScan} 指定了要扫描的包路径，则在指定的包路径下扫描被 {@link BindMapstruct} 注解标记了的 Mapstruct 转换类；
         * 若没有显式指定 {@link MapstructScan}，则在 {@link SpringBootApplication} 所在的包下扫描被 {@link BindMapstruct} 注解标记了的 Mapstruct 转换类；
         */
        Set<Class<?>> classesWithMapstructScan = SpringsUtil.scanPackageByAnnotation(MapstructScan.class);
        Set<Class<?>> classesWithBindMapstruct;
        Assert.INSTANCE.set(StringsUtil.format("found multi @{} in {}, please just specifies one", MapstructScan.class.getSimpleName(), classesWithMapstructScan.stream().map(Class::getName).toList())).throwsIfTrue(classesWithMapstructScan.size() > 1);
        if (Objects.isNotEmpty(classesWithMapstructScan)) {
            String[] packageNamesToFindMapstruct = AnnotationsUtil.getAnnotationValue(CollectionsUtil.getFirst(classesWithMapstructScan), MapstructScan.class);
            classesWithBindMapstruct = ClassesUtil.scanPackageByAnnotation(packageNamesToFindMapstruct, BindMapstruct.class);
        } else {
            classesWithBindMapstruct = SpringsUtil.scanPackageByAnnotation(BindMapstruct.class);
        }

        /**
         * 由于所有 mapstruct 的相关类使用了 bean 来管理，
         * 若 mapstruct 的相关类未使用如：&#064;{@link org.mapstruct.Mapper Mapper}(componentModel = "spring")，
         * 此时 mapstruct 不会加入 IOC 容器，
         * 此处将未加入 IOC 容器的 mapstruct 加入 IOC 容器；
         */
        classesWithBindMapstruct.forEach(classWithBindMapstruct -> {
            if (Objects.isNull(SpringsUtil.getBean(classWithBindMapstruct))) {
                SpringsUtil.registerBean(
                        StringsUtil.lowerFirst(classWithBindMapstruct.getSimpleName()),
                        Objects.requireNotNull(() -> StringsUtil.format("无法获取 {} 的实例，请检查！", classWithBindMapstruct.getName()), Mappers.getMapper(classWithBindMapstruct))
                );
            }
        });

        /**
         * 收集 {@link BindMapstruct} 注解标记了的 Mapstruct 转换类的所有方法
         */
        setMapstructAllMethods(classesWithBindMapstruct
                .stream()
                .map(mapstructObjectClass -> Arrays.asList(ReflectsUtil.getMethods(mapstructObjectClass)))
                .flatMap(Collection::stream)
                .toList()
        );
    }

    /**
     * 收集 {@link #mapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap}
     */
    private void collectMapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap() {
        setMapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap(CollectionsUtil.toImmutableMap(CollectionsUtil.toMultiMap(
                getMapstructAllMethods(),
                // key：获取方法出入参的全路径类名，以 "/" 作为分隔符，最后一个全路径类名为出参，之前的全路径类名为入参，并且去除例如 List<Bean> 中的 "<"、">"，原因是泛型在运行时无法直接获取，在实际转换前匹配转换方法时，不再进行泛型前后标志符的拼接，提升性能
                // 示例1：xxx.GradeDO/xxx.GradeVO
                // 示例2：xxx.StudentDO/xxx.GradeDO/xxx.StudentVO
                // 示例3：java.util.Listxxx.StudentDO/java.util.Listxxx.StudentVO
                method -> StringsUtil.removeAny(
                        StringsUtil.join(
                                StringPool.SLASH,
                                CollectionsUtil.add(TypesUtil.getParameterTypeNames(method), TypesUtil.getReturnTypeName(method))
                        ),
                        StringPool.SINGLE_BOOK_NAME_LEFT, StringPool.SINGLE_BOOK_NAME_RIGHT
                ),
                // value：对应的 method
                method -> method
        )));
    }

    /**
     * 收集 {@link #mapstructSupportedMethodInputParameterNameMappingMethodMap}
     */
    private void collectMapstructSupportedMethodInputParameterNameMappingMethodMap() {
        // 获取所有支持统一转换的方法
        List<Method> mapstructSupportedMethods = new ArrayList<>(getMapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap().values());
        // 从所有支持统一转换的方法中过滤出只有一个入参的方法
        List<Method> haveOneParameterMapstructSupportedMethods = CollectionsUtil.filtersToList(mapstructSupportedMethods, method -> method.getParameterTypes().length == 1);
        // 从所有支持统一转换的方法中过滤出有多个入参的方法
        List<Method> haveMoreParameterMapstructSupportedMethods = CollectionsUtil.filtersToList(mapstructSupportedMethods, method -> method.getParameterTypes().length > 1);
        /**
         * 从只有一个入参的方法中，再过滤掉该入参存在于多个入参方法中的方法
         *  例如：
         *      Mapstruct 定义了如下方法：
         *        1、StudentVO toVO(StudentDO studentDO)
         *        2、StudentVO toVO(StudentDO studentDO, GradeDO gradeDO)
         *        3、GradeVO toVO(GradeDO gradeDO)
         *        4、List< StudentVO> toVOs(List< StudentDO> studentDOs)
         *      则只有第 3 个方法会加入到该 map 中，第 1、2 个方法会被过滤掉，即便第 1 个方法也只有一个入参；
         *      原因是，在实际转换前的匹配方法过程中，会首先用第一个待转换类进行匹配，若第 1 个方法也存在该 map 中，此时就会出现有多个转换类的方法被匹配到只有一个转换类的方法；
         *      而第 4 个方法存在泛型，不是普通 Bean，存在泛型表示在实际转换前的匹配方法过程中必须拼接字符串用于匹配，因此也不会加入到该 map 中；
         */
        List<Method> doHaveOneParameterMapstructSupportedMethods = CollectionsUtil.filtersToList(haveOneParameterMapstructSupportedMethods,
                mapstructSupportedMethodHasOneParameter -> {
                    if (TypesUtil.isFirstParameterExistGeneric(mapstructSupportedMethodHasOneParameter)) {
                        return false;
                    }
                    for (Method mapstructSupportedMethodHasMoreParameter : haveMoreParameterMapstructSupportedMethods) {
                        if (Objects.equals(
                                CollectionsUtil.getFirst(TypesUtil.getParameterTypeNames(mapstructSupportedMethodHasMoreParameter)),
                                CollectionsUtil.getFirst(TypesUtil.getParameterTypeNames(mapstructSupportedMethodHasOneParameter))
                        )) {
                            return false;
                        }
                    }
                    return true;
                }
        );
        setMapstructSupportedMethodInputParameterNameMappingMethodMap(CollectionsUtil.toImmutableMap(CollectionsUtil.capableFiltersToList(
                CollectionsUtil.toMultiMap(
                        doHaveOneParameterMapstructSupportedMethods,
                        method -> StringsUtil.removeAny(
                                StringsUtil.join(
                                        StringPool.SLASH,
                                        TypesUtil.getParameterTypeNames(method)
                                ),
                                StringPool.SINGLE_BOOK_NAME_LEFT,
                                StringPool.SINGLE_BOOK_NAME_RIGHT
                        ),
                        method -> method
                ),
                entry -> entry.getValue().size() == 1 && !StringsUtil.containsAny(entry.getKey(), MapstructConvertsSupporter.LIST_NAME, StringPool.SLASH),
                entry -> entry.getValue().get(0)
        )));
    }

    /**
     * 收集 {@link #mapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap}
     */
    private void collectMapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap() {
        setMapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap(CollectionsUtil.toImmutableMap(CollectionsUtil.capableFiltersToList(
                getMapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap(),
                // 待转换类只有一个
                entry -> entry.getValue().size() == 1,
                entry -> entry.getValue().get(0)
        )));
    }

    /**
     * 收集 {@link #mapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap}
     */
    private void collectMapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap() {
        setMapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap(CollectionsUtil.toImmutableMap(CollectionsUtil.filtersToList(
                getMapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap(),
                // 待转换类有多个
                entry -> entry.getValue().size() > 1
        )));
    }

    /**
     * 初始化后的操作
     */
    private void AfterInit() {
        // help GC
        mapstructAllMethods = null;
        mapstructAllMethodsInputAndOutputParameterNameMappingMethodsMap = null;
    }

    /**
     * 根据待转换类获取对应的默认值
     *
     * @param defaultValue 默认值
     * @param source       待转换类
     * @param <T>          默认值类型
     * @return 默认值
     */
    private <T> Object getDefaultValue(@Nullable T defaultValue, @NonNull Object source) {
        return MapstructConvertsSupporterManager.getSupporter(source).getDefaultValue(defaultValue);
    }

    /**
     * 确保获取到转换方法
     *
     * @param mapstructConvertsSupporter Mapstruct 转换器支持类
     * @param target                     目标类
     * @param sources                    待转换类
     * @return 转换方法
     */
    private Method ensureGetMatchedMethod(@NonNull MapstructConvertsSupporter mapstructConvertsSupporter, @NonNull Class<?> target, @NonNull Object... sources) {
        Object source = sources[0];
        String key = mapstructConvertsSupporter.buildKeyToMatchMapstructMethod(source);
        // 从第一层缓存获取转换方法
        Method method = getMapstructSupportedMethodInputParameterNameMappingMethodMap().get(key);
        if (Objects.isNull(method)) {
            key = mapstructConvertsSupporter.buildKeyToMatchMapstructMethod(target, sources);
            // 从第二层缓存获取转换方法
            method = getMapstructSupportedMethodInputAndOutputParameterNameMappingMethodMap().get(key);
            if (Objects.isNull(method)) {
                // 转换方法是否存在于不支持的转换方法集合中
                List<Method> unsupportedMethods = getMapstructUnsupportedMethodInputAndOutputParameterNameMappingMethodsMap().get(key);
                if (Objects.isNotEmpty(unsupportedMethods)) {
                    handleHaveDifferentParametersMapstructMethods(ImmutableMap.of(key, unsupportedMethods), false);
                }
                // 未找到转换方法
                handleMapstructMethodsNotFound(key);
            }
        }
        return method;
    }

    /**
     * 转换方法未能找到时的处理
     *
     * @param key 参考：{@link MapstructConvertsSupporter#buildKeyToMatchMapstructMethod(Object)}、{@link MapstructConvertsSupporter#buildKeyToMatchMapstructMethod(Class, Object...)}
     */
    private void handleMapstructMethodsNotFound(String key) {
        Assert.INSTANCE.set(new MapstructConvertMethodNotFoundException(
                StringsUtil.format("未能找到匹配的方法，请确保已使用 @{} 标记了 Mapstruct 转换器，且在转换器中定义了以下参数的方法：\n\t 入参:[{}]\n\t 出参:[{}]",
                        BindMapstruct.class.getSimpleName(),
                        StringsUtil.subBefore(key, StringPool.SLASH, true),
                        StringsUtil.subAfter(key, StringPool.SLASH, true))
        )).throwsNow();
    }

    /**
     * 出现相同出入参转换方法时的处理
     *
     * @param mapstructUnsupportedMethodParameterNameMappingMethodsMap 不支持使用统一转换器转换的方法集合
     * @param warnOrThrow                                              true 代表打印异常，false 代表抛出异常
     */
    private void handleHaveDifferentParametersMapstructMethods(Map<String, List<Method>> mapstructUnsupportedMethodParameterNameMappingMethodsMap, boolean warnOrThrow) {
        if (Objects.isEmpty(mapstructUnsupportedMethodParameterNameMappingMethodsMap)) {
            return;
        }
        List<String> unsupportedLocations = new ArrayList<>();
        mapstructUnsupportedMethodParameterNameMappingMethodsMap.forEach((name, methods) -> {
            StringBuilder unsupportedReason = new StringBuilder().append("\t 位置[类名#方法名]: ");
            methods.forEach(method -> unsupportedReason.append(StringsUtil.format("[{}#{}]", method.getDeclaringClass().getName(), method.getName())));
            Method methodToGetParameter = CollectionsUtil.getFirst(methods);
            unsupportedLocations.add(StringsUtil.format(
                    "{}, 入参:[{}], 出参:[{}]",
                    unsupportedReason.toString(),
                    StringsUtil.pretty(TypesUtil.getParameterTypeNames(methodToGetParameter)),
                    TypesUtil.getReturnTypeName(methodToGetParameter)
            ));
        });
        if (Objects.isNotEmpty(unsupportedLocations)) {
            String msg = "MapstructBeanConverter：检测到有相同出入参的方法，对于这些方法无法使用通用转换，需要直接调用，如下：\n" + StringsUtil.pretty(unsupportedLocations, "\n");
            Assert.INSTANCE.set(new MapstructConvertMethodUnsupportedException(msg)).throwsIfFalse(warnOrThrow);
            log.warn(msg);
        }
    }

    /**
     * 普通 Bean 一对一转换
     *
     * @param source 待转换类
     * @param target 目标类
     * @param <T1>   待转换类类型
     * @param <T2>   目标类类型
     * @return 目标值
     */
    public <T1, T2> T2 toBean(@Nullable T1 source, @Nullable Class<T2> target) {
        return toBean(source, target, (T2) DEFAULT_TARGET_VALUE);
    }

    /**
     * 普通 Bean 一对一转换
     *
     * @param source       待转换类
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param <T1>         待转换类类型
     * @param <T2>         目标类类型
     * @return 目标值
     */
    public <T1, T2> T2 toBean(@Nullable T1 source, @Nullable Class<T2> target, @Nullable T2 defaultValue) {
        return toBean(source, target, defaultValue, DEFAULT_CONVERT_QUIETLY);
    }

    /**
     * 普通 Bean 一对一转换
     *
     * @param source  待转换类
     * @param target  目标类
     * @param quietly 是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>    待转换类类型
     * @param <T2>    目标类类型
     * @return 目标值
     */
    public <T1, T2> T2 toBean(@Nullable T1 source, @Nullable Class<T2> target, @Nullable Boolean quietly) {
        return toBean(source, target, (T2) DEFAULT_TARGET_VALUE, quietly);
    }

    /**
     * 普通 Bean 一对一转换
     *
     * @param source       待转换类
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param quietly      是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>         待转换类类型
     * @param <T2>         目标类类型
     * @return 目标值
     */
    public <T1, T2> T2 toBean(@Nullable T1 source, @Nullable Class<T2> target, @Nullable T2 defaultValue, @Nullable Boolean quietly) {
        if (Objects.isNull(source, target)) {
            return defaultValue;
        }
        return (T2) convert(target, defaultValue, quietly, source);
    }

    /**
     * 普通 Bean 二对一转换
     *
     * @param source1 待转换类1
     * @param source2 待转换类2
     * @param target  目标类
     * @param <T1>    待转换类类型1
     * @param <T2>    待转换类类型2
     * @param <T3>    目标类类型
     * @return 目标值
     */
    public <T1, T2, T3> T3 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable Class<T3> target) {
        return toBean(source1, source2, target, (T3) DEFAULT_TARGET_VALUE);
    }

    /**
     * 普通 Bean 二对一转换
     *
     * @param source1      待转换类1
     * @param source2      待转换类2
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param <T1>         待转换类类型1
     * @param <T2>         待转换类类型2
     * @param <T3>         目标类类型
     * @return 目标值
     */
    public <T1, T2, T3> T3 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable Class<T3> target, @Nullable T3 defaultValue) {
        return toBean(source1, source2, target, defaultValue, DEFAULT_CONVERT_QUIETLY);
    }

    /**
     * 普通 Bean 二对一转换
     *
     * @param source1 待转换类1
     * @param source2 待转换类2
     * @param target  目标类
     * @param quietly 是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>    待转换类类型1
     * @param <T2>    待转换类类型2
     * @param <T3>    目标类类型
     * @return 目标值
     */
    public <T1, T2, T3> T3 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable Class<T3> target, @Nullable Boolean quietly) {
        return toBean(source1, source2, target, (T3) DEFAULT_TARGET_VALUE, quietly);
    }

    /**
     * 普通 Bean 二对一转换
     *
     * @param source1      待转换类1
     * @param source2      待转换类2
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param quietly      是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>         待转换类1类型
     * @param <T2>         待转换类2类型
     * @param <T3>         目标类类型
     * @return 目标值
     */
    public <T1, T2, T3> T3 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable Class<T3> target, @Nullable T3 defaultValue, @Nullable Boolean quietly) {
        if (Objects.isNull(source1, source2, target)) {
            return defaultValue;
        }
        return (T3) convert(target, defaultValue, quietly, source1, source2);
    }

    /**
     * 普通 Bean 三对一转换
     *
     * @param source1 待转换类1
     * @param source2 待转换类2
     * @param source3 待转换类3
     * @param target  目标类
     * @param <T1>    待转换类1类型
     * @param <T2>    待转换类2类型
     * @param <T3>    待转换类3类型
     * @param <T4>    目标类类型
     * @return 目标值
     */
    public <T1, T2, T3, T4> T4 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable T3 source3, @Nullable Class<T4> target) {
        return toBean(source1, source2, source3, target, (T4) DEFAULT_TARGET_VALUE);
    }

    /**
     * 普通 Bean 三对一转换
     *
     * @param source1      待转换类1
     * @param source2      待转换类2
     * @param source3      待转换类3
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param <T1>         待转换类1类型
     * @param <T2>         待转换类2类型
     * @param <T3>         待转换类3类型
     * @param <T4>         目标类类型
     * @return 目标值
     */
    public <T1, T2, T3, T4> T4 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable T3 source3, @Nullable Class<T4> target, @Nullable T4 defaultValue) {
        return toBean(source1, source2, source3, target, defaultValue, DEFAULT_CONVERT_QUIETLY);
    }

    /**
     * 普通 Bean 三对一转换
     *
     * @param source1 待转换类1
     * @param source2 待转换类2
     * @param source3 待转换类3
     * @param target  目标类
     * @param quietly 是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>    待转换类1类型
     * @param <T2>    待转换类2类型
     * @param <T3>    待转换类3类型
     * @param <T4>    目标类类型
     * @return 目标值
     */
    public <T1, T2, T3, T4> T4 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable T3 source3, @Nullable Class<T4> target, @Nullable Boolean quietly) {
        return toBean(source1, source2, source3, target, (T4) DEFAULT_TARGET_VALUE, quietly);
    }

    /**
     * 普通 Bean 三对一转换
     *
     * @param source1      待转换类1
     * @param source2      待转换类2
     * @param source3      待转换类3
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param quietly      是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>         待转换类1类型
     * @param <T2>         待转换类2类型
     * @param <T3>         待转换类3类型
     * @param <T4>         目标类类型
     * @return 目标值
     */
    public <T1, T2, T3, T4> T4 toBean(@Nullable T1 source1, @Nullable T2 source2, @Nullable T3 source3, @Nullable Class<T4> target, @Nullable T4 defaultValue, @Nullable Boolean quietly) {
        if (Objects.isNull(source1, source2, source3, target)) {
            return defaultValue;
        }
        return (T4) convert(target, defaultValue, quietly, source1, source2, source3);
    }

    /**
     * List Bean 一对一转换
     *
     * @param source 待转换类
     * @param target 目标类
     * @param <T1>   待转换类类型
     * @param <T2>   目标类类型
     * @return 目标值
     */
    @NonNull
    public <T1, T2> List<T2> toBeans(@Nullable List<T1> source, @Nullable Class<T2> target) {
        return toBeans(source, target, DEFAULT_CONVERT_QUIETLY);
    }

    /**
     * List Bean 一对一转换
     *
     * @param source       待转换类
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param <T1>         待转换类类型
     * @param <T2>         目标类类型
     * @return 目标值
     */
    @NonNull
    public <T1, T2> List<T2> toBeans(@Nullable List<T1> source, @Nullable Class<T2> target, @Nullable T2 defaultValue) {
        return toBeans(source, target, defaultValue, DEFAULT_CONVERT_QUIETLY);
    }

    /**
     * List Bean 一对一转换
     *
     * @param source  待转换类
     * @param target  目标类
     * @param quietly 是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>    待转换类类型
     * @param <T2>    目标类类型
     * @return 目标值
     */
    @NonNull
    public <T1, T2> List<T2> toBeans(@Nullable List<T1> source, @Nullable Class<T2> target, @Nullable Boolean quietly) {
        return toBeans(source, target, (T2) DEFAULT_TARGET_VALUE, quietly);
    }

    /**
     * List Bean 一对一转换
     *
     * @param source       待转换类
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param quietly      是否静默转换，即报错不抛出异常，只打印日志
     * @param <T1>         待转换类类型
     * @param <T2>         目标类类型
     * @return 目标值
     */
    @NonNull
    public <T1, T2> List<T2> toBeans(@Nullable List<T1> source, @Nullable Class<T2> target, @Nullable T2 defaultValue, @Nullable Boolean quietly) {
        if (Objects.isEmpty(source, target)) {
            return (List<T2>) MapstructListBeanConvertsSupporter.INSTANCE.getDefaultValue(defaultValue);
        }
        return (List<T2>) convert(target, defaultValue, quietly, source);
    }

    /**
     * 统一转换
     *
     * @param target       目标类
     * @param defaultValue 所有异常情况时的默认目标值
     * @param quietly      是否静默转换，即报错不抛出异常，只打印日志
     * @param sources      待转换类
     * @param <T>          目标类类型
     * @return 目标值
     */
    public <T> Object convert(@NonNull Class<T> target, @Nullable T defaultValue, @Nullable Boolean quietly, @NonNull Object... sources) {
        try {
            MapstructConvertsSupporter mapstructConvertsSupporter = MapstructConvertsSupporterManager.getSupporter(sources[0]);
            Method convertMethod = ensureGetMatchedMethod(mapstructConvertsSupporter, target, sources);
            Class<?> convertMethodClass = convertMethod.getDeclaringClass();

            Object convertMethodObject = MAPSTRUCT_CLASS_NAME_MAPPING_MAPSTRUCT_CLASS_MAP.get(convertMethodClass.getName());
            if (Objects.isNull(convertMethodObject)) {
                convertMethodObject = SpringsUtil.getBean(convertMethodClass);
                MAPSTRUCT_CLASS_NAME_MAPPING_MAPSTRUCT_CLASS_MAP.put(convertMethodClass.getName(), convertMethodObject);
            }

            MethodAccess methodAccess = MAPSTRUCT_CLASS_NAME_MAPPING_ASM_REFLECT_MAP.get(convertMethodClass.getName());
            if (Objects.isNull(methodAccess)) {
                methodAccess = MethodAccess.get(convertMethodClass);
                MAPSTRUCT_CLASS_NAME_MAPPING_ASM_REFLECT_MAP.put(convertMethodClass.getName(), methodAccess);
            }

            return methodAccess.invoke(convertMethodObject, convertMethod.getName(), sources);
        } catch (Exception e) {
            if (Objects.isTrue(quietly)) {
                log.error(StringPool.EMPTY, e);
                return getDefaultValue(defaultValue, sources[0]);
            }
            throw e;
        }
    }

}

