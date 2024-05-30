// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.service;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.orm.mybatis.flex.base.repository.GenericRepository;
import cn.srd.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * the generic curd service
 *
 * @param <P> the entity extends {@link PO}
 * @param <V> the entity extends {@link VO}
 * @param <R> the repository extends {@link GenericRepository}
 * @author wjm
 * @since 2024-04-16 14:31
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.UNCHECKED)
public class GenericService<P extends PO, V extends VO, R extends GenericRepository<P>> {

    @Autowired private R repository;

    public V save(V entity) {
        return (V) repository.save((P) entity.toPO()).toVO();
    }

    public List<V> saveBatch(Iterable<V> entities) {
        return repository.saveBatch(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()))
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> saveBatch(Iterable<V> entities, int batchSizeEachTime) {
        return repository.saveBatch(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), batchSizeEachTime)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public V updateById(V entity) {
        return (V) repository.updateById((P) entity.toPO()).toVO();
    }

    public List<V> updateBatchById(V... entities) {
        return repository.updateBatchById(Arrays.stream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()))
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> updateBatchById(Iterable<V> entities) {
        return repository.updateBatchById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()))
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> updateBatchById(Iterable<V> entities, int batchSizeEachTime) {
        return repository.updateBatchById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), batchSizeEachTime)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public V updateWithVersionById(V entity) {
        return (V) repository.updateWithVersionById((P) entity.toPO()).toVO();
    }

    public List<V> updateBatchWithVersionById(Iterable<V> entities, Function<P, ? extends Serializable> getIdAction) {
        return repository.updateBatchWithVersionById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), getIdAction)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> updateBatchWithVersionById(Iterable<V> entities, Function<P, ? extends Serializable> getIdAction, int batchSizeEachTime) {
        return repository.updateBatchWithVersionById(Collections.ofUnknownSizeStream(entities).map(vo -> (P) vo.toPO()).collect(Collectors.toList()), getIdAction, batchSizeEachTime)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public void deleteById(V entity) {
        repository.deleteById((P) entity.toPO());
    }

    public void deleteById(Serializable id) {
        repository.deleteById(id);
    }

    public void deleteByIds(Serializable... ids) {
        repository.deleteByIds(ids);
    }

    public void deleteByIds(Iterable<? extends Serializable> ids) {
        repository.deleteByIds(ids);
    }

    public void deleteSkipLogicById(V entity) {
        repository.deleteSkipLogicById((P) entity.toPO());
    }

    public void deleteSkipLogicById(Serializable id) {
        repository.deleteSkipLogicById(id);
    }

    public void deleteSkipLogicByIds(Serializable... ids) {
        repository.deleteSkipLogicByIds(ids);
    }

    public void deleteSkipLogicByIds(Iterable<? extends Serializable> ids) {
        repository.deleteSkipLogicByIds(ids);
    }

    public Optional<V> getById(Serializable id) {
        Optional<P> po = repository.getById(id);
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    public Optional<V> getById(V entity) {
        Optional<P> po = repository.getById((P) entity.toPO());
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    public Optional<V> getByField(ColumnNameGetter<P> columnNameGetter, String name) {
        Optional<P> po = repository.getByField(columnNameGetter, name);
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    public List<V> listByIds(Iterable<? extends Serializable> ids) {
        return repository.listByIds(ids)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listByField(ColumnNameGetter<P> columnNameGetter, Object value) {
        return repository.listByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listLikeByField(ColumnNameGetter<P> columnNameGetter, String value) {
        return repository.listLikeByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listAll() {
        return repository.listAll()
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public long countAll() {
        return repository.countAll();
    }

}