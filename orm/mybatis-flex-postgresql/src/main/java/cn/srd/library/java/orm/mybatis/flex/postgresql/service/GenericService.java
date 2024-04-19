// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.service;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
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

    public List<V> listJsonbEmptyByField(ColumnNameGetter<P> columnNameGetter) {
        return dao.listJsonbEmptyListByField(columnNameGetter)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListNumberEqualByField(ColumnNameGetter<P> columnNameGetter, T value) {
        return dao.listJsonbListNumberEqualByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListNumberInByField(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return dao.listJsonbListNumberInByField(columnNameGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listJsonbListStringEqualByField(ColumnNameGetter<P> columnNameGetter, String value) {
        return dao.listJsonbListStringEqualByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listJsonbListStringInByField(ColumnNameGetter<P> columnNameGetter, List<String> values) {
        return dao.listJsonbListStringInByField(columnNameGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listJsonbListStringLikeByField(ColumnNameGetter<P> columnNameGetter, String value) {
        return dao.listJsonbListStringLikeByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyIdEqualByField(ColumnNameGetter<P> columnNameGetter, T value) {
        return dao.listJsonbListObjectKeyIdEqualByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyIdInByField(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return dao.listJsonbListObjectKeyIdInByField(columnNameGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyTypeEqualByField(ColumnNameGetter<P> columnNameGetter, T value) {
        return dao.listJsonbListObjectKeyTypeEqualByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public <T extends Number> List<V> listJsonbListObjectKeyTypeInByField(ColumnNameGetter<P> columnNameGetter, List<T> values) {
        return dao.listJsonbListObjectKeyTypeInByField(columnNameGetter, values)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

}