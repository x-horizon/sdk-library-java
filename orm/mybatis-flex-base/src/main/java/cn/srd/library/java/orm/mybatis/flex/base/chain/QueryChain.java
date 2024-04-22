// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.orm.mybatis.flex.base.adapter.QueryWrapperAdapter;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.mybatis.Mappers;
import com.mybatisflex.core.query.CPI;
import com.mybatisflex.core.query.MapperQueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-04-20 15:08
 */
public class QueryChain<T> extends QueryWrapperAdapter<QueryChain<T>> implements MapperQueryChain<T> {

    @Serial private static final long serialVersionUID = 6771089212486923320L;

    private final BaseMapper<T> baseMapper;

    public QueryChain(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    public static <T> QueryChain<T> of(Class<T> entityClass) {
        BaseMapper<T> baseMapper = Mappers.ofEntityClass(entityClass);
        return new QueryChain<>(baseMapper);
    }

    public static <E> QueryChain<E> of(BaseMapper<E> baseMapper) {
        return new QueryChain<>(baseMapper);
    }

    @Override
    public BaseMapper<T> baseMapper() {
        return baseMapper;
    }

    @Override
    public QueryWrapper toQueryWrapper() {
        return this;
    }

    @Override
    public String toSQL() {
        TableInfo tableInfo = TableInfoFactory.ofMapperClass(baseMapper.getClass());
        CPI.setFromIfNecessary(this, tableInfo.getSchema(), tableInfo.getTableName());
        return super.toSQL();
    }

}