// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.mybatis.postgresql.handler;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.api.Converts;

import java.util.Map;

/**
 * @author wjm
 * @since 2024-07-24 14:54
 */
public class JdbcJsonbMappingJavaMapTypeHandler extends AbstractJdbcJsonbMappingJavaObjectTypeHandler<Map<String, Object>> {

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @Override
    protected Map<String, Object> doConvertToJavaObject(String columnValue, Class javaType) {
        return Converts.onJackson().toMap(columnValue, Object.class);
    }

}