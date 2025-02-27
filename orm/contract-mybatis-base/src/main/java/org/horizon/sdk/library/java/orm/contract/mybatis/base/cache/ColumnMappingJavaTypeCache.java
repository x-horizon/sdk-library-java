package org.horizon.sdk.library.java.orm.contract.mybatis.base.cache;

import lombok.Getter;
import org.apache.ibatis.type.TypeHandler;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.functional.Assert;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.object.Types;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.spring.contract.support.Annotations;
import org.horizon.sdk.library.java.tool.spring.contract.support.Classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * the complex database column data type and java type mapping cache
 *
 * @author wjm
 * @since 2023-11-07 16:50
 */
public abstract class ColumnMappingJavaTypeCache {

    /**
     * the database column name and java type mapping cache, like: {"detail_info": [org.horizon.sdk.library.java.test.DetailPO]}
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Getter
    private final Map<String, Set<Class>> cache =
            Classes.scanByBasePackagePath()
                    .stream()
                    .map(Classes::getFields)
                    .flatMap(Collection::stream)
                    // select fields with the specified orm framework annotation marked column
                    .filter(field -> Annotations.hasAnnotation(field, getTypeHandlerLocatedAnnotation()))
                    // select fields marked complex jdbc data type
                    .filter(field -> Classes.isAssignable(Annotations.getAnnotationValue(field, getTypeHandlerLocatedAnnotation(), getTypeHandlerLocatedAnnotationFieldName()), getAbstractTypeHandler()))
                    // convert to column name and java type mapping
                    .map(field -> Collections.ofPair(getTypeHandlerAnnotatedFieldColumnName(field), (Class) getTypeHandlerAnnotatedFieldType(field)))
                    .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toSet())));

    /**
     * get java type by column name from cache
     *
     * @param columnName the specified column name
     * @return the java type
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    public Set<Class> getMappingJavaTypes(String columnName) {
        Set<Class> javaClasses = cache.get(columnName);
        Assert.of().setMessage("{}could not find column mapping java type by column name [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, columnName)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfEmpty(javaClasses);
        return javaClasses;
    }

    /**
     * get the complex type handler
     *
     * @return the complex type handler
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    protected abstract Class<? extends TypeHandler> getAbstractTypeHandler();

    /**
     * get the orm framework annotation marked column
     *
     * @return the orm framework annotation marked column
     */
    protected abstract Class<? extends Annotation> getTypeHandlerLocatedAnnotation();

    /**
     * get the complex type handler field name in orm framework annotation
     *
     * @return the complex type handler field name in orm framework annotation
     */
    protected String getTypeHandlerLocatedAnnotationFieldName() {
        return "typeHandler";
    }

    /**
     * <pre>
     * get the field data type with the orm framework annotation marked column
     * for example:
     *
     * {@code
     *     // will return cn.xxx.DetailPO from the field detailPO
     *     @OrmFrameworkColumnMarkedDemo(typeHandler = ComplexTypeHandlerDemo.class)
     *     private DetailPO detailPO;
     *
     *     // will return cn.xxx.DetailPO from the field detailPOs
     *     @OrmFrameworkColumnMarkedDemo(typeHandler = ComplexTypeHandlerDemo.class)
     *     private List<DetailPO> detailPOs;
     * }
     * </pre>
     *
     * @param annotatedField the field with the orm framework annotation marked column
     * @return the field actual data type
     */
    private Class<?> getTypeHandlerAnnotatedFieldType(Field annotatedField) {
        if (Classes.isAssignable(annotatedField.getType(), Map.class)) {
            return Map.class;
        }
        if (Types.hasGeneric(annotatedField)) {
            return Types.getEmbedGenericTypeClass(annotatedField.getDeclaringClass(), annotatedField.getName());
        }
        return annotatedField.getType();
    }

    /**
     * <pre>
     * get the column name with the orm framework annotation marked column
     * for example:
     *
     * {@code
     *     // will return "detail_info" from the orm framework annotation field columnName value
     *     @OrmFrameworkColumnMarkedDemo(columnName = "detail_info", typeHandler = ComplexTypeHandlerDemo.class)
     *     private DetailPO detailPO;
     *
     *     // will return "detail_pos" from the field under line case name because the orm framework annotation field columnName is not specified
     *     @OrmFrameworkColumnMarkedDemo(typeHandler = ComplexTypeHandlerDemo.class)
     *     private List<DetailPO> detailPOs;
     * }
     * </pre>
     *
     * @param annotatedField the field with the orm framework annotation marked column
     * @return the column name
     */
    private String getTypeHandlerAnnotatedFieldColumnName(Field annotatedField) {
        String columnName = Annotations.getAnnotationValue(annotatedField, getTypeHandlerLocatedAnnotation(), String.class);
        return Nil.isBlank(columnName) ? Strings.underlineCase(annotatedField.getName()) : columnName;
    }

}