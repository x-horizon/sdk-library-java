// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;

import java.util.List;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java list enum mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like array [] as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id    BIGINT                     NOT NULL,
 *         types JSONB  DEFAULT '[]'::JSONB NOT NULL, -- the value like [1, 2, 3]
 *         PRIMARY KEY (id)
 *     );
 * }
 *
 * 2. the java object as following:
 * {@code
 *     @Data
 *     // need to replace this annotation from the specified orm framework
 *     @OrmFrameworkTableMarkedDemo(tableName = "example")
 *     public class ExamplePO implements Serializable {
 *
 *         @Serial private static final long serialVersionUID = -7680901283684311918L;
 *
 *         // need to replace this annotation from the specified orm framework
 *         @OrmFrameworkIdMarkedDemo
 *         @OrmFrameworkColumnMarkedDemo(columnName = "id")
 *         private Long id;
 *
 *         // need to replace this annotation from the specified orm framework
 *         // add the type handler
 *         @OrmFrameworkColumnMarkedDemo(columnName = "types", typeHandler = JdbcJsonbMappingJavaListEnumIntegerTypeHandler.class)
 *         private List<TypeEnum> types;
 *
 *     }
 * }
 *
 * 3. the enum mapping postgresql jdbc jsonb as following:
 * {@code
 *     @Getter
 *     @AllArgsConstructor
 *     public class TypeEnum {
 *
 *         A(1),
 *         B(2),
 *         C(3),
 *         ;
 *
 *         @EnumValue
 *         private final int code;
 *
 *     }
 * }
 * </pre>
 *
 * <h2>note: the core of the postgresql jdbc jsonb data type and java list enum mapping relation is:</h2>
 * <strong><em>@OrmFrameworkColumnMarkedDemo(columnName = "types", typeHandler = JdbcJsonbMappingJavaListEnumIntegerTypeHandler.class)</em></strong>
 * <p/>
 *
 * @param <E> the enum data type
 * @author xiongjing
 * @since 2023-05-09 10:35
 */
public class JdbcJsonbMappingJavaListEnumIntegerTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<E> {

    @Override
    protected Object doConvertToJdbcObject(List<E> javaObjects) {
        return Nil.isNull(javaObjects) ?
                Collections.newImmutableList() :
                javaObjects.stream().map(javaObject -> Enums.getFieldValue(javaObject, Integer.class)).toList();
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected List<E> doConvertToJavaObject(String columnValue, Class javaType) {
        return Strings.splitToEnums(Strings.removeHeadTailBracket(columnValue), javaType);
    }

}
