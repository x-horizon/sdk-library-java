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
@Getter
public abstract class ColumnMappingJavaTypeCache {

    /**
     * the database column name and java type mapping cache, like: {"detail_info": [org.horizon.sdk.library.java.test.DetailPO]}
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
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
     * <p>get the field data type with the ORM framework annotation marked column.</p>
     *
     * <p>usage examples:</p>
     * <pre>{@code
     * // return cn.xxx.DetailPO for detailPO field
     * @YourOrmColumn(typeHandler = ComplexTypeHandlerDemo.class)
     * private DetailPO detailPO;
     *
     * // return cn.xxx.DetailPO for detailPOs field (generic type resolution)
     * @YourOrmColumn(typeHandler = ComplexTypeHandlerDemo.class)
     * private List<DetailPO> detailPOs;
     * }</pre>
     *
     * <p>key features:</p>
     * <ul>
     *  <li>supports both simple types and generic collection types</li>
     *  <li>automatically resolves actual type arguments for generic fields</li>
     *  <li>works with various ORM framework annotations</li>
     * </ul>
     *
     * @param annotatedField the field annotated with ORM framework column marker
     * @return the resolved actual data type of the field, including generic information
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
     * <p>get the column name with the ORM framework annotation marked column.</p>
     *
     * <p>usage examples:</p>
     * <pre>{@code
     * // return "detail_info" (explicit columnName value)
     * @YourOrmColumn(
     *     columnName = "detail_info",
     *     typeHandler = ComplexTypeHandlerDemo.class
     * )
     * private DetailPO detailPO;
     *
     * // return "detail_pos" (auto-converted camelCase to snake_case)
     * @YourOrmColumn(typeHandler = ComplexTypeHandlerDemo.class)
     * private List<DetailPO> detailPOs;
     * }</pre>
     *
     * <p>key features:</p>
     * <ul>
     *  <li>priority use of explicit columnName from annotation</li>
     *  <li>auto-convert camelCase field names to snake_case when columnName not specified</li>
     *  <li>support both simple fields and generic collection types</li>
     * </ul>
     *
     * @param annotatedField the field annotated with ORM framework column marker
     * @return the resolved column name, either explicitly defined or automatically converted
     */
    private String getTypeHandlerAnnotatedFieldColumnName(Field annotatedField) {
        String columnName = Annotations.getAnnotationValue(annotatedField, getTypeHandlerLocatedAnnotation(), String.class);
        return Nil.isBlank(columnName) ? Strings.underlineLowerCase(annotatedField.getName()) : columnName;
    }

}