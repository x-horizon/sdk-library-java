// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.dao;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.adapter.BaseMapperAdapter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.postgresql.chain.QueryChainer;
import cn.srd.library.java.orm.mybatis.flex.postgresql.function.PostgresqlFunctionQueryCondition;
import com.mybatisflex.core.BaseMapper;

import java.util.List;

/**
 * the generic dao
 *
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2024-04-18 21:54
 */
public interface GenericDao<P extends PO> extends cn.srd.library.java.orm.mybatis.flex.base.dao.GenericDao<P> {

    @Override
    default QueryChainer<P> openQuery() {
        return QueryChainer.of(getBaseMapper());
    }

    default List<P> listJsonbEmptyListByField(ColumnNameGetter<P> columnNameGetter) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbEmptyList(columnNameGetter));
    }

    default <T extends Number> List<P> listJsonbListNumberEqualByField(ColumnNameGetter<P> columnNameGetter, T value) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListNumberEqual(columnNameGetter, value));
    }

    default <T extends Number> List<P> listJsonbListNumberInByField(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListNumberIn(columnNameGetter, values));
    }

    default List<P> listJsonbListStringEqualByField(ColumnNameGetter<P> columnNameGetter, String value) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListStringEqual(columnNameGetter, value));
    }

    default List<P> listJsonbListStringInByField(ColumnNameGetter<P> columnNameGetter, List<String> values) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListStringIn(columnNameGetter, values));
    }

    default List<P> listJsonbListStringLikeByField(ColumnNameGetter<P> columnNameGetter, String value) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListStringLike(columnNameGetter, value));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyIdEqualByField(ColumnNameGetter<P> columnNameGetter, T value) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyIdEqual(columnNameGetter, value));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyIdInByField(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyIdIn(columnNameGetter, values));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyTypeEqualByField(ColumnNameGetter<P> columnNameGetter, T value) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyTypeEqual(columnNameGetter, value));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyTypeInByField(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return getBaseMapper().selectListByCondition(PostgresqlFunctionQueryCondition.listJsonbListObjectKeyTypeIn(columnNameGetter, values));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private BaseMapper<P> getBaseMapper() {
        return (BaseMapper<P>) BaseMapperAdapter.getInstance().getBaseMapper(this.getClass());
    }

}