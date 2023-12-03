// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.orm.contract.model.base.PO;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.mybatis.Mappers;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.*;
import com.mybatisflex.core.table.TableDef;
import com.mybatisflex.core.util.LambdaGetter;
import com.mybatisflex.core.util.LambdaUtil;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @param <T> the entity extends {@link PO}
 * @author wjm
 * @since 2023-11-28 22:57
 */
@SuppressWarnings(SuppressWarningConstant.ALL)
public class ConstrainedQueryChain<T extends PO> extends QueryWrapperAdapter<ConstrainedQueryChain<T>> implements MapperQueryChain<T> {

    @Serial private static final long serialVersionUID = 7214746557544965890L;

    private final BaseMapper<T> baseMapper;

    public ConstrainedQueryChain(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    public static <T extends PO> ConstrainedQueryChain<T> of(Class<T> entityClass) {
        return new ConstrainedQueryChain<>(Mappers.ofEntityClass(entityClass));
    }

    public static <T extends PO> ConstrainedQueryChain<T> of(BaseMapper<T> baseMapper) {
        return new ConstrainedQueryChain<>(baseMapper);
    }

    @Override
    public BaseMapper<T> baseMapper() {
        return this.baseMapper;
    }

    @Override
    public QueryWrapper toQueryWrapper() {
        return this;
    }

    @Override
    public ConstrainedQueryChain<T> from(QueryTable... tables) {
        return super.from(tables);
    }

    public ConstrainedQueryConditionAppender<ConstrainedQueryChain<T>> where(QueryColumnFunction<T> columnValueAction) {
        return new ConstrainedQueryConditionAppender<>(this, LambdaUtil.getQueryColumn(columnValueAction), SqlConnector.AND);
    }

    @Override
    public ConstrainedQueryChain<T> and(QueryCondition queryCondition) {
        return super.and(queryCondition);
    }

    @Override
    public List<T> list() {
        return MapperQueryChain.super.list();
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked most mybatis-flex original functions as deprecated, since mybatis-flex version 1.7.5, it is not recommended to use as following:

    @Deprecated
    public static QueryWrapper create() {
        throw new UnsupportedException();
    }

    @Deprecated
    public static QueryWrapper create(Object entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    public static QueryWrapper create(Object entity, SqlOperators operators) {
        throw new UnsupportedException();
    }

    @Deprecated
    public static QueryWrapper create(Map map) {
        throw new UnsupportedException();
    }

    @Deprecated
    public static QueryWrapper create(Map map, SqlOperators operators) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public String toSQL() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public long count() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public boolean exists() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public T one() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <R> R oneAs(Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Object obj() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <R> R objAs(Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Optional<Object> objOpt() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <R> Optional<R> objAsOpt(Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public List<Object> objList() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <R> List<R> objListAs(Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <R> List<R> listAs(Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Page<T> page(Page<T> page) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <R> Page<R> pageAs(Page<R> page, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public FieldsBuilder<T> withFields() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public RelationsBuilder<T> withRelations() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Optional<T> oneOpt() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <R> Optional<R> oneAsOpt(Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public WithBuilder<ConstrainedQueryChain<T>> with(String name) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public WithBuilder<ConstrainedQueryChain<T>> with(String name, String... params) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public WithBuilder<ConstrainedQueryChain<T>> withRecursive(String name) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public WithBuilder<ConstrainedQueryChain<T>> withRecursive(String name, String... params) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> select() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> select(String... columns) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> select(LambdaGetter<U>... lambdaGetters) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> select(QueryColumn... queryColumns) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> select(Iterable<QueryColumn> queryColumns) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> select(QueryColumn[]... queryColumns) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> select(QueryColumn[] queryColumns, QueryColumn... queryColumns2) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> from(TableDef... tableDefs) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> from(Class<?>... entityClasses) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> from(String... tables) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> from(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> as(String alias) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> where(String sql) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> where(String sql, Object... params) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> where(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> where(Map<String, Object> whereConditions, SqlOperators operators) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> QueryConditionBuilder<ConstrainedQueryChain<T>> where(LambdaGetter<U> fn) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> where(Consumer<QueryWrapper> consumer) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> where(QueryCondition queryCondition) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> and(String sql) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> and(String sql, Object... params) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> QueryConditionBuilder<ConstrainedQueryChain<T>> and(LambdaGetter<U> fn) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> and(Consumer<QueryWrapper> consumer) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> and(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> and(Map<String, Object> whereConditions, SqlOperators operators) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> and(Map<String, Object> whereConditions, SqlOperators operators, SqlConnector innerConnector) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> or(QueryCondition queryCondition) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> or(String sql) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> or(String sql, Object... params) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> QueryConditionBuilder<ConstrainedQueryChain<T>> or(LambdaGetter<U> fn) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> or(Consumer<QueryWrapper> consumer) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> or(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> or(Map<String, Object> whereConditions, SqlOperators operators) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> or(Map<String, Object> whereConditions, SqlOperators operators, SqlConnector innerConnector) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(String table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(String table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(Class entityClass) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(Class entityClass, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(TableDef table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(TableDef table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(QueryWrapper table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> leftJoin(QueryWrapper table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(String table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(String table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(Class entityClass) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(Class entityClass, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(TableDef table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(TableDef table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(QueryWrapper table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> rightJoin(QueryWrapper table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(String table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(String table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(Class entityClass) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(Class entityClass, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(TableDef table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(TableDef table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(QueryWrapper table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> innerJoin(QueryWrapper table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(String table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(String table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(Class entityClass) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(Class entityClass, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(TableDef table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(TableDef table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(QueryWrapper table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> fullJoin(QueryWrapper table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(String table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(String table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(Class entityClass) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(Class entityClass, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(TableDef table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(TableDef table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(QueryWrapper table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> crossJoin(QueryWrapper table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(String table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(String table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(Class entityClass) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(Class entityClass, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(TableDef table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(TableDef table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(QueryWrapper table) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public Joiner<ConstrainedQueryChain<T>> join(QueryWrapper table, boolean when) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> union(QueryWrapper unionQuery) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> unionAll(QueryWrapper unionQuery) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> forUpdate() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> forUpdateNoWait() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> groupBy(String name) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> groupBy(String... names) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> groupBy(QueryColumn column) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> groupBy(QueryColumn... columns) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> groupBy(LambdaGetter<U> column) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> groupBy(LambdaGetter<U>... columns) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> having(QueryCondition queryCondition) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> orderBy(QueryColumn column, Boolean asc) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> orderBy(QueryOrderBy... orderBys) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> orderBy(LambdaGetter<U> column, Boolean asc) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> QueryOrderByBuilder<ConstrainedQueryChain<T>> orderBy(LambdaGetter<U> getter) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> orderBy(String column, Boolean asc) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> orderBy(String... orderBys) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> limit(Number rows) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> offset(Number offset) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> limit(Number offset, Number rows) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> datasource(String datasource) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> hint(String hint) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> eq(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> eq(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> eq(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> eq(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> eq(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> eq(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> ne(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> ne(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> ne(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> ne(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> ne(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> ne(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> gt(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> gt(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> gt(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> gt(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> gt(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> gt(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> ge(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> ge(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> ge(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> ge(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> ge(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> ge(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> lt(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> lt(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> lt(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> lt(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> lt(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> lt(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> le(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> le(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> le(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> le(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> le(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> le(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> in(String column, Object... values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> in(LambdaGetter<U> column, Object... values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> in(String column, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> in(LambdaGetter<U> column, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> in(String column, Collection<?> values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> in(LambdaGetter<U> column, Collection<?> values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> in(String column, Object[] values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> in(LambdaGetter<U> column, Object[] values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> in(String column, Collection<?> values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> in(LambdaGetter<U> column, Collection<?> values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> in(String column, QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> in(LambdaGetter<U> column, QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> in(String column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> in(LambdaGetter<U> column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notIn(String column, Object... values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notIn(LambdaGetter<U> column, Object... values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notIn(String column, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notIn(LambdaGetter<U> column, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notIn(String column, Collection<?> values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notIn(LambdaGetter<U> column, Collection<?> values) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notIn(String column, Object[] values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notIn(LambdaGetter<U> column, Object[] values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notIn(String column, Collection<?> values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notIn(LambdaGetter<U> column, Collection<?> values, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notIn(String column, QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notIn(LambdaGetter<U> column, QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notIn(String column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notIn(LambdaGetter<U> column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> between(String column, Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> between(LambdaGetter<U> column, Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> between(String column, Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> between(LambdaGetter<U> column, Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> between(String column, Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> between(LambdaGetter<U> column, Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notBetween(String column, Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notBetween(LambdaGetter<U> column, Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notBetween(String column, Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notBetween(LambdaGetter<U> column, Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notBetween(String column, Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notBetween(LambdaGetter<U> column, Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> like(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> like(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> like(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> like(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> like(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> like(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> likeLeft(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> likeLeft(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> likeLeft(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> likeLeft(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> likeLeft(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> likeLeft(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> likeRight(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> likeRight(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> likeRight(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> likeRight(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> likeRight(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> likeRight(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notLike(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notLike(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notLike(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notLike(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> notLike(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> notLike(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notLikeLeft(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notLikeLeft(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notLikeLeft(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notLikeLeft(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> notLikeLeft(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> notLikeLeft(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notLikeRight(String column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notLikeRight(LambdaGetter<U> column, Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> notLikeRight(String column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> notLikeRight(LambdaGetter<U> column, Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <V> ConstrainedQueryChain<T> notLikeRight(String column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U, V> ConstrainedQueryChain<T> notLikeRight(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> isNull(String column) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> isNull(LambdaGetter<U> column) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> isNull(String column, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> isNull(LambdaGetter<U> column, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> isNull(String column, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> isNull(LambdaGetter<U> column, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> isNotNull(String column) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> isNotNull(LambdaGetter<U> column) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> isNotNull(String column, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> isNotNull(LambdaGetter<U> column, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> isNotNull(String column, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <U> ConstrainedQueryChain<T> isNotNull(LambdaGetter<U> column, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public ConstrainedQueryChain<T> clone() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryWrapper and(Consumer<QueryWrapper> consumer, boolean condition) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryWrapper or(Consumer<QueryWrapper> consumer, boolean condition) {
        throw new UnsupportedException();
    }

}
