// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.contract.constant.page.PageConstant;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.page.PageParam;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.converter.PageConverter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/**
 * @param <T> the entity extends {@link PO}
 * @author wjm
 * @since 2023-11-28 22:57
 */
@AllArgsConstructor(access = AccessLevel.MODULE)
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class QueryChainer<T extends PO> extends BaseQueryChainer<T> {

    @Getter(AccessLevel.PROTECTED) private final BaseMapper<T> nativeBaseMapper;

    @Getter(AccessLevel.PROTECTED) private final QueryChain<T> nativeQueryChainer;

    public static <T extends PO> QueryChainer<T> of(BaseMapper<T> baseMapper) {
        return new QueryChainer<>(baseMapper, QueryChain.of(baseMapper));
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<T> select(ColumnValueGetter<U>... columnValueGetters) {
        getNativeQueryChainer().select(columnValueGetters);
        return this;
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> innerJoin(Class<U> entityClass) {
        return innerJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> innerJoin(Class<U> entityClass, BooleanSupplier condition) {
        return innerJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> innerJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().innerJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> leftJoin(Class<U> entityClass) {
        return leftJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> leftJoin(Class<U> entityClass, BooleanSupplier condition) {
        return leftJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> leftJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().leftJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> rightJoin(Class<U> entityClass) {
        return rightJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> rightJoin(Class<U> entityClass, BooleanSupplier condition) {
        return rightJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> rightJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().rightJoin(entityClass, condition), this);
    }

    // TODO wjm 关于 cross join，不是在 cross join table name 后拼接 on 的连接条件的，而是在 where 后拼接表的连接条件，mybatis-flex 目前的实现有 bug，此处先屏蔽 cross join 的相关函数
    // public <U extends PO> QueryJoiner<T, QueryChainer<T>> crossJoin(Class<U> entityClass) {
    //     return crossJoin(entityClass, true);
    // }
    // public <U extends PO> QueryJoiner<T, QueryChainer<T>> crossJoin(Class<U> entityClass, BooleanSupplier condition) {
    //     return crossJoin(entityClass, condition.getAsBoolean());
    // }
    // public <U extends PO> QueryJoiner<T, QueryChainer<T>> crossJoin(Class<U> entityClass, boolean condition) {
    //     return new QueryJoiner<>(getNativeQueryChainer().crossJoin(entityClass, condition), this);
    // }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> fullJoin(Class<U> entityClass) {
        return fullJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> fullJoin(Class<U> entityClass, BooleanSupplier condition) {
        return fullJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> fullJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().fullJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryConditional<T, QueryChainer<T>, QueryChain<T>> where(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChainer().where(columnValueGetter), this);
    }

    public <U extends PO> QueryConditional<T, QueryChainer<T>, QueryChain<T>> and(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChainer().and(columnValueGetter), this);
    }

    public <U extends PO> QueryConditional<T, QueryChainer<T>, QueryChain<T>> or(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChainer().or(columnValueGetter), this);
    }

    public QueryChainer<T> as(String aliasName) {
        getNativeQueryChainer().as(aliasName);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<T> groupBy(ColumnValueGetter<U>... columnValueGetters) {
        getNativeQueryChainer().groupBy(columnValueGetters);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<T> orderByAsc(ColumnValueGetter<U>... columnValueGetters) {
        for (ColumnValueGetter<U> columnValueGetter : columnValueGetters) {
            getNativeQueryChainer().orderBy(columnValueGetter, true);
        }
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<T> orderByDesc(ColumnValueGetter<U>... columnValueGetters) {
        for (ColumnValueGetter<U> columnValueGetter : columnValueGetters) {
            getNativeQueryChainer().orderBy(columnValueGetter, false);
        }
        return this;
    }

    public Optional<T> get() {
        return getNativeQueryChainer().oneOpt();
    }

    public List<T> list() {
        return getNativeQueryChainer().list();
    }

    public PageResult<T> page() {
        return page(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE);
    }

    public PageResult<T> page(PageParam pageParam) {
        return page(pageParam.getPageNumber(), pageParam.getPageSize(), pageParam.getTotalNumber());
    }

    public PageResult<T> page(Number pageIndex, Number pageSize) {
        return page(new Page<>(pageIndex, pageSize));
    }

    public PageResult<T> page(Number pageIndex, Number pageSize, Number totalNumber) {
        return page(new Page<>(pageIndex, pageSize, totalNumber));
    }

    private PageResult<T> page(Page<T> page) {
        return PageConverter.INSTANCE.toPageResult(getNativeQueryChainer().page(page));
    }

    public long count() {
        return getNativeQueryChainer().count();
    }

    public boolean exists() {
        return getNativeQueryChainer().exists();
    }

    public boolean notExists() {
        return !exists();
    }

    public String toSQL() {
        return getNativeQueryChainer().toSQL();
    }

}