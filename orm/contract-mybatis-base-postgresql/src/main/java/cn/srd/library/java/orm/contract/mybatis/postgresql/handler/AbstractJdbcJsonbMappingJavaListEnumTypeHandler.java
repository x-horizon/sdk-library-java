// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.mybatis.postgresql.handler;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.enums.Enums;
import cn.srd.library.java.tool.lang.text.Strings;

import java.util.List;

/**
 * the postgresql jdbc jsonb data type and java list enum mapping relation abstract type handler
 *
 * @param <E> the enum data type
 * @author wjm
 * @since 2023-11-09 18:45
 */
public abstract class AbstractJdbcJsonbMappingJavaListEnumTypeHandler<E extends Enum<E>> extends AbstractJdbcJsonbMappingJavaListObjectTypeHandler<E> {

    /**
     * <pre>
     * select the enum field type to persist.
     *
     * for example, there is an enum as following:
     *
     * {@code
     *    @Getter
     *    @AllArgsConstructor
     *    public enum GenderType {
     *
     *        MAN(1, "man", "Man"),
     *        WOMAN(2, "woman", "Woman"),
     *        UNKNOWN(3, "unknown", "Unknown"),
     *
     *        ;
     *
     *        private final int code;
     *        private final String description1;
     *        private final String description2;
     *
     *    }
     * }
     * <li>if this function return Integer.class, the data in jdbc jsonb is [1, 2, 3].</li>
     * <li>if this function return String.class, the data in jdbc jsonb is ["man", "woman", "unknown"].</li>
     * </pre>
     *
     * @return the enum field type to persist
     */
    protected abstract Class<?> selectEnumFieldType();

    @Override
    protected Object doConvertToJdbcObject(List<E> javaObjects) {
        return javaObjects.stream().map(javaObject -> Enums.getFieldValue(javaObject, selectEnumFieldType())).toList();
    }

    @SuppressWarnings({SuppressWarningConstant.UNCHECKED, SuppressWarningConstant.RAW_TYPE})
    @Override
    protected List<E> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.toEnumsByString(Strings.removeHeadTailBracket(columnValue), javaType);
    }

}
