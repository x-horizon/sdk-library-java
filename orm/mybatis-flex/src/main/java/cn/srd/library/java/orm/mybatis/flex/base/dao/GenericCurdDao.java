// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.field.FieldQueryBuilder;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
@SuppressWarnings(SuppressWarningConstant.ALL)
public interface GenericCurdDao<T> extends BaseMapper<T> {

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
    default T saveWithPrimaryKey(T entity) {
        BaseMapper.super.insertSelectiveWithPk(entity);
        return entity;
    }

    default List<T> saveBatch(Iterable<T> entities) {
        return saveBatch(Collections.toList(entities));
    }

    default List<T> saveBatch(Collection<T> entities) {
        return saveBatch(Collections.toList(entities));
    }

    default List<T> saveBatch(List<T> entities) {
        insertBatch(entities);
        return entities;
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked all mybatis-flex base mapper funcations as deprecated, since mybatis-flex version 1.7.3, it is not recommended to use those following functions:

    @Override
    @Deprecated
    default int insert(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int insertSelective(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int insertWithPk(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int insertSelectiveWithPk(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int insertBatch(List<T> entities, int size) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int insertOrUpdate(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int insertOrUpdateSelective(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int insertOrUpdate(T entity, boolean ignoreNulls) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int delete(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int deleteBatchByIds(List<? extends Serializable> ids, int size) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int deleteByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int deleteByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int update(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int updateByMap(T entity, Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int updateByMap(T entity, boolean ignoreNulls, Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int updateByCondition(T entity, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int updateByCondition(T entity, boolean ignoreNulls, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default int updateByQuery(T entity, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneByEntityId(T entity) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> R selectOneByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneWithRelationsByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneWithRelationsByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneWithRelationsByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default T selectOneWithRelationsById(Serializable id) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> R selectOneWithRelationsByIdAs(Serializable id, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> R selectOneWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectListByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectListByMap(Map<String, Object> whereConditions, Long count) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectListByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectListByCondition(QueryCondition whereConditions, Long count) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectListByQuery(QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> List<R> selectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> List<R> selectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectListWithRelationsByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> List<R> selectListWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> List<R> selectListWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectAll() {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default List<T> selectAllWithRelations() {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Object selectObjectByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> R selectObjectByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> List<R> selectObjectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default long selectCountByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default long selectCountByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginate(Number pageNumber, Number pageSize, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginate(Number pageNumber, Number pageSize, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginate(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginate(Number pageNumber, Number pageSize, Number totalRow, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, Number totalRow, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginate(Page<T> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginate(Page<T> page, QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginateWithRelations(Page<T> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default Page<T> paginateWithRelations(Page<T> page, QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateAs(Number pageNumber, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateAs(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateWithRelationsAs(Number pageNumber, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateWithRelationsAs(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateWithRelationsAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <R> Page<R> paginateWithRelationsAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, QueryWrapper queryWrapper, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

    @Override
    @Deprecated
    default <E> Page<E> xmlPaginate(String dataSelectId, String countSelectId, Page<E> page, QueryWrapper queryWrapper, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

}
