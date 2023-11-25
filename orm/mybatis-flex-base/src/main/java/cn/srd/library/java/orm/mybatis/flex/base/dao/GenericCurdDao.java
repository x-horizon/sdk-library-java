// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.page.PageConstant;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.orm.contract.model.base.BO;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.page.PageParam;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.converter.PageConverter;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.field.FieldQueryBuilder;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.row.Row;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.core.util.ClassUtil;
import com.mybatisflex.core.util.SqlUtil;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @param <T>
 * @author wjm
 * @since 2023-11-04 00:19
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.ALL)
public interface GenericCurdDao<T extends PO> extends BaseMapper<T> {

    /**
     * see <a href="https://mybatis-flex.com/zh/base/batch.html#basemapper-insertbatch-%E6%96%B9%E6%B3%95">"the batch operation core guide"</a>.
     */
    int SMALL_BATCH_OPERATION_SIZE = 100;

    /**
     * see {@link BaseMapper#insertSelective(Object)}
     *
     * @param entity
     * @return
     */
    default T save(T entity) {
        BaseMapper.super.insertSelective(entity);
        return entity;
    }

    /**
     * see {@link BaseMapper#insertWithPk(Object)}
     *
     * @param entity
     * @return
     */
    default T saveWithPK(T entity) {
        BaseMapper.super.insertSelectiveWithPk(entity);
        return entity;
    }

    /**
     * using {@link #DEFAULT_BATCH_SIZE} as batch size each time to insert batch to database.
     *
     * @param entities the operate entities
     * @return the entities with primary key
     * @see #SMALL_BATCH_OPERATION_SIZE
     * @see #saveBatch(Iterable, int)
     * @see BaseMapper#insertBatch(List, int)
     * @see IService#saveBatch(Collection, int)
     */
    default List<T> saveBatch(Iterable<T> entities) {
        return saveBatch(entities, DEFAULT_BATCH_SIZE);
    }

    /**
     * insert batch to database.
     * <ol>
     *   <li>if the entites size <= {@link #SMALL_BATCH_OPERATION_SIZE}, the insert logic see function {@link BaseMapper#insertBatch(List, int)}.</li>
     *   <li>if the entites size > {@link #SMALL_BATCH_OPERATION_SIZE}, the insert logic see function {@link IService#saveBatch(Collection, int)}.</li>
     * </ol>
     *
     * @param entities          the operate entities
     * @param batchSizeEachTime insert size each time
     * @return the entities with primary key
     * @apiNote about the different between {@link BaseMapper#insertBatch(List, int)} and {@link IService#saveBatch(Collection, int)}, you should see <a href="https://mybatis-flex.com/zh/base/batch.html#basemapper-insertbatch-%E6%96%B9%E6%B3%95">"the batch operation core guide"</a>.
     * @see #SMALL_BATCH_OPERATION_SIZE
     * @see BaseMapper#insertBatch(List, int)
     * @see IService#saveBatch(Collection, int)
     */
    @Transactional(rollbackFor = Throwable.class)
    default List<T> saveBatch(Iterable<T> entities, int batchSizeEachTime) {
        if (Nil.isEmpty(entities)) {
            return Collections.newArrayList();
        }
        List<T> listTypeEntities = entities instanceof List<T> ? (List<T>) entities : Converts.toList(entities);
        Assert.of()
                .setMessage("{}save batch failed, please check!", ModuleView.ORM_MYBATIS_SYSTEM)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfFalse(listTypeEntities.size() <= SMALL_BATCH_OPERATION_SIZE ?
                        Converts.toBoolean(BaseMapper.super.insertBatch(listTypeEntities, batchSizeEachTime)) :
                        SqlUtil.toBool(Db.executeBatch(listTypeEntities, batchSizeEachTime, ClassUtil.getUsefulClass(this.getClass()), BaseMapper::insertSelective))
                );
        return listTypeEntities;
    }

    default int deleteById(T entity) {
        return BaseMapper.super.delete(entity);
    }

    default int deleteBatchByIds(Iterable<? extends Serializable> ids) {
        return deleteBatchByIds(Converts.toList(ids));
    }

    default int deleteBatchByIds(Iterable<? extends Serializable> ids, int shardedSize) {
        return BaseMapper.super.deleteBatchByIds(Converts.toList(ids), shardedSize);
    }

    default int deleteBatchByIds(Collection<? extends Serializable> ids, int shardedSize) {
        return BaseMapper.super.deleteBatchByIds(Converts.toList(ids), shardedSize);
    }

    // TODO wjm 虽然这个方法叫 updateBatch，但一样可以执行 insert、delete、update 等任何 SQL； 这个方法类似 Spring 的 jdbcTemplate.batchUpdate() 方法。
    // TODO wjm Db.updateEntitiesBatch 这个方法用于批量根据 id 更新 entity，其是对 Db.executeBatch 的封装，使用代码如下：
    default int updateById(T entity) {
        return BaseMapper.super.update(entity);
    }

