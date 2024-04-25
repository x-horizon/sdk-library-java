// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.contract.constant.page.PageConstant;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.contract.model.page.PageParam;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.converter.PageConverter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import com.mybatisflex.core.paginate.Page;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/**
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2023-11-28 22:57
 */
@Getter(AccessLevel.PROTECTED)
@AllArgsConstructor
public class QueryChainer<P extends PO> extends BaseQueryChainer<P> {

    private final QueryChain<P> nativeQueryChain;

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> QueryChainer<P> select(ColumnNameGetter<U>... columnNameGetters) {
        getNativeQueryChain().select(columnNameGetters);
        return this;
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> innerJoin(Class<U> entityClass) {
        return innerJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> innerJoin(Class<U> entityClass, BooleanSupplier condition) {
        return innerJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> innerJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().innerJoin(entityClass, condition), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> leftJoin(Class<U> entityClass) {
        return leftJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> leftJoin(Class<U> entityClass, BooleanSupplier condition) {
        return leftJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> leftJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().leftJoin(entityClass, condition), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> rightJoin(Class<U> entityClass) {
        return rightJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> rightJoin(Class<U> entityClass, BooleanSupplier condition) {
        return rightJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> rightJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().rightJoin(entityClass, condition), this);
    }

    // TODO wjm 关于 cross join，不是在 cross join table name 后拼接 on 的连接条件的，而是在 where 后拼接表的连接条件，mybatis-flex 目前的实现有 bug，此处先屏蔽 cross join 的相关函数
    // @SuppressWarnings(SuppressWarningConstant.ALL)
    // public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> crossJoin(Class<U> entityClass) {
    //     return crossJoin(entityClass, true);
    // }
    // @SuppressWarnings(SuppressWarningConstant.ALL)
    // public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> crossJoin(Class<U> entityClass, BooleanSupplier condition) {
    //     return crossJoin(entityClass, condition.getAsBoolean());
    // }
    // @SuppressWarnings(SuppressWarningConstant.ALL)
    // public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> crossJoin(Class<U> entityClass, boolean condition) {
    //     return new QueryJoiner<>(getNativeQueryChainer().crossJoin(entityClass, condition), this);
    // }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> fullJoin(Class<U> entityClass) {
        return fullJoin(entityClass, true);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> fullJoin(Class<U> entityClass, BooleanSupplier condition) {
        return fullJoin(entityClass, condition.getAsBoolean());
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends PO> QueryJoiner<P, ? extends QueryChainer<P>> fullJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChain().fullJoin(entityClass, condition), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends POJO> QueryConditional<? extends QueryChainer<P>, QueryChain<P>> where(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().where(columnNameGetter), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends POJO> QueryConditional<? extends QueryChainer<P>, QueryChain<P>> and(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().and(columnNameGetter), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends POJO> QueryConditional<? extends QueryChainer<P>, QueryChain<P>> or(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChain().or(columnNameGetter), this);
    }

    public QueryChainer<P> as(String aliasName) {
        getNativeQueryChain().as(aliasName);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<P> groupBy(ColumnNameGetter<U>... columnNameGetters) {
        getNativeQueryChain().groupBy(columnNameGetters);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<P> orderByAsc(ColumnNameGetter<U>... columnNameGetters) {
        for (ColumnNameGetter<U> columnNameGetter : columnNameGetters) {
            getNativeQueryChain().orderBy(columnNameGetter, true);
        }
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<P> orderByDesc(ColumnNameGetter<U>... columnNameGetters) {
        for (ColumnNameGetter<U> columnNameGetter : columnNameGetters) {
            getNativeQueryChain().orderBy(columnNameGetter, false);
        }
        return this;
    }

    public Optional<P> get() {
        return getNativeQueryChain().oneOpt();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <V extends VO> Optional<V> getToVO() {
        Optional<P> po = get();
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    public List<P> list() {
        return getNativeQueryChain().list();
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <V extends VO> List<V> listToVOs() {
        return list().stream().map(po -> (V) po.toVO()).collect(Collectors.toList());
    }

    public PageResult<P> page() {
        return page(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE);
    }

    public PageResult<P> page(PageParam pageParam) {
        return page(pageParam.getPageNumber(), pageParam.getPageSize(), pageParam.getTotalNumber());
    }

    public PageResult<P> page(Number pageIndex, Number pageSize) {
        return page(new Page<>(pageIndex, pageSize));
    }

    public PageResult<P> page(Number pageIndex, Number pageSize, Number totalNumber) {
        return page(new Page<>(pageIndex, pageSize, totalNumber));
    }

    private PageResult<P> page(Page<P> page) {
        return PageConverter.INSTANCE.toPageResult(getNativeQueryChain().page(page));
    }

    public <V extends VO> PageResult<V> pageToVO() {
        return pageToVO(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE);
    }

    public <V extends VO> PageResult<V> pageToVO(PageParam pageParam) {
        return pageToVO(pageParam.getPageNumber(), pageParam.getPageSize(), pageParam.getTotalNumber());
    }

    public <V extends VO> PageResult<V> pageToVO(Number pageIndex, Number pageSize) {
        return pageToVO(new Page<>(pageIndex, pageSize));
    }

    public <V extends VO> PageResult<V> pageToVO(Number pageIndex, Number pageSize, Number totalNumber) {
        return pageToVO(new Page<>(pageIndex, pageSize, totalNumber));
    }

    private <V extends VO> PageResult<V> pageToVO(Page<P> page) {
        return PageConverter.INSTANCE.toPageResultVO(getNativeQueryChain().page(page));
    }

    public long count() {
        return getNativeQueryChain().count();
    }

    public boolean exists() {
        return getNativeQueryChain().exists();
    }

    public boolean notExists() {
        return !exists();
    }

    public String toSQL() {
        return getNativeQueryChain().toSQL();
    }

}