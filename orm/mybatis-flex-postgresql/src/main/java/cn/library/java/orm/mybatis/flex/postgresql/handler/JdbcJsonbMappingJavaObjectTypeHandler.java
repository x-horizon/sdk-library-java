// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.handler;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbTypeHandler;
import cn.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingRelationCache;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.convert.jackson.NullableObject;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import io.vavr.control.Try;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java object data type mapping relation type handler.
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
 *      public class exampleJsonObjectMappingJsonbTypeHandler&lt;T&gt; extends {@link cn.library.java.orm.mybatis.contract.postgresql.handler.JavaObjectMappingJdbcJsonbTypeHandler <T>} {
 *
 *          &#064;Override
 *          public Class&lt;T&gt; {@link cn.library.java.orm.mybatis.contract.postgresql.handler.JavaObjectMappingJdbcJsonbTypeHandler#getTargetClass()} {
 *              return ExampleJsonObject.class;
 *          }
 *
 *      }
 *
 * 3、完成上述配置后，即可成功建立 Java 实体类与映射数据库 JSONB 类型的相互映射；
 *
 * </pre>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35:42
 */
public class JdbcJsonbMappingJavaObjectTypeHandler<T extends NullableObject> extends JdbcJsonbTypeHandler<T> {

    @Override
    protected Object toJdbcObject(T javaObject) {
        return toPGobject(Nil.isNull(javaObject) || javaObject.isNull() ? Map.of() : javaObject);
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @SneakyThrows
    @Override
    protected T toJavaObject(ResultSet resultSet, String columnName) {
        String jsonbString = resultSet.getString(columnName);
        if (Collections.isBlankOrEmptyMapString(jsonbString)) {
            return null;
        }
        Set<Class> javaClasses = ColumnJsonbMappingRelationCache.getInstance().getMappingJavaClass(columnName);
        return javaClasses.stream()
                .map(javaType -> Optional.ofNullable((T) Try.of(() -> Converts.withJackson().toBean(jsonbString, javaType)).getOrNull()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> whenFailed(columnName, javaClasses));
    }

}
