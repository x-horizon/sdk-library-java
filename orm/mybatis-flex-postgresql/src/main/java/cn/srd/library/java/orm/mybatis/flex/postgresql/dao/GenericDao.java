// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.dao;

import cn.srd.library.java.contract.component.database.postgresql.PostgresqlJsonbSQL;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.adapter.MybatisFlexAdapter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.orm.mybatis.flex.postgresql.chain.QueryChainer;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.RawQueryCondition;

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

    default List<P> listJsonbEmptyListByField(ColumnValueGetter<P> columnValueGetter) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getEmptyListEqual(MybatisFlexs.getQueryColumn(columnValueGetter).getName())));
    }

    default <T extends Number> List<P> listJsonbListNumberEqualByField(ColumnValueGetter<P> columnValueGetter, T value) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListNumberEqual(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), value)));
    }

    default <T extends Number> List<P> listJsonbListNumberInByField(ColumnValueGetter<P> columnValueGetter, List<T> values) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListNumberIn(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), values)));
    }

    default List<P> listJsonbListStringEqualByField(ColumnValueGetter<P> columnValueGetter, String value) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListStringEqual(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), value)));
    }

    default List<P> listJsonbListStringInByField(ColumnValueGetter<P> columnValueGetter, List<String> values) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListStringIn(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), values)));
    }

    default List<P> listJsonbListStringLikeByField(ColumnValueGetter<P> columnValueGetter, String value) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListStringLike(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), value)));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyIdEqualByField(ColumnValueGetter<P> columnValueGetter, T value) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyIdEqual(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), value)));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyIdInByField(ColumnValueGetter<P> columnValueGetter, List<T> values) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyIdIn(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), values)));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyTypeEqualByField(ColumnValueGetter<P> columnValueGetter, T value) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyTypeEqual(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), value)));
    }

    default <T extends Number> List<P> listJsonbListObjectKeyTypeInByField(ColumnValueGetter<P> columnValueGetter, List<T> values) {
        return getBaseMapper().selectListByCondition(new RawQueryCondition(PostgresqlJsonbSQL.getListObjectKeyTypeIn(MybatisFlexs.getQueryColumn(columnValueGetter).getName(), values)));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    private BaseMapper<P> getBaseMapper() {
        return (BaseMapper<P>) MybatisFlexAdapter.getInstance().getBaseMapper(this.getClass());
    }

}