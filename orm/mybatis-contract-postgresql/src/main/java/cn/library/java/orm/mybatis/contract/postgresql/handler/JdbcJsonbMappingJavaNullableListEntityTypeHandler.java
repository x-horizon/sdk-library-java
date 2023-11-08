// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.srd.library.java.tool.convert.jackson.NullableObject;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;

import java.util.List;

/**
 * <pre>
 * the postgresql jdbc jsonb data type and java list object data type mapping relation type handler.
 *
 * 1. the postgresql sql contain jsonb like array [] as following:
 * {@code
 *     CREATE TABLE example
 *     (
 *         id          BIGINT                      NOT NULL,
 *         detail_infos JSONB  DEFAULT '[]'::JSONB NOT NULL, -- the value like [{"name": "myName1", "age": 18}, {"name": "myName2", "age": 18}]
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
 *         @Column(value = "detail_info", typeHandler = JdbcJsonbMappingJavaListEntityTypeHandler.class)
 *         private List<DetailPO> detailPOs;
 *
 *     }
 * }
 * </pre>
 *
 * <strong><em>note: the core of the postgresql jdbc jsonb data type and java list object data type mapping relation is:<br/></em></strong>
 * <p>
 * <strong><em>@Column(value = "detail_info", typeHandler = JdbcJsonbMappingJavaListEntityTypeHandler.class)</em></strong>
 * </p>
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
 * when the field detailPOs value in the class ExamplePO is null or empty, it will set "[]" into postgresql.<br/>
 * when the field detailPOs value in the class ExamplePO is not empty, but some element detailPOs {@link NullableObject#isNull()} return true,<br/>
 * it will be filtered out and not set into postgresql.<br/>
 * </em></strong>
 * </p>
 * <br/>
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2022-09-07 10:35
 */
public class JdbcJsonbMappingJavaNullableListEntityTypeHandler<T extends NullableObject> extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<T> {

    @Override
    protected Object doConvertToJdbcObject(List<T> javaObjects) {
        return Nil.isNull(javaObjects) ?
                Collections.newImmutableList() :
                javaObjects.stream().filter(NullableObject::isNotNull).toList();
    }

}
