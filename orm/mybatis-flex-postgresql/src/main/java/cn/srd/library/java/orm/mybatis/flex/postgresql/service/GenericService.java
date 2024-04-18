// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.service;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnValueGetter;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.GenericDao;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * the generic service
 *
 * @param <P> the entity extends {@link PO}
 * @param <V> the entity extends {@link VO}
 * @param <D> the dao extends {@link GenericCurdDao}
 * @author wjm
 * @since 2024-04-18 23:44
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.UNCHECKED)
public class GenericService<P extends PO, V extends VO, D extends GenericDao<P>> extends cn.srd.library.java.orm.mybatis.flex.base.service.GenericService<P, V, D> {

    @Autowired private D dao;

    public List<V> listJsonbEmptyByField(ColumnValueGetter<P> columnValueGetter) {
        return dao.listJsonbEmptyListByField(columnValueGetter)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListNumberEqualByField(ColumnValueGetter<P> columnValueGetter, T value) {
        return dao.listJsonbListNumberEqualByField(columnValueGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListNumberInByField(ColumnValueGetter<P> columnValueGetter, List<T> values) {
        return dao.listJsonbListNumberInByField(columnValueGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listJsonbListStringEqualByField(ColumnValueGetter<P> columnValueGetter, String value) {
        return dao.listJsonbListStringEqualByField(columnValueGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listJsonbListStringInByField(ColumnValueGetter<P> columnValueGetter, List<String> values) {
        return dao.listJsonbListStringInByField(columnValueGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listJsonbListStringLikeByField(ColumnValueGetter<P> columnValueGetter, String value) {
        return dao.listJsonbListStringLikeByField(columnValueGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyIdEqualByField(ColumnValueGetter<P> columnValueGetter, T value) {
        return dao.listJsonbListObjectKeyIdEqualByField(columnValueGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyIdInByField(ColumnValueGetter<P> columnValueGetter, List<T> values) {
        return dao.listJsonbListObjectKeyIdInByField(columnValueGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyTypeEqualByField(ColumnValueGetter<P> columnValueGetter, T value) {
        return dao.listJsonbListObjectKeyTypeEqualByField(columnValueGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyTypeInByField(ColumnValueGetter<P> columnValueGetter, List<T> values) {
        return dao.listJsonbListObjectKeyTypeInByField(columnValueGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

}