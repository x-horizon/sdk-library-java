package cn.srd.itcp.sugar.mybatis.plus.database.postgresql.handler;

import cn.srd.itcp.sugar.tools.core.Objects;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <pre>
 * {@link T} 与 PostgreSQL 中 JSONB 类型字段的类型映射处理器；
 * 适用于 Java 字段的数据类型为 {@link T} &lt;==&gt; PostgreSQL 字段的数据类型为 JSONB 的相互转换；
 *
 * 使用方式：
 *
 * 1、存在一张表名为 example 的表，该表中包含一个字段名为 example_json_object，数据类型为 JSONB 的字段，以下为该表对应的持久化模型，其中，
 *    ①、example_json_object 对应的 java 字段名为 exampleJsonObject；
 *    ②、使用 ExampleJsonObject 实体类来描述数据库类型为 JSONB 对应的 Java 类型
 *
 *      &#064;Data
 *      &#064;Accessors(chain = true)
 *      &#064;TableName(value = "example", autoResultMap = true)
 *      public class ExamplePO implements Serializable {
 *
 *          &#064;Serial
 *          private static final long serialVersionUID = -1366954660147806379L;
 *
 *          &#064;TableId(value = "example_id")
 *          private Long exampleId;
 *
 *          &#064;TableField(value = "example_json_object", typeHandler = ExampleJsonObjectMappingJsonbTypeHandler.class)
 *          private ExampleJsonObject exampleJsonObject;
 *
 *          &#064;Data
 *          &#064;Accessors(chain = true)
 *          public static final class ExampleJsonObject implements Serializable {
 *
 *              &#064;Serial
 *              private static final long serialVersionUID = 1529342927734057832L;
 *
 *              private String exampleName1;
 *
 *              private String exampleName2;
 *
 *          }
 *
 *      }
 *
 * 2、上述的持久化模型中，exampleJsonObject 字段上的 &#064;TableField 注解中的 typeHandler 字段值对应以下 typeHandler，即需要显式指定需要映射的 Java 类型：
 *
 *      public class exampleJsonObjectMappingJsonbTypeHandler&lt;T&gt; extends {@link ObjectMappingJsonbTypeHandler<T>} {
 *
 *          &#064;Override
 *          public Class&lt;T&gt; {@link ObjectMappingJsonbTypeHandler#getTargetClass()} {
 *              return ExampleJsonObject.class;
 *          }
 *
 *      }
 *
 * 3、完成上述配置后，即可成功建立 Java 实体类与映射数据库 JSONB 类型的相互映射；
 *
 * </pre>
 *
 * @author wjm
 * @since 2022-09-07 10:35:42
 */
public abstract class ObjectMappingJsonbTypeHandler<T> extends BaseTypeHandler<T> implements JsonbHandler<T> {

    /**
     * see {@link JsonbHandler#getTargetClass()}
     *
     * @return 目标类的类型
     */
    public abstract Class<T> getTargetClass();

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
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, T parameter, JdbcType jdbcType) {
        if (Objects.isNotNull(parameter)) {
            preparedStatement.setObject(columnIndex, convertObjectToJsonb(parameter));
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
    public T getNullableResult(ResultSet resultSet, String columnName) {
        return convertJsonbStringToObject(resultSet.getString(columnName));
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
    public T getNullableResult(ResultSet resultSet, int columnIndex) {
        return convertJsonbStringToObject(resultSet.getString(columnIndex));
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
    public T getNullableResult(CallableStatement callableStatement, int columnIndex) {
        return convertJsonbStringToObject(callableStatement.getString(columnIndex));
    }

}

