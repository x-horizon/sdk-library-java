//package cn.srd.itcp.sugar.cache.all.support;
//
//import cn.srd.itcp.sugar.cache.all.core.EnableMultilevelCaching;
//import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
//import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
//import cn.srd.itcp.sugar.tool.core.Objects;
//import cn.srd.itcp.sugar.tool.core.StringsUtil;
//import org.springframework.context.annotation.Condition;
//import org.springframework.context.annotation.ConditionContext;
//import org.springframework.core.type.AnnotatedTypeMetadata;
//import org.springframework.lang.NonNull;
//
//import java.util.Set;
//
///**
// * @author wjm
// * @since 2023-06-07 16:48:52
// */
//public class EnableMultilevelCachingCondition implements Condition {
//
//    @Override
//    public boolean matches(@NonNull ConditionContext context, @NonNull AnnotatedTypeMetadata metadata) {
//        Set<Class<?>> classesWithEnableMultilevelCaching = SpringsUtil.scanPackageByAnnotation(EnableMultilevelCaching.class);
//        Objects.requireTrue(
//                () -> StringsUtil.format("init multilevel cache system failed, please check, the failed message is: [@{}] was marked on multi classes [{}]", EnableMultilevelCaching.class.getSimpleName(), StringsUtil.pretty(CollectionsUtil.toList(classesWithEnableMultilevelCaching, Class::getSimpleName))),
//                classesWithEnableMultilevelCaching.size() <= 1
//        );
//        return classesWithEnableMultilevelCaching.size() == 1;
//    }
//
//}
