package org.horizon.sdk.library.java.orm.mybatis.flex.base.repository;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.keygen.CustomKeyGenerator;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.core.util.ClassUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.horizon.sdk.library.java.contract.constant.module.ModuleView;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.base.PO;
import org.horizon.sdk.library.java.contract.model.throwable.LibraryJavaInternalException;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.cache.MybatisFlexSystemCache;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.DeleteChainer;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryChain;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.QueryChainer;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.chain.UpdateChainer;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import org.horizon.sdk.library.java.orm.mybatis.flex.base.support.MybatisFlexs;
import org.horizon.sdk.library.java.tool.lang.collection.Collections;
import org.horizon.sdk.library.java.tool.lang.compare.Comparators;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;
import org.horizon.sdk.library.java.tool.lang.functional.Action;
import org.horizon.sdk.library.java.tool.lang.object.Nil;
import org.horizon.sdk.library.java.tool.lang.reflect.Reflects;
import org.horizon.sdk.library.java.tool.lang.text.Strings;
import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * the generic curd repository
 *
 * @param <P> the entity extends {@link PO}
 * @author wjm
 * @since 2023-11-04 00:19
 */
@SuppressWarnings(SuppressWarningConstant.UNUSED)
@CanIgnoreReturnValue
public interface GenericRepository<P extends PO> {

    /**
     * see <a href="https://mybatis-flex.com/zh/base/batch.html">"the batch operation guide"</a>.
     */
    int GENERATE_FULL_SQL_BATCH_SIZE = 100;

    /**
     * default batch operation size each time
     */
    int DEFAULT_BATCH_SIZE_EACH_TIME = BaseMapper.DEFAULT_BATCH_SIZE;

    /**
     * <p>insert operation with null column exclusion.</p>
     *
     * <p>primary key handling rules:</p>
     * <ul>
     *     <li>generates primary key when value is null/empty (via key generation strategy)</li>
     *     <li>uses existing value when primary key contains non-empty value</li>
     *     <li>supports composite primary keys</li>
     * </ul>
     *
     * <p>usage examples:</p>
     * <ol>
     *     <li><b>Single primary key with null value:</b>
     *         <pre>{@code
     * TestTablePO(entity: id=null, name="testName")
     * // SQL: INSERT INTO "test_table"("id", "name") VALUES (487223443892741, 'testName')
     *         }</pre>
     *     </li>
     *
     *     <li><b>All null fields except primary key:</b>
     *         <pre>{@code
     * TestTablePO(entity: id=null, name=null)
     * // SQL: INSERT INTO "test_table"("id") VALUES (487223443892741)
     *         }</pre>
     *     </li>
     *
     *     <li><b>Explicit primary key value:</b>
     *         <pre>{@code
     * TestTablePO(entity: id=1, name=null)
     * // SQL: INSERT INTO "test_table"("id") VALUES (1)
     *         }</pre>
     *     </li>
     *
     *     <li><b>Composite keys generation:</b>
     *         <pre>{@code
     * TestTablePO(entity: id1=null, id2=null)
     * // SQL: INSERT INTO "test_table"("id1", "id2") VALUES (487223443892741, 487223443892742)
     *         }</pre>
     *     </li>
     *
     *     <li><b>Explicit composite keys:</b>
     *         <pre>{@code
     * TestTablePO(entity: id1=1L, id2=2L)
     * // SQL: INSERT INTO "test_table"("id1", "id2") VALUES (1, 2)
     *         }</pre>
     *     </li>
     * </ol>
     *
     * @param entity target entity for insertion
     * @return entity with populated primary key(s)
     * @see BaseMapper#insertSelective(Object)
     * @see CustomKeyGenerator#processBefore(Executor, MappedStatement, Statement, Object)
     */
    default P save(P entity) {
        getBaseMapper().insertSelective(entity);
        return entity;
    }

    /**
     * using {@link #GENERATE_FULL_SQL_BATCH_SIZE batch size each time} to insert batch.
     *
     * @param entities the operate entities
     * @return the entities with primary key
     * @see #GENERATE_FULL_SQL_BATCH_SIZE
     * @see #save(PO)
     * @see #saveBatch(Iterable, int)
     * @see BaseMapper#insertSelective(Object)
     * @see BaseMapper#insertBatch(Collection, int)
     * @see IService#saveBatch(Collection, int)
     * @see CustomKeyGenerator#processBefore(Executor, MappedStatement, Statement, Object)
     */
    @Transactional(rollbackFor = Throwable.class)
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> saveBatch(Iterable<P> entities) {
        return Springs.getProxy(GenericRepository.class).saveBatch(entities, DEFAULT_BATCH_SIZE_EACH_TIME);
    }

    /**
     * <p>performs batch insert operations with adaptive SQL generation.</p>
     *
     * <p>execution strategies:</p>
     * <ol>
     *     <li><b>Single-statement batch:</b> when entity count ≤ {@link #GENERATE_FULL_SQL_BATCH_SIZE}
     *         <pre>{@code
     * INSERT INTO test_table (id, name)
     * VALUES
     *   (487223443892741, 'test1'),
     *   (487223443892742, 'test2'),
     *   (487223443913230, 'test3')
     *         }</pre>
     *     </li>
     *
     *     <li><b>Multi-statement batch:</b> when entity count > {@link #GENERATE_FULL_SQL_BATCH_SIZE}
     *         <pre>{@code
     * INSERT INTO test_table (id, name) VALUES (487223443892741, 'test1');
     * INSERT INTO test_table (id, name) VALUES (487223443892742, 'test2');
     * INSERT INTO test_table (id, name) VALUES (487223443913230, 'test3');
     *         }</pre>
     *     </li>
     * </ol>
     *
     * @param entities          collection of entities to insert
     * @param batchSizeEachTime maximum number of statements per batch operation
     * @return entities with generated primary key(s) populated
     * @apiNote strategy selection based on <a href="https://mybatis-flex.com/zh/base/batch.html">batch operation performance guidelines</a>
     * @see #GENERATE_FULL_SQL_BATCH_SIZE
     * @see BaseMapper#insertSelective(Object)
     * @see BaseMapper#insertBatch(Collection, int)
     * @see IService#saveBatch(Collection, int)
     * @see CustomKeyGenerator#processBefore(Executor, MappedStatement, Statement, Object)
     */
    @Transactional(rollbackFor = Throwable.class)
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> saveBatch(Iterable<P> entities, int batchSizeEachTime) {
        if (Nil.isEmpty(entities)) {
            return Collections.newArrayList();
        }
        List<P> listTypeEntities = entities instanceof List<P> actualEntities ? actualEntities : Converts.toArrayList(entities);
        Action.ifTrue(listTypeEntities.size() <= GENERATE_FULL_SQL_BATCH_SIZE)
                .then(() -> getBaseMapper().insertBatch(listTypeEntities, batchSizeEachTime))
                .otherwise(() -> Db.executeBatch(listTypeEntities, batchSizeEachTime, ClassUtil.getUsefulClass(this.getClass()), GenericRepository::save));
        return listTypeEntities;
    }

    /**
     * update by id.
     *
     * @param entity – the operate entity
     * @see #updateBatchById(Iterable, int)
     */
    default P updateById(P entity) {
        getBaseMapper().update(entity);
        return entity;
    }

    default P updateByIdIgnoreLogicDelete(P entity) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateById(entity));
    }

    /**
     * update batch by id.
     *
     * @param entities the operate entities
     * @see #updateBatchById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> updateBatchById(P... entities) {
        List<P> needToUpdateEntities = Collections.ofArrayList(entities);
        Springs.getProxy(GenericRepository.class).updateBatchById(needToUpdateEntities);
        return needToUpdateEntities;
    }

    /**
     * using {@link #GENERATE_FULL_SQL_BATCH_SIZE batch size each time} to update by id.
     *
     * @param entities the operate entities
     * @see #updateBatchById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> updateBatchById(Iterable<P> entities) {
        return Springs.getProxy(GenericRepository.class).updateBatchById(entities, DEFAULT_BATCH_SIZE_EACH_TIME);
    }

    /**
     * <p>performs batch updates by primary key(s).</p>
     *
     * <p>implementation details:</p>
     * <ul>
     *     <li>uses {@link IService#updateBatch(Collection, int)} for execution</li>
     *     <li>supports entities with composite primary keys</li>
     * </ul>
     *
     * <p>update examples:</p>
     * <ol>
     *     <li><b>single primary key:</b>
     *         <pre>{@code
     * UPDATE test_table SET name = 'test1' WHERE id = 1
     *         }</pre>
     *     </li>
     *
     *     <li><b>composite primary keys:</b>
     *         <pre>{@code
     * UPDATE test_table SET name = 'test1' WHERE id = 1 AND id2 = 2
     *         }</pre>
     *     </li>
     * </ol>
     *
     * @param entities          entities to update
     * @param batchSizeEachTime maximum number of updates per batch
     * @apiNote see <a href="https://mybatis-flex.com/zh/base/batch.html">batch update performance guidelines</a>
     * @see IService#updateBatch(Collection, int)
     */
    @Transactional(rollbackFor = Throwable.class)
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> updateBatchById(Iterable<P> entities, int batchSizeEachTime) {
        if (Nil.isEmpty(entities)) {
            return Collections.newArrayList();
        }
        List<P> needToUpdateEntities = entities instanceof List<P> actualEntities ? actualEntities : Converts.toArrayList(entities);
        Db.executeBatch(
                needToUpdateEntities,
                batchSizeEachTime,
                ClassUtil.getUsefulClass(this.getClass()),
                GenericRepository::updateById
        );
        return needToUpdateEntities;
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> updateBatchByIdIgnoreLogicDelete(P... entities) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchById(entities));
    }

    default List<P> updateBatchByIdIgnoreLogicDelete(Iterable<P> entities) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchById(entities));
    }

    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> updateBatchByIdIgnoreLogicDelete(Iterable<P> entities, int batchSizeEachTime) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> Springs.getProxy(GenericRepository.class).updateBatchById(entities, batchSizeEachTime));
    }

    /**
     * <p>performs version-controlled updates with optimistic locking.</p>
     *
     * <p>implementation workflow:</p>
     * <ul>
     *     <li>supports versioned entities</li>
     *     <li>handles composite primary keys</li>
     * </ul>
     *
     * <p>update sequence examples:</p>
     * <ol>
     *     <li><b>single primary key:</b>
     *         <pre>{@code
     * TestTablePO(entity: id=1L, name="test1", version=0)
     *         }</pre>
     *         <ol>
     *             <li>fetch current state:
     *                 <pre>{@code
     * SELECT * FROM test_table WHERE id = 1
     *                 }</pre>
     *             </li>
     *             <li>apply versioned update:
     *                 <pre>{@code
     * UPDATE test_table
     * SET name = 'test2', version = version + 1
     * WHERE id = 1 AND version = 0
     *                 }</pre>
     *             </li>
     *         </ol>
     *     </li>
     *
     *     <li><b>composite primary keys:</b>
     *         <pre>{@code
     * TestTablePO(entity: id1=1L, id2=2L, name="test1", version=0)
     *         }</pre>
     *         <ol>
     *             <li>fetch current state:
     *                 <pre>{@code
     * SELECT * FROM test_table WHERE id1 = 1 AND id2 = 2
     *                 }</pre>
     *             </li>
     *             <li>apply versioned update:
     *                 <pre>{@code
     * UPDATE test_table
     * SET name = 'test2', version = version + 1
     * WHERE id1 = 1 AND id2 = 2 AND version = 0
     *                 }</pre>
     *             </li>
     *         </ol>
     *     </li>
     * </ol>
     *
     * @param entity target entity containing update values and version
     * @see #updateById(PO)
     */
    default P updateWithVersionById(P entity) {
        setVersionFieldValue(getEntityToUpdateVersion(entity), entity);
        updateById(entity);
        return entity;
    }

    default P updateWithVersionByIdIgnoreLogicDelete(P entity) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateWithVersionById(entity));
    }

    /**
     * update batch with version by id.
     *
     * @param entities    the operate entities
     * @param getIdAction how to find the primary key in each entity
     * @apiNote only support the entity with one primary key.
     * @see #updateBatchWithVersionById(Iterable, Function, int)
     * @see #updateBatchById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    default List<P> updateBatchWithVersionById(Iterable<P> entities, Function<P, ? extends Serializable> getIdAction) {
        setVersionFieldValues(entities, getIdAction);
        return updateBatchById(entities);
    }

    /**
     * <p>performs version-controlled batch updates with optimistic locking.</p>
     *
     * <p>key constraints:</p>
     * <ul>
     *     <li>requires version field in entities</li>
     *     <li>supports single-primary-key entities only</li>
     * </ul>
     *
     * <p>execution workflow example:</p>
     * <pre>{@code
     * Entities:
     *   TestTablePO(id=1L, name="test1", version=0),
     *   TestTablePO(id=2L, name="test2", version=0)
     * }</pre>
     * <ol>
     *     <li><b>Batch fetch:</b>
     *         <pre>{@code
     * SELECT * FROM test_table WHERE id IN (1, 2)
     *         }</pre>
     *     </li>
     *
     *     <li><b>Atomic updates:</b>
     *         <pre>{@code
     * UPDATE test_table
     * SET name = 'test3', version = version + 1
     * WHERE id = 1 AND version = 0;
     *
     * UPDATE test_table
     * SET name = 'test4', version = version + 1
     * WHERE id = 2 AND version = 0;
     *         }</pre>
     *     </li>
     * </ol>
     *
     * @param entities          collection of versioned entities to update
     * @param getIdAction       function to extract primary key from entities
     * @param batchSizeEachTime maximum number of updates per batch operation
     * @apiNote <strong>only supports single-primary-key entities</strong>
     * @see #updateBatchById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    @SuppressWarnings(SuppressWarningConstant.UNCHECKED)
    default List<P> updateBatchWithVersionById(Iterable<P> entities, Function<P, ? extends Serializable> getIdAction, int batchSizeEachTime) {
        setVersionFieldValues(entities, getIdAction);
        return Springs.getProxy(GenericRepository.class).updateBatchById(entities, batchSizeEachTime);
    }

    default List<P> updateBatchWithVersionByIdIgnoreLogicDelete(Iterable<P> entities, Function<P, ? extends Serializable> getIdAction) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchWithVersionById(entities, getIdAction));
    }

    default List<P> updateBatchWithVersionByIdIgnoreLogicDelete(Iterable<P> entities, Function<P, ? extends Serializable> getIdAction, int batchSizeEachTime) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> updateBatchWithVersionById(entities, getIdAction, batchSizeEachTime));
    }

    /**
     * <p>deletes entity by composite primary keys with logical delete support.</p>
     *
     * <p>execution scenarios:</p>
     * <ol>
     *     <li><b>Logical delete (with delete flag column):</b>
     *         <ul>
     *             <li>entity example:
     *                 <pre>{@code
     * TestTablePO(entity: id1=1L, id2=2L, rowIsDeleted=null)
     *                 }</pre>
     *             </li>
     *             <li>generated SQL:
     *                 <pre>{@code
     * UPDATE test_table
     * SET row_is_deleted = TRUE
     * WHERE id1 = 1 AND id2 = 2
     *   AND row_is_deleted = FALSE
     *                 }</pre>
     *             </li>
     *         </ul>
     *     </li>
     *
     *     <li><b>Physical delete (no delete flag):</b>
     *         <ul>
     *             <li>entity example:
     *                 <pre>{@code
     * TestTablePO(entity: id1=1L, id2=2L)
     *                 }</pre>
     *             </li>
     *             <li>generated SQL:
     *                 <pre>{@code
     * DELETE FROM test_table
     * WHERE id1 = 1 AND id2 = 2
     *                 }</pre>
     *             </li>
     *         </ul>
     *     </li>
     * </ol>
     *
     * @param entity target entity with composite primary keys
     */
    default void deleteById(P entity) {
        getBaseMapper().delete(entity);
    }

    default void deleteById(Serializable id) {
        getBaseMapper().deleteById(id);
    }

    /**
     * <p>performs unconditional physical delete ignoring logical delete markers.</p>
     *
     * <p>key characteristics:</p>
     * <ul>
     *     <li>executes direct DELETE statement regardless of logical delete configuration</li>
     *     <li>recommended for entities with composite primary keys</li>
     * </ul>
     *
     * <p>execution example:</p>
     * <pre>{@code
     * Entity:
     * TestTablePO(id1=1L, id2=2L, rowIsDeleted=null)
     *
     * SQL:
     * DELETE FROM test_table
     * WHERE id1 = 1 AND id2 = 2
     * }</pre>
     *
     * @param entity target entity with composite primary keys to delete permanently
     */
    default void deleteByIdIgnoreLogicDelete(P entity) {
        LogicDeleteManager.execWithoutLogicDelete(() -> deleteById(entity));
    }

    /**
     * delete ignore logic delete.
     *
     * @param id the primary key value
     * @see #deleteByIdsIgnoreLogicDelete(Iterable)
     */
    default void deleteByIdIgnoreLogicDelete(Serializable id) {
        LogicDeleteManager.execWithoutLogicDelete(() -> deleteById(id));
    }

    /**
     * delete batch by ids.
     *
     * @param ids the primary key values
     * @see #deleteByIds(Iterable)
     */
    default void deleteByIds(Serializable... ids) {
        deleteByIds(Collections.ofHashSet(ids));
    }

    /**
     * <p>performs batch delete operations with adaptive strategy.</p>
     *
     * <p>execution strategies:</p>
     * <ol>
     *     <li><b>Logical delete (with delete flag):</b>
     *         <pre>{@code
     * UPDATE test_table
     * SET row_is_deleted = TRUE
     * WHERE id IN (1, 2, 3)
     *   AND row_is_deleted = FALSE
     *         }</pre>
     *     </li>
     *
     *     <li><b>Physical delete (no delete flag):</b>
     *         <pre>{@code
     * DELETE FROM test_table
     * WHERE id IN (1, 2, 3)
     *         }</pre>
     *     </li>
     * </ol>
     *
     * @param ids collection of primary key values to delete
     */
    default void deleteByIds(Iterable<? extends Serializable> ids) {
        if (Nil.isEmpty(ids)) {
            return;
        }
        getBaseMapper().deleteBatchByIds(ids instanceof Collection<? extends Serializable> ? (Collection<? extends Serializable>) ids : Converts.toHashSet(ids));
    }

    /**
     * delete batch ignore logic delete.
     *
     * @param ids the primary key values
     * @see #deleteByIdsIgnoreLogicDelete(Iterable)
     */
    default void deleteByIdsIgnoreLogicDelete(Serializable... ids) {
        deleteByIdsIgnoreLogicDelete(Collections.ofHashSet(ids));
    }

    /**
     * <p>performs unconditional physical delete batch operation.</p>
     *
     * <p>key characteristics:</p>
     * <ul>
     *     <li>ignores logical delete configuration</li>
     *     <li>executes direct DELETE statements</li>
     * </ul>
     *
     * <p>example execution:</p>
     * <pre>{@code
     * DELETE FROM test_table
     * WHERE id IN (1, 2, 3)
     * }</pre>
     *
     * @param ids collection of primary key values to delete permanently
     */
    default void deleteByIdsIgnoreLogicDelete(Iterable<? extends Serializable> ids) {
        LogicDeleteManager.execWithoutLogicDelete(() -> deleteByIds(ids instanceof Collection<? extends Serializable> ? ids : Converts.toHashSet(ids)));
    }

    default Optional<P> getById(Serializable id) {
        return Optional.ofNullable(getBaseMapper().selectOneById(id));
    }

    default Optional<P> getById(P entity) {
        return Optional.ofNullable(getBaseMapper().selectOneByEntityId(entity));
    }

    default Optional<P> getByIdIgnoreLogicDelete(Serializable id) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> getById(id));
    }

    default Optional<P> getByIdIgnoreLogicDelete(P entity) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> getById(entity));
    }

    default Optional<P> getByField(ColumnNameGetter<P> columnNameGetter, Object value) {
        return Optional.ofNullable(getBaseMapper().selectOneByMap(Collections.ofImmutableMap(MybatisFlexs.getColumnName(columnNameGetter), value)));
    }

    default Optional<P> getByFieldIgnoreLogicDelete(ColumnNameGetter<P> columnNameGetter, Object value) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> getByField(columnNameGetter, value));
    }

    default List<P> listByIds(Iterable<? extends Serializable> ids) {
        if (Nil.isEmpty(ids)) {
            return Collections.newArrayList();
        }
        return getBaseMapper().selectListByIds(ids instanceof Collection<? extends Serializable> ? (Collection<? extends Serializable>) ids : Converts.toHashSet(ids));
    }

    default List<P> listByIdsIgnoreLogicDelete(Iterable<? extends Serializable> ids) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> listByIdsIgnoreLogicDelete(ids));
    }

    default List<P> listByField(ColumnNameGetter<P> columnNameGetter, Object value) {
        return getBaseMapper().selectListByMap(Collections.ofImmutableMap(MybatisFlexs.getColumnName(columnNameGetter), value));
    }

    default List<P> listByFieldIgnoreLogicDelete(ColumnNameGetter<P> columnNameGetter, Object value) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> listByFieldIgnoreLogicDelete(columnNameGetter, value));
    }

    default List<P> listLikeByField(ColumnNameGetter<P> columnNameGetter, String value) {
        return getBaseMapper().selectListByQuery(QueryWrapper.create().like(MybatisFlexs.getColumnName(columnNameGetter), Nil.isNull(value) ? SymbolConstant.EMPTY : value));
    }

    default List<P> listLikeByFieldIgnoreLogicDelete(ColumnNameGetter<P> columnNameGetter, String value) {
        return LogicDeleteManager.execWithoutLogicDelete(() -> listLikeByField(columnNameGetter, value));
    }

    default List<P> listAll() {
        return getBaseMapper().selectListByQuery(QueryWrapper.create());
    }

    default List<P> listAllIgnoreLogicDelete() {
        return LogicDeleteManager.execWithoutLogicDelete(this::listAll);
    }

    default long countAll() {
        return getBaseMapper().selectCountByQuery(QueryWrapper.create());
    }

    default long countAllIgnoreLogicDelete() {
        return LogicDeleteManager.execWithoutLogicDelete(this::countAll);
    }

    default QueryChainer<P> openQuery() {
        BaseMapper<P> baseMapper = getBaseMapper();
        return new QueryChainer<>(QueryChain.of(baseMapper));
    }

    default UpdateChainer<P> openUpdate() {
        BaseMapper<P> baseMapper = getBaseMapper();
        return new UpdateChainer<>(UpdateChain.of(baseMapper));
    }

    default DeleteChainer<P> openDelete() {
        BaseMapper<P> baseMapper = getBaseMapper();
        return new DeleteChainer<>(UpdateChain.of(baseMapper));
    }

    default <U> boolean isUniqueOnSave(ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue) {
        return Nil.isNull(requireUniqueColumnValue) || Nil.isZeroValue(openQuery()
                .where(requireUniqueColumnNameGetter).equalsTo(requireUniqueColumnValue)
                .count()
        );
    }

    default <U, C> boolean isUniqueOnSave(ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue, ColumnNameGetter<P> conditionScopeColumnNameGetter, C conditionScopeColumnValue) {
        return Nil.isNull(requireUniqueColumnValue) || Nil.isZeroValue(openQuery()
                .where(requireUniqueColumnNameGetter).equalsTo(requireUniqueColumnValue)
                .and(conditionScopeColumnNameGetter).equalsTo(conditionScopeColumnValue)
                .count()
        );
    }

    default <U> boolean isUniqueOnUpdate(Serializable id, ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue) {
        return Nil.isNull(requireUniqueColumnValue) || getByField(requireUniqueColumnNameGetter, requireUniqueColumnValue)
                .map(po -> Comparators.equals(MybatisFlexs.getPrimaryKeyValue(po), id))
                .orElse(true);
    }

    default <U, C> boolean isUniqueOnUpdate(Serializable id, ColumnNameGetter<P> requireUniqueColumnNameGetter, U requireUniqueColumnValue, ColumnNameGetter<P> conditionScopeColumnNameGetter, C conditionScopeColumnValue) {
        return Nil.isNull(requireUniqueColumnValue) || openQuery()
                .where(requireUniqueColumnNameGetter).equalsTo(requireUniqueColumnValue)
                .and(conditionScopeColumnNameGetter).equalsTo(conditionScopeColumnValue)
                .get()
                .map(po -> Comparators.equals(MybatisFlexs.getPrimaryKeyValue(po), id))
                .orElse(true);
    }

    default BaseMapper<P> getBaseMapper() {
        return MybatisFlexSystemCache.getInstance().getBaseMapper(this.getClass());
    }

    private P getEntityToUpdateVersion(P updatedEntity) {
        return getById(updatedEntity).orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}update with version failed, the entity [{}] could not be found in table [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, updatedEntity.getClass().getName(), MybatisFlexs.getTableName(updatedEntity).orElse(null))));
    }

    private void setVersionFieldValue(P oldEntity, P updatedEntity) {
        String versionFieldName = MybatisFlexs.getVersionFieldName(updatedEntity).orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}update with version failed, the entity [{}] does not have the version column, please check!", ModuleView.ORM_MYBATIS_SYSTEM, updatedEntity.getClass().getName())));
        Reflects.setFieldValue(updatedEntity, versionFieldName, Reflects.getFieldValue(oldEntity, versionFieldName));
    }

    private void setVersionFieldValues(Iterable<P> updatedEntities, Function<P, ? extends Serializable> getIdAction) {
        Map<? extends Serializable, P> idMappingOldEntity = Converts.toHashMap(listByIds(Converts.toArrayList(updatedEntities, getIdAction)), getIdAction);
        Map<? extends Serializable, P> idMappingUpdatedEntity = Converts.toHashMap(updatedEntities, getIdAction);
        idMappingOldEntity.forEach((id, oldEntity) -> setVersionFieldValue(oldEntity, idMappingUpdatedEntity.get(id)));
    }

}