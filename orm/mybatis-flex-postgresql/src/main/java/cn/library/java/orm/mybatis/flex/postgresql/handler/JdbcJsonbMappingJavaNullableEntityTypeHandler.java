// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.flex.postgresql.handler;

import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbMappingJavaObjectAbstractTypeHandler;
import cn.library.java.orm.mybatis.flex.postgresql.cache.ColumnJsonbMappingRelationCache;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.jackson.NullableObject;
import cn.srd.library.java.tool.lang.object.Nil;

import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java object data type mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like map {} as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id          BIGINT                     NOT NULL,
 *         detail_info JSONB  DEFAULT '{}'::JSONB NOT NULL, -- the value like {"name": "myName", "age": 18}
 *         PRIMARY KEY (id)
 *     );
 * }
 *
 * 2. the java po object as following:
 * {@code
 *     @Data
 *     @Table(value = "example")
 *     public class ExamplePO implements Serializable {
 *
 *         @Serial private static final long serialVersionUID = -7680901283684311918L;
 *
 *         @Id
 *         @Column(value = "id")
 *         private Long id;
 *
 *         // add the type handler
 *         @Column(value = "detail_info", typeHandler = JdbcJsonbMappingJavaEntityTypeHandler.class)
 *         private DetailPO detailPO;
 *
 *     }
 * }
 * </pre>
 *
 * <strong><em>note: the core of the postgresql jdbc jsonb data type and java object data type mapping relation is:</em></strong>
 * <strong><em>@Column(value = "detail_info", typeHandler = JdbcJsonbMappingJavaEntityTypeHandler.class)</em></strong>
 *
 * <pre>
 *
 * 3. the java po object mapping postgresql jdbc jsonb as following:
 * {@code
 *     @Data
 *     public class DetailPO implements NullableObject, Serializable {
 *
 *         @Serial private static final long serialVersionUID = -88531220073385451L;
 *
 *         @Override
 *         public boolean isNull() {
 *             return false;
 *         }
 *
 *         private String name;
 *
 *         private Short age;
 *
 *     }
 * }
 * </pre>
 *
 * <strong><em>note: about the usage of implement class {@link NullableObject}:</em></strong>
 * <p>
 * <strong><em>
 * when storing this class into postgresql,<br/>
 * it provides an opportunity to represent the condition that the field in postgresql is empty,<br/>
 * when the field detailPO value in the class ExamplePO is null,<br/>
 * or {@link NullableObject#isNull()} return true,<br/>
 * it will set "{}" into postgresql.<br/>
 * </em></strong>
 * </p>
 * <br/>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public class JdbcJsonbMappingJavaNullableEntityTypeHandler<T extends NullableObject> extends JdbcJsonbMappingJavaObjectAbstractTypeHandler<T> {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Set<Class> getMappingJavaTypes(String columnName) {
        return ColumnJsonbMappingRelationCache.getInstance().getMappingJavaTypes(columnName);
    }

    @Override
    protected Object doConvertToJdbcObject(T javaObject) {
        return Nil.isNull(javaObject) || javaObject.isNull() ? Map.of() : javaObject;
    }

}
