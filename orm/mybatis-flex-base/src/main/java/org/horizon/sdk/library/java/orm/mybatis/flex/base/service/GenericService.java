package org.horizon.sdk.library.java.orm.mybatis.flex.base.service;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.base.VO;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.repository.GenericRepository;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
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

    public V updateByIdIgnoreLogicDelete(V entity) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateById(entity));
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

    public List<V> updateBatchByIdIgnoreLogicDelete(V... entities) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchById(entities));
    }

    public List<V> updateBatchByIdIgnoreLogicDelete(Iterable<V> entities) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchById(entities));
    }

    public List<V> updateBatchByIdIgnoreLogicDelete(Iterable<V> entities, int batchSizeEachTime) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchById(entities, batchSizeEachTime));
    }

    public V updateWithVersionById(V entity) {
        return (V) repository.updateWithVersionById((P) entity.toPO()).toVO();
    }

    public V updateWithVersionByIdIgnoreLogicDelete(V entity) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateWithVersionById(entity));
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

    public List<V> updateBatchWithVersionByIdIgnoreLogicDelete(Iterable<V> entities, Function<P, ? extends Serializable> getIdAction) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchWithVersionById(entities, getIdAction));
    }

    public List<V> updateBatchWithVersionByIdIgnoreLogicDelete(Iterable<V> entities, Function<P, ? extends Serializable> getIdAction, int batchSizeEachTime) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchWithVersionById(entities, getIdAction, batchSizeEachTime));
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

    public void deleteByField(ColumnNameGetter<P> columnNameGetter, Object value) {
        repository.deleteByField(columnNameGetter, value);
    }

    public void deleteByIdIgnoreLogicDelete(V entity) {
        repository.deleteByIdIgnoreLogicDelete(entity);
    }

    public void deleteByIdIgnoreLogicDelete(Serializable id) {
        repository.deleteByIdIgnoreLogicDelete(id);
    }

    public void deleteByIdsIgnoreLogicDelete(Serializable... ids) {
        repository.deleteByIdsIgnoreLogicDelete(ids);
    }

    public void deleteByIdsIgnoreLogicDelete(Iterable<? extends Serializable> ids) {
        repository.deleteByIdsIgnoreLogicDelete(ids);
    }

    public void deleteByFieldIgnoreLogicDelete(ColumnNameGetter<P> columnNameGetter, Object value) {
        repository.deleteByFieldIgnoreLogicDelete(columnNameGetter, value);
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

    public Optional<V> getByIdIgnoreLogicDelete(Serializable id) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> getById(id));
    }

    public Optional<V> getByIdIgnoreLogicDelete(V entity) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> getById(entity));
    }

    public Optional<V> getByField(ColumnNameGetter<P> columnNameGetter, String name) {
        Optional<P> po = repository.getByField(columnNameGetter, name);
        if (po.isPresent()) {
            return Optional.ofNullable((V) po.orElseThrow().toVO());
        }
        return Optional.empty();
    }

    public Optional<V> getByFieldIgnoreLogicDelete(ColumnNameGetter<P> columnNameGetter, String name) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> getByField(columnNameGetter, name));
    }

    public List<V> listByIds(Iterable<? extends Serializable> ids) {
        return repository.listByIds(ids)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listByIdsIgnoreLogicDelete(Iterable<? extends Serializable> ids) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> listByIds(ids));
    }

    public List<V> listByField(ColumnNameGetter<P> columnNameGetter, Object value) {
        return repository.listByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listByFieldIgnoreLogicDelete(ColumnNameGetter<P> columnNameGetter, Object value) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> listByField(columnNameGetter, value));
    }

    public List<V> listLikeByField(ColumnNameGetter<P> columnNameGetter, String value) {
        return repository.listLikeByField(columnNameGetter, value)
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listLikeByFieldIgnoreLogicDelete(ColumnNameGetter<P> columnNameGetter, String value) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> listLikeByField(columnNameGetter, value));
    }

    public List<V> listAll() {
        return repository.listAll()
                .stream()
                .map(po -> (V) po.toVO())
                .collect(Collectors.toList());
    }

    public List<V> listAllIgnoreLogicDelete() {
        return LogicDeleteManager.execWithoutLogicDelete(this::listAll);
    }

    public long countAll() {
        return repository.countAll();
    }

    public long countAllIgnoreLogicDelete() {
        return LogicDeleteManager.execWithoutLogicDelete(this::countAll);
    }

    public <U> boolean isUniqueOnSave(ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue) {
        return repository.isUniqueOnSave(requireUniqueColumnNameGetter, requireUniqueColumnValue);
    }

    public <U, C> boolean isUniqueOnSave(ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue, ColumnNameGetter<P> conditionScopeColumnNameGetter, C conditionScopeColumnValue) {
        return repository.isUniqueOnSave(requireUniqueColumnNameGetter, requireUniqueColumnValue, conditionScopeColumnNameGetter, conditionScopeColumnValue);
    }

    public <U> boolean isUniqueOnUpdate(Serializable id, ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue) {
        return repository.isUniqueOnUpdate(id, requireUniqueColumnNameGetter, requireUniqueColumnValue);
    }

    public <U, C> boolean isUniqueOnUpdate(Serializable id, ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue, ColumnNameGetter<P> conditionScopeColumnNameGetter, C conditionScopeColumnValue) {
        return repository.isUniqueOnUpdate(id, requireUniqueColumnNameGetter, requireUniqueColumnValue, conditionScopeColumnNameGetter, conditionScopeColumnValue);
    }

}