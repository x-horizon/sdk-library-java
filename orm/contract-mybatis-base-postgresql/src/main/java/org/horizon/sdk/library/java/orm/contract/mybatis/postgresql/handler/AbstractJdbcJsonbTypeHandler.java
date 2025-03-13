package org.horizon.sdk.library.java.orm.contract.mybatis.postgresql.handler;

import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.horizon.sdk.library.java.contract.constant.database.PostgresqlDataType;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.handler.AbstractJdbcComplexTypeHandler;
import org.horizon.sdk.library.java.orm.contract.mybatis.base.type.JdbcComplexType;
import org.horizon.sdk.library.java.tool.convert.api.Converts;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.postgresql.util.PGobject;

import java.util.Optional;
import java.util.Set;

/**
 * the postgresql jdbc jsonb abstract type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-06 18:29
 */
public abstract class AbstractJdbcJsonbTypeHandler<T> extends AbstractJdbcComplexTypeHandler<T> {

    /**
     * return true if the postgresql jdbc jsonb column value is empty
     *
     * @param columnValue the postgresql jdbc jsonb column value
     * @return return true if the postgresql jdbc jsonb column value is empty
     */
    protected abstract boolean isEmptyJsonbColumnValue(String columnValue);

    /**
     * convert to java object when the postgresql jdbc jsonb column value is empty
     *
     * @return java object
     */
    protected abstract T toJavaObjectWhenEmptyJsonbColumnValue();

    /**
     * convert to java object by column value and java type
     *
     * @param columnValue the column value
     * @param javaType    the java type
     * @return java object
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    protected abstract T doConvertToJavaObject(String columnValue, Class javaType);

    /**
     * convert to postgresql jdbc jsonb object
     *
     * @param javaObject the java object
     * @return postgresql jdbc jsonb object
     */
    protected Object doConvertToJdbcObject(T javaObject) {
        return javaObject;
    }

    @SneakyThrows
    @Override
    protected Object toJdbcObject(T javaObject) {
        return toPostgresqlObject(Converts.onJackson().toString(doConvertToJdbcObject(javaObject)));
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @SneakyThrows
    @Override
    protected T toJavaObject(String columnName, String columnValue) {
        if (isEmptyJsonbColumnValue(columnValue)) {
            return toJavaObjectWhenEmptyJsonbColumnValue();
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
     * convert to postgresql object
     *
     * @param content the content
     * @return postgresql object
     */
    @SneakyThrows
    protected PGobject toPostgresqlObject(String content) {
        PGobject pgObject = new PGobject();
        pgObject.setType(PostgresqlDataType.JSONB.getValue());
        pgObject.setValue(content);
        return pgObject;
    }

}