// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import cn.srd.library.java.contract.constant.page.PageConstant;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.page.PageParam;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.converter.PageConverter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import cn.srd.library.java.tool.lang.collection.Collections;
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
public class QueryChainer<T extends PO> extends AbstractQueryChainer<T> {

    @Getter(AccessLevel.PROTECTED) private final QueryChain<T> nativeQueryChain;

    public static <T extends PO> QueryChainer<T> of(BaseMapper<T> baseMapper) {
        return new QueryChainer<>(QueryChain.of(baseMapper));
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> innerJoin(Class<U> entityClass) {
        return innerJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> innerJoin(Class<U> entityClass, BooleanSupplier condition) {
        return innerJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> innerJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().innerJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> leftJoin(Class<U> entityClass) {
        return leftJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> leftJoin(Class<U> entityClass, BooleanSupplier condition) {
        return leftJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> leftJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().leftJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> rightJoin(Class<U> entityClass) {
        return rightJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> rightJoin(Class<U> entityClass, BooleanSupplier condition) {
        return rightJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> rightJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().rightJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> crossJoin(Class<U> entityClass) {
        return crossJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> crossJoin(Class<U> entityClass, BooleanSupplier condition) {
        return crossJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> crossJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().crossJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> fullJoin(Class<U> entityClass) {
        return fullJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> fullJoin(Class<U> entityClass, BooleanSupplier condition) {
        return fullJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<T, QueryChainer<T>> fullJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().fullJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryConditional<T, QueryChainer<T>, QueryChain<T>> where(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChain().where(columnValueGetter), this);
    }

    public <U extends PO> QueryConditional<T, QueryChainer<T>, QueryChain<T>> and(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChain().and(columnValueGetter), this);
    }

    public <U extends PO> QueryConditional<T, QueryChainer<T>, QueryChain<T>> or(ColumnValueGetter<U> columnValueGetter) {
        return new QueryConditional<>(getNativeQueryChain().or(columnValueGetter), this);
    }

    public QueryChainer<T> as(String aliasName) {
        getNativeQueryChain().as(aliasName);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<T> groupBy(ColumnValueGetter<U>... columnValueGetters) {
        getNativeQueryChain().groupBy(columnValueGetters);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<T> orderByAsc(ColumnValueGetter<U>... columnValueGetters) {
        for (ColumnValueGetter<U> columnValueGetter : columnValueGetters) {
            getNativeQueryChain().orderBy(columnValueGetter, true);
        }
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<T> orderByDesc(ColumnValueGetter<U>... columnValueGetters) {
        for (ColumnValueGetter<U> columnValueGetter : columnValueGetters) {
            getNativeQueryChain().orderBy(columnValueGetter, false);
        }
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> Optional<U> get() {
        return (Optional<U>) getNativeQueryChain().oneOpt();
    }

    public <U extends PO> Optional<U> getFirst() {
        return Collections.getFirst(list());
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> Optional<U> getOnlyOne() {
        return Optional.ofNullable((U) getNativeQueryChain().obj());
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> List<U> list() {
        return (List<U>) getNativeQueryChain().list();
    }

    public PageResult<T> page() {
        return page(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE);
    }

    public PageResult<T> page(PageParam pageParam) {
        return page(pageParam.getPageIndex(), pageParam.getPageSize(), pageParam.getTotal());
    }

    public PageResult<T> page(Number pageIndex, Number pageSize) {
        return page(new Page<>(pageIndex, pageSize));
    }

    public PageResult<T> page(Number pageIndex, Number pageSize, Number totalNumber) {
        return page(new Page<>(pageIndex, pageSize, totalNumber));
    }

    private PageResult<T> page(Page<T> page) {
        return PageConverter.INSTANCE.toPageResult(getNativeQueryChain().page(page));
    }

    public long count() {
        return getNativeQueryChain().count();
    }

    public boolean exists() {
        return getNativeQueryChain().exists();
    }

    public String toSQL() {
        return getNativeQueryChain().toSQL();
    }

}
