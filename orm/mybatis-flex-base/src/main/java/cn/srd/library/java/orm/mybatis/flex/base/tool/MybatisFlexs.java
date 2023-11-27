// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.tool;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2023-11-27 22:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MybatisFlexs {

    public static <T extends PO> TableInfo getTableInfo(Class<T> entityClass) {
        return TableInfoFactory.ofEntityClass(entityClass);
    }

    public static <T extends PO> Object getPrimaryKeyValue(T entity) {
        return Collections.getFirst(getPrimaryKeyValues(entity)).orElseThrow();
    }

    public static <T extends PO> Object[] getPrimaryKeyValues(T entity) {
        TableInfo tableInfo = getTableInfo(entity.getClass());
        return tableInfo.buildPkSqlArgs(entity);
    }

}
