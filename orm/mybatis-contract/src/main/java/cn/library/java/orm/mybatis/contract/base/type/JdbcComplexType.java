// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.base.type;

import cn.library.java.orm.mybatis.contract.base.cache.ColumnMappingRelationCache;
import cn.srd.library.java.tool.enums.autowired.EnumAutowired;
import lombok.Getter;

/**
 * @author wjm
 * @since 2023-11-08 17:07
 */
@Getter
@EnumAutowired(rootClass = ColumnMappingRelationCache.class)
public enum JdbcComplexType {

    JSON,

    ;

    private ColumnMappingRelationCache columnMappingRelationCache;

}