    default int updateByCondition(T entity, QueryWrapper queryWrapper) {
        return BaseMapper.super.updateByQuery(entity, queryWrapper);
    }

    default Optional<T> getById(Serializable id) {
        return Optional.ofNullable(selectOneById(id));
    }

    default Optional<T> getById(T entity) {
        return Optional.ofNullable(BaseMapper.super.selectOneByEntityId(entity));
    }

    default Optional<T> getByCondition(QueryWrapper queryWrapper) {
        return Optional.ofNullable(BaseMapper.super.selectOneByQuery(queryWrapper));
    }

    default List<T> listByIds(Iterable<? extends Serializable> ids) {
        return selectListByIds(Converts.toList(ids));
    }

    default List<T> listByCondition(QueryWrapper queryWrapper) {
        return selectListByQuery(queryWrapper);
    }

    default <R extends BO> List<R> listByCondition(QueryWrapper queryWrapper, Class<R> asType) {
        return BaseMapper.super.selectListByQueryAs(queryWrapper, asType);
    }

    default List<T> listAll() {
        return listByCondition(QueryWrapper.create());
    }

    // TODO wjm totalRow 的说明 在一般的分页场景中，只有第一页的时候有必要去查询数据总量，第二页以后是没必要的（因为第一页已经拿到总量了），因此， 第二页的时候，我们可以带入 totalRow，这样能提高程序的查询效率。
    default PageResult<T> pageByCondition(QueryWrapper queryWrapper) {
        return pageByCondition(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE, queryWrapper);
    }

    default PageResult<T> pageByCondition(PageParam pageParam, QueryWrapper queryWrapper) {
        return pageByCondition(pageParam.getPageIndex(), pageParam.getPageSize(), queryWrapper);
    }

    default PageResult<T> pageByCondition(Number pageIndex, Number pageSize, QueryWrapper queryWrapper) {
        return PageConverter.INSTANCE.toPageResult(BaseMapper.super.paginate(pageIndex, pageSize, queryWrapper));
    }

    default <R extends BO> PageResult<R> pageByCondition(PageParam pageParam, QueryWrapper queryWrapper, Class<R> asType) {
        return pageByCondition(pageParam.getPageIndex(), pageParam.getPageSize(), queryWrapper, asType);
    }

    default <R extends BO> PageResult<R> pageByCondition(Number pageIndex, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        return PageConverter.INSTANCE.toPageResult(BaseMapper.super.paginateAs(pageIndex, pageSize, queryWrapper, asType));
    }

    default long countByCondition(QueryWrapper queryWrapper) {
        return BaseMapper.super.selectCountByQuery(queryWrapper);
    }

    default long countAll() {
        return countByCondition(QueryWrapper.create());
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked most mybatis-flex base mapper funcations as deprecated, since mybatis-flex version 1.7.3, it is not recommended to use as following:

    @Deprecated
    @Override
    default int insert(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertSelective(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertWithPk(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertSelectiveWithPk(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertBatch(List<T> entities, int size) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertOrUpdate(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertOrUpdateSelective(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertOrUpdate(T entity, boolean ignoreNulls) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int delete(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int update(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByMap(T entity, Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByMap(T entity, boolean ignoreNulls, Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByCondition(T entity, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByCondition(T entity, boolean ignoreNulls, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    default int updateByQuery(T entity, QueryWrapper queryWrapper) {
        return BaseMapper.super.updateByQuery(entity, queryWrapper);
    }

    @Deprecated
    @Override
    default T selectOneByEntityId(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectOneByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsById(Serializable id) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectOneWithRelationsByIdAs(Serializable id, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectOneWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByMap(Map<String, Object> whereConditions, Long count) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByCondition(QueryCondition whereConditions, Long count) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByQuery(QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    Cursor<T> selectCursorByQuery(QueryWrapper queryWrapper);

    @Deprecated
    List<Row> selectRowsByQuery(QueryWrapper queryWrapper);

    @Deprecated
    @Override
    default <R> List<R> selectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListWithRelationsByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectListWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectListWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectAll() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectAllWithRelations() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Object selectObjectByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectObjectByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectObjectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default long selectCountByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default long selectCountByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, Number totalRow, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, Number totalRow, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Page<T> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Page<T> page, QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Page<T> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Page<T> page, QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Number pageNumber, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Number pageNumber, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, QueryWrapper queryWrapper, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, String countSelectId, Page<E> page, QueryWrapper queryWrapper, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

}
