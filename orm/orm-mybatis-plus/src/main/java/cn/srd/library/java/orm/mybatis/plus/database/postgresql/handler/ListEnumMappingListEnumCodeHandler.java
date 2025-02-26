package cn.srd.library.java.orm.mybatis.plus.database.postgresql.handler;

import cn.srd.library.java.tool.lang.core.EnumsUtil;
import cn.srd.library.java.tool.lang.core.StringsUtil;
import cn.srd.library.java.tool.lang.core.object.Objects;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 适用于 Java 字段的数据类型为 {@link List<Enum<E>>} &lt;==&gt; PostgreSQL 字段的数据类型为 jsonb 的相互转换，用法参考：{@link ListObjectMappingJsonbTypeHandler}
 *
 * @author xiongjing
 * @since 2023-05-09 10:35:42
 */
public abstract class ListEnumMappingListEnumCodeHandler<E extends Enum<E>> extends BaseTypeHandler<List<E>> implements JsonbHandler<E> {

    /**
     * 目标枚举类
     *
     * @return 目标枚举类
     */
    public abstract Class<E> getTargetEnumClass();

    /**
     * 定义如何把 Java 类型的参数转换为指定的数据库类型
     *
     * @param preparedStatement SQL 预编译对象
     * @param columnIndex       字段索引
     * @param parameter         自定义参数
     * @param jdbcType          JDBC 数据类型
     */
    @Override
    @SneakyThrows
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, List<E> parameter, JdbcType jdbcType) {
        if (Objects.isNotNull(parameter)) {
            List<Integer> enumIntegerValues = new ArrayList<>();
            for (E item : parameter) {
                enumIntegerValues.add(EnumsUtil.getEnumValue(item, Integer.class));
            }
            preparedStatement.setObject(columnIndex, convertObjectToJsonb(enumIntegerValues));
        }
    }

    /**
     * 定义通过字段名称获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param resultSet  结果集
     * @param columnName 字段名称
     * @return 转换结果
     */
    @Override
    @SneakyThrows
    public List<E> getNullableResult(ResultSet resultSet, String columnName) {
        return StringsUtil.splitToListEnum(resultSet.getArray(columnName).toString(), getTargetEnumClass());
    }

    /**
     * 定义通过字段索引获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param resultSet   结果集
     * @param columnIndex 字段索引
     * @return 转换结果
     */
    @SneakyThrows
    @Override
    public List<E> getNullableResult(ResultSet resultSet, int columnIndex) {
        return StringsUtil.splitToListEnum(resultSet.getArray(columnIndex).toString(), getTargetEnumClass());
    }

    /**
     * 定义通过存储过程获取字段数据时，如何把数据库类型转换为指定的 Java 类型
     *
     * @param callableStatement 存储过程执行对象
     * @param columnIndex       字段索引
     * @return 转换结果
     */
    @SneakyThrows
    @Override
    public List<E> getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return StringsUtil.splitToListEnum(callableStatement.getArray(columnIndex).toString(), getTargetEnumClass());
    }

}

