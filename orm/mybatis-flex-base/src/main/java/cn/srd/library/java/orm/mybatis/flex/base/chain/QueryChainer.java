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
import com.mybatisflex.core.BaseMapper;
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
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class QueryChainer<P extends PO> extends BaseQueryChainer<P> {

    @Getter(AccessLevel.PROTECTED) private final BaseMapper<P> nativeBaseMapper;

    @Getter(AccessLevel.PROTECTED) private final QueryChain<P> nativeQueryChainer;

    public static <P extends PO> QueryChainer<P> of(BaseMapper<P> baseMapper) {
        return new QueryChainer<>(baseMapper, QueryChain.of(baseMapper));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    public <U extends PO> QueryChainer<P> select(ColumnNameGetter<U>... columnNameGetters) {
        getNativeQueryChainer().select(columnNameGetters);
        return this;
    }

    public <U extends PO> QueryChainer<P> selectSelfAll() {
        getNativeQueryChainer().select();
        return this;
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> innerJoin(Class<U> entityClass) {
        return innerJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> innerJoin(Class<U> entityClass, BooleanSupplier condition) {
        return innerJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> innerJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().innerJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> leftJoin(Class<U> entityClass) {
        return leftJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> leftJoin(Class<U> entityClass, BooleanSupplier condition) {
        return leftJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> leftJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().leftJoin(entityClass, condition), this);
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> rightJoin(Class<U> entityClass) {
        return rightJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> rightJoin(Class<U> entityClass, BooleanSupplier condition) {
        return rightJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> rightJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().rightJoin(entityClass, condition), this);
    }

    // TODO wjm 关于 cross join，不是在 cross join table name 后拼接 on 的连接条件的，而是在 where 后拼接表的连接条件，mybatis-flex 目前的实现有 bug，此处先屏蔽 cross join 的相关函数
    // public <U extends PO> QueryJoiner<P, QueryChainer<P>> crossJoin(Class<U> entityClass) {
    //     return crossJoin(entityClass, true);
    // }
    // public <U extends PO> QueryJoiner<P, QueryChainer<P>> crossJoin(Class<U> entityClass, BooleanSupplier condition) {
    //     return crossJoin(entityClass, condition.getAsBoolean());
    // }
    // public <U extends PO> QueryJoiner<P, QueryChainer<P>> crossJoin(Class<U> entityClass, boolean condition) {
    //     return new QueryJoiner<>(getNativeQueryChainer().crossJoin(entityClass, condition), this);
    // }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> fullJoin(Class<U> entityClass) {
        return fullJoin(entityClass, true);
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> fullJoin(Class<U> entityClass, BooleanSupplier condition) {
        return fullJoin(entityClass, condition.getAsBoolean());
    }

    public <U extends PO> QueryJoiner<P, QueryChainer<P>> fullJoin(Class<U> entityClass, boolean condition) {
        return new QueryJoiner<>(getNativeQueryChainer().fullJoin(entityClass, condition), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends POJO> QueryConditional<P, ? extends QueryChainer<P>, QueryChain<P>> where(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChainer().where(columnNameGetter), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends POJO> QueryConditional<P, ? extends QueryChainer<P>, QueryChain<P>> and(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChainer().and(columnNameGetter), this);
    }

    @SuppressWarnings(SuppressWarningConstant.ALL)
    public <U extends POJO> QueryConditional<P, ? extends QueryChainer<P>, QueryChain<P>> or(ColumnNameGetter<U> columnNameGetter) {
        return new QueryConditional<>(getNativeQueryChainer().or(columnNameGetter), this);
    }

    public QueryChainer<P> as(String aliasName) {
        getNativeQueryChainer().as(aliasName);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<P> groupBy(ColumnNameGetter<U>... columnNameGetters) {
        getNativeQueryChainer().groupBy(columnNameGetters);
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<P> orderByAsc(ColumnNameGetter<U>... columnNameGetters) {
        for (ColumnNameGetter<U> columnNameGetter : columnNameGetters) {
            getNativeQueryChainer().orderBy(columnNameGetter, true);
        }
        return this;
    }

    @SafeVarargs
    public final <U extends PO> QueryChainer<P> orderByDesc(ColumnNameGetter<U>... columnNameGetters) {
        for (ColumnNameGetter<U> columnNameGetter : columnNameGetters) {
            getNativeQueryChainer().orderBy(columnNameGetter, false);
        }
        return this;
    }

    public Optional<P> get() {
        return getNativeQueryChainer().oneOpt();
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
        return getNativeQueryChainer().list();
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
        return PageConverter.INSTANCE.toPageResult(getNativeQueryChainer().page(page));
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
        return PageConverter.INSTANCE.toPageResultVO(getNativeQueryChainer().page(page));
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