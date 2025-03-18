package org.horizon.sdk.library.java.orm.contract.mybatis.base.handler;

import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.type.JdbcComplexType;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.lang.text.Strings;

import java.util.Optional;
import java.util.Set;

/**
 * the jdbc json abstract type handler
 *
 * @param <T> the java object data type
 * @param <J> the jdbc object data type
 * @author wjm
 * @since 2023-11-06 18:29
 */
public abstract class AbstractJdbcJsonTypeHandler<T, J> extends AbstractJdbcComplexTypeHandler<T, J> {

    /**
     * return true if the jdbc json column value is empty
     *
     * @param columnValue the jdbc json column value
     * @return return true if the jdbc json column value is empty
     */
    protected abstract boolean isEmptyJsonColumnValue(String columnValue);

    /**
     * convert to java object when the jdbc json column value is empty
     *
     * @return java object
     */
    protected abstract T toJavaObjectWhenEmptyJsonColumnValue();

    /**
     * convert to java object by column value and java type
     *
     * @param columnValue the column value
     * @param javaType    the java type
     * @return java object
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    protected abstract T doConvertToJavaObject(String columnValue, Class javaType);

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @SneakyThrows
    @Override
    protected T toJavaObject(String columnName, String columnValue) {
        if (isEmptyJsonColumnValue(columnValue)) {
            return toJavaObjectWhenEmptyJsonColumnValue();
        }
        Set<Class> javaTypes = JdbcComplexType.JSON.getColumnMappingJavaTypeCache().getMappingJavaTypes(columnName);
        return javaTypes.stream()
                .map(javaType -> Optional.ofNullable(Try.of(() -> doConvertToJavaObject(columnValue, javaType)).getOrNull()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new LibraryJavaInternalException(Strings.format(
                        "{}could not convert the value of column name [{}] to following classes {}, please check!",
                        ModuleView.ORM_MYBATIS_SYSTEM,
                        columnName,
                        javaTypes.stream().map(Class::getName).toList()
                )));
    }

    /**
     * convert to jdbc object by java object content
     *
     * @param javaObjectContent the java object content
     * @return jdbc object
     */
    protected abstract J toJdbcObjectByStringContent(String javaObjectContent);

    @Override
    protected J toJdbcObject(T javaObject) {
        return toJdbcObjectByStringContent(toJdbcObjectContent(javaObject));
    }

    /**
     * convert to jdbc object content
     *
     * @param javaObject the java object
     * @return jdbc object content
     */
    protected String toJdbcObjectContent(T javaObject) {
        return Converts.onJackson().toString(doConvertToJdbcObject(javaObject));
    }

    /**
     * convert to jdbc json object
     *
     * @param javaObject the java object
     * @return jdbc json object
     */
    protected Object doConvertToJdbcObject(T javaObject) {
        return javaObject;
    }

}