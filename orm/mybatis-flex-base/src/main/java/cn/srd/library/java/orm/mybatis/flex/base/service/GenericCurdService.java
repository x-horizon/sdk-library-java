// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.service;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.base.dao.GenericCurdDao;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * the generic curd service
 *
 * @author wjm
 * @since 2024-04-16 14:31
 */
@Service
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.UNCHECKED)
public class GenericCurdService<P extends PO, V extends VO, D extends GenericCurdDao<P>> {

    @Autowired private D dao;

    public V save(V entity) {
        return (V) dao.save((P) entity.toPO()).toVO();
    }

    public List<V> saveBatch(Iterable<V> entities) {
        return dao.saveBatch(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()))
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> saveBatch(Iterable<V> entities, int batchSizeEachTime) {
        return dao.saveBatch(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), batchSizeEachTime)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public V updateById(V entity) {
        return (V) dao.updateById((P) entity.toPO()).toVO();
    }

    public List<V> updateBatchById(V... entities) {
        return dao.updateBatchById(Arrays.stream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()))
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> updateBatchById(Iterable<V> entities) {
        return dao.updateBatchById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()))
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> updateBatchById(Iterable<V> entities, int batchSizeEachTime) {
        return dao.updateBatchById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), batchSizeEachTime)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public V updateWithVersionById(V entity) {
        return (V) dao.updateWithVersionById((P) entity.toPO()).toVO();
    }

    public List<V> updateBatchWithVersionById(Iterable<V> entities, Function<P, ? extends Serializable> getIdAction) {
        return dao.updateBatchWithVersionById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), getIdAction)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> updateBatchWithVersionById(Iterable<V> entities, Function<P, ? extends Serializable> getIdAction, int batchSizeEachTime) {
        return dao.updateBatchWithVersionById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), getIdAction, batchSizeEachTime)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public void deleteById(V entity) {
        dao.deleteById((P) entity.toPO());
    }

    public void deleteById(Serializable id) {
        dao.deleteById(id);
    }

    public void deleteByIds(Serializable... ids) {
        dao.deleteByIds(ids);
    }

    public void deleteByIds(Iterable<? extends Serializable> ids) {
        dao.deleteByIds(ids);
    }

    public void deleteSkipLogicById(V entity) {
        dao.deleteSkipLogicById((P) entity.toPO());
    }

    public void deleteSkipLogicById(Serializable id) {
        dao.deleteSkipLogicById(id);
    }

    public void deleteSkipLogicByIds(Serializable... ids) {
        dao.deleteSkipLogicByIds(ids);
    }

    public void deleteSkipLogicByIds(Iterable<? extends Serializable> ids) {
        dao.deleteSkipLogicByIds(ids);
    }

    public Optional<V> getById(Serializable id) {
        Optional<P> po = dao.getById(id);
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    public Optional<V> getById(V entity) {
        Optional<P> po = dao.getById((P) entity.toPO());
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    public List<V> listByIds(Iterable<? extends Serializable> ids) {
        return dao.listByIds(ids)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listAll() {
        return dao.listAll()
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public long countAll() {
        return dao.countAll();
    }

}