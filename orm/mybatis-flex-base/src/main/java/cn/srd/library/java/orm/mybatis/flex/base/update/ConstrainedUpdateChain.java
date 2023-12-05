// // Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// // Use of this source code is governed by SRD.
// // license that can be found in the LICENSE file.
//
// package cn.srd.library.java.orm.mybatis.flex.base.update;
//
// import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
// import cn.srd.library.java.contract.model.throwable.UnsupportedException;
// import cn.srd.library.java.orm.contract.model.base.PO;
// import cn.srd.library.java.orm.mybatis.flex.base.query.ConstrainedQueryConditionAppender;
// import cn.srd.library.java.orm.mybatis.flex.base.query.QueryColumnValueGetter;
// import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
// import cn.srd.library.java.tool.lang.functional.If;
// import cn.srd.library.java.tool.lang.reflect.Reflects;
// import com.mybatisflex.core.BaseMapper;
// import com.mybatisflex.core.constant.SqlConnector;
// import com.mybatisflex.core.query.*;
// import com.mybatisflex.core.table.TableDef;
// import com.mybatisflex.core.update.PropertySetter;
// import com.mybatisflex.core.update.UpdateChain;
// import com.mybatisflex.core.update.UpdateWrapper;
// import com.mybatisflex.core.util.LambdaGetter;
//
// import java.io.Serial;
// import java.util.Collection;
// import java.util.List;
// import java.util.Map;
// import java.util.function.BooleanSupplier;
// import java.util.function.Consumer;
// import java.util.function.Predicate;
//
// /**
//  * @param <T> the entity extends {@link PO}
//  * @author wjm
//  * @since 2023-11-28 22:57
//  */
// @SuppressWarnings(SuppressWarningConstant.ALL)
// public class ConstrainedUpdateChain<T extends PO> extends QueryWrapperAdapter<ConstrainedUpdateChain<T>> implements PropertySetter<ConstrainedUpdateChain<T>> {
//
//     private static final String ENTITY_FIELD_NAME = "entity";
//
//     @Serial private static final long serialVersionUID = -8659002936965579370L;
//
//     private final BaseMapper<T> baseMapper;
//
//     private final UpdateWrapper updateWrapper;
//
//     private final T entity;
//
//     public ConstrainedUpdateChain(BaseMapper<T> baseMapper) {
//         this.baseMapper = baseMapper;
//         this.entity = (T) Reflects.getFieldValue(UpdateChain.of(baseMapper), ENTITY_FIELD_NAME);
//         this.updateWrapper = (UpdateWrapper) entity;
//     }
//
//     public static <T extends PO> ConstrainedUpdateChain<T> of(BaseMapper<T> baseMapper) {
//         return new ConstrainedUpdateChain<>(baseMapper);
//     }
//
//     public ConstrainedQueryConditionAppender<ConstrainedUpdateChain<T>> where(QueryColumnValueGetter<T> queryColumnValueGetter) {
//         return new ConstrainedQueryConditionAppender<>(this, MybatisFlexs.getQueryColumn(queryColumnValueGetter), SqlConnector.AND);
//     }
//
//     @Override
//     public ConstrainedUpdateChain<T> where(QueryCondition queryCondition) {
//         return super.where(queryCondition);
//     }
//
//     public ConstrainedQueryConditionAppender<ConstrainedUpdateChain<T>> and(QueryColumnValueGetter<T> queryColumnValueGetter) {
//         return new ConstrainedQueryConditionAppender<>(this, MybatisFlexs.getQueryColumn(queryColumnValueGetter), SqlConnector.AND);
//     }
//
//     @Override
//     public ConstrainedUpdateChain<T> and(QueryCondition queryCondition) {
//         return super.and(queryCondition);
//     }
//
//     public ConstrainedUpdateChain<T> set(QueryColumnValueGetter<T> queryColumnValueGetter, Object value) {
//         return set(MybatisFlexs.getQueryColumn(queryColumnValueGetter), value);
//     }
//
//     @Override
//     public ConstrainedUpdateChain<T> set(QueryColumn queryColumn, Object value) {
//         return set(queryColumn, value, true);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotNull(QueryColumnValueGetter<T> queryColumnValueGetter, Object value) {
//         return set(queryColumnValueGetter, value, If::notNull);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotNull(QueryColumn queryColumn, Object value) {
//         return set(queryColumn, value, If::notNull);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotZeroValue(QueryColumnValueGetter<T> queryColumnValueGetter, Number value) {
//         return set(queryColumnValueGetter, value, If::notZeroValue);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotZeroValue(QueryColumn queryColumn, Number value) {
//         return set(queryColumn, value, If::notZeroValue);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotEmpty(QueryColumnValueGetter<T> queryColumnValueGetter, CharSequence value) {
//         return set(queryColumnValueGetter, value, If::notEmpty);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotEmpty(QueryColumn queryColumn, CharSequence value) {
//         return set(queryColumn, value, If::notEmpty);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotBlank(QueryColumnValueGetter<T> queryColumnValueGetter, CharSequence value) {
//         return set(queryColumnValueGetter, value, If::notBlank);
//     }
//
//     public ConstrainedUpdateChain<T> setIfNotBlank(QueryColumn queryColumn, CharSequence value) {
//         return set(queryColumn, value, If::notBlank);
//     }
//
//     public ConstrainedUpdateChain<T> set(QueryColumnValueGetter<T> queryColumnValueGetter, Object value, BooleanSupplier condition) {
//         return set(queryColumnValueGetter, value, condition.getAsBoolean());
//     }
//
//     public ConstrainedUpdateChain<T> set(QueryColumn queryColumn, Object value, BooleanSupplier condition) {
//         return set(queryColumn, value, condition.getAsBoolean());
//     }
//
//     public <U> ConstrainedUpdateChain<T> set(QueryColumnValueGetter<T> queryColumnValueGetter, U value, Predicate<U> condition) {
//         return set(queryColumnValueGetter, value, condition.test(value));
//     }
//
//     public <U> ConstrainedUpdateChain<T> set(QueryColumn queryColumn, U value, Predicate<U> condition) {
//         return set(queryColumn, value, condition.test(value));
//     }
//
//     public ConstrainedUpdateChain<T> set(QueryColumnValueGetter<T> queryColumnValueGetter, Object value, boolean condition) {
//         return set(MybatisFlexs.getQueryColumn(queryColumnValueGetter), value, condition);
//     }
//
//     public ConstrainedUpdateChain<T> set(QueryColumn queryColumn, Object value, boolean condition) {
//         updateWrapper.set(queryColumn, value, condition);
//         return this;
//     }
//
//     // =======================================================================================================================================================
//     // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
//     // marked most mybatis-flex original functions as deprecated, since mybatis-flex version 1.7.5, it is not recommended to use as following:
//
//     @Deprecated
//     @Override
//     public WithBuilder<ConstrainedUpdateChain<T>> with(String name) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public WithBuilder<ConstrainedUpdateChain<T>> with(String name, String... params) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public WithBuilder<ConstrainedUpdateChain<T>> withRecursive(String name) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public WithBuilder<ConstrainedUpdateChain<T>> withRecursive(String name, String... params) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> select() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> select(String... columns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> select(LambdaGetter<U>... lambdaGetters) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> select(QueryColumn... queryColumns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> select(Iterable<QueryColumn> queryColumns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> select(QueryColumn[]... queryColumns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> select(QueryColumn[] queryColumns, QueryColumn... queryColumns2) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> from(TableDef... tableDefs) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> from(Class<?>... entityClasses) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> from(String... tables) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> from(QueryTable... tables) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> from(QueryWrapper queryWrapper) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> as(String alias) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> where(String sql) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> where(String sql, Object... params) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> where(Map<String, Object> whereConditions) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> where(Map<String, Object> whereConditions, SqlOperators operators) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> QueryConditionBuilder<ConstrainedUpdateChain<T>> where(LambdaGetter<U> fn) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> where(Consumer<QueryWrapper> consumer) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> and(String sql) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> and(String sql, Object... params) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> QueryConditionBuilder<ConstrainedUpdateChain<T>> and(LambdaGetter<U> fn) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> and(Consumer<QueryWrapper> consumer) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> and(Map<String, Object> whereConditions) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> and(Map<String, Object> whereConditions, SqlOperators operators) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> and(Map<String, Object> whereConditions, SqlOperators operators, SqlConnector innerConnector) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> or(QueryCondition queryCondition) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> or(String sql) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> or(String sql, Object... params) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> QueryConditionBuilder<ConstrainedUpdateChain<T>> or(LambdaGetter<U> fn) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> or(Consumer<QueryWrapper> consumer) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> or(Map<String, Object> whereConditions) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> or(Map<String, Object> whereConditions, SqlOperators operators) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> or(Map<String, Object> whereConditions, SqlOperators operators, SqlConnector innerConnector) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(String table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(String table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(Class entityClass) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(Class entityClass, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(TableDef table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(TableDef table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(QueryWrapper table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> leftJoin(QueryWrapper table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(String table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(String table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(Class entityClass) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(Class entityClass, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(TableDef table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(TableDef table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(QueryWrapper table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> rightJoin(QueryWrapper table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(String table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(String table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(Class entityClass) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(Class entityClass, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(TableDef table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(TableDef table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(QueryWrapper table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> innerJoin(QueryWrapper table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(String table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(String table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(Class entityClass) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(Class entityClass, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(TableDef table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(TableDef table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(QueryWrapper table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> fullJoin(QueryWrapper table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(String table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(String table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(Class entityClass) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(Class entityClass, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(TableDef table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(TableDef table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(QueryWrapper table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> crossJoin(QueryWrapper table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(String table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(String table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(Class entityClass) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(Class entityClass, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(TableDef table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(TableDef table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(QueryWrapper table) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public Joiner<ConstrainedUpdateChain<T>> join(QueryWrapper table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> union(QueryWrapper unionQuery) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> unionAll(QueryWrapper unionQuery) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> forUpdate() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> forUpdateNoWait() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> groupBy(String name) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> groupBy(String... names) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> groupBy(QueryColumn column) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> groupBy(QueryColumn... columns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> groupBy(LambdaGetter<U> column) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> groupBy(LambdaGetter<U>... columns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> having(QueryCondition queryCondition) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> orderBy(QueryColumn column, Boolean asc) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> orderBy(QueryOrderBy... orderBys) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> orderBy(LambdaGetter<U> column, Boolean asc) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> QueryOrderByBuilder<ConstrainedUpdateChain<T>> orderBy(LambdaGetter<U> getter) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> orderBy(String column, Boolean asc) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> orderBy(String... orderBys) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> limit(Number rows) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> offset(Number offset) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> limit(Number offset, Number rows) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> datasource(String datasource) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> hint(String hint) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> eq(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> eq(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> eq(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> eq(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> eq(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> eq(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> ne(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> ne(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> ne(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> ne(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> ne(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> ne(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> gt(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> gt(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> gt(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> gt(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> gt(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> gt(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> ge(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> ge(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> ge(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> ge(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> ge(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> ge(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> lt(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> lt(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> lt(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> lt(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> lt(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> lt(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> le(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> le(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> le(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> le(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> le(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> le(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> in(String column, Object... values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> in(LambdaGetter<U> column, Object... values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> in(String column, QueryWrapper queryWrapper) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> in(LambdaGetter<U> column, QueryWrapper queryWrapper) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> in(String column, Collection<?> values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> in(LambdaGetter<U> column, Collection<?> values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> in(String column, Object[] values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> in(LambdaGetter<U> column, Object[] values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> in(String column, Collection<?> values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> in(LambdaGetter<U> column, Collection<?> values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> in(String column, QueryWrapper queryWrapper, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> in(LambdaGetter<U> column, QueryWrapper queryWrapper, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> in(String column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> in(LambdaGetter<U> column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notIn(String column, Object... values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notIn(LambdaGetter<U> column, Object... values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notIn(String column, QueryWrapper queryWrapper) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notIn(LambdaGetter<U> column, QueryWrapper queryWrapper) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notIn(String column, Collection<?> values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notIn(LambdaGetter<U> column, Collection<?> values) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notIn(String column, Object[] values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notIn(LambdaGetter<U> column, Object[] values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notIn(String column, Collection<?> values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notIn(LambdaGetter<U> column, Collection<?> values, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notIn(String column, QueryWrapper queryWrapper, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notIn(LambdaGetter<U> column, QueryWrapper queryWrapper, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notIn(String column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notIn(LambdaGetter<U> column, QueryWrapper queryWrapper, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> between(String column, Object start, Object end) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> between(LambdaGetter<U> column, Object start, Object end) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> between(String column, Object start, Object end, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> between(LambdaGetter<U> column, Object start, Object end, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> between(String column, Object start, Object end, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> between(LambdaGetter<U> column, Object start, Object end, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notBetween(String column, Object start, Object end) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notBetween(LambdaGetter<U> column, Object start, Object end) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notBetween(String column, Object start, Object end, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notBetween(LambdaGetter<U> column, Object start, Object end, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notBetween(String column, Object start, Object end, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notBetween(LambdaGetter<U> column, Object start, Object end, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> like(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> like(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> like(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> like(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> like(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> like(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> likeLeft(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> likeLeft(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> likeLeft(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> likeLeft(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> likeLeft(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> likeLeft(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> likeRight(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> likeRight(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> likeRight(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> likeRight(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> likeRight(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> likeRight(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notLike(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notLike(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notLike(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notLike(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> notLike(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> notLike(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notLikeLeft(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notLikeLeft(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notLikeLeft(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notLikeLeft(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> notLikeLeft(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> notLikeLeft(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notLikeRight(String column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notLikeRight(LambdaGetter<U> column, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> notLikeRight(String column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> notLikeRight(LambdaGetter<U> column, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> notLikeRight(String column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> notLikeRight(LambdaGetter<U> column, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> isNull(String column) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> isNull(LambdaGetter<U> column) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> isNull(String column, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> isNull(LambdaGetter<U> column, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> isNull(String column, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> isNull(LambdaGetter<U> column, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> isNotNull(String column) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> isNotNull(LambdaGetter<U> column) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> isNotNull(String column, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> isNotNull(LambdaGetter<U> column, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> isNotNull(String column, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> isNotNull(LambdaGetter<U> column, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> clone() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public QueryWrapper and(Consumer<QueryWrapper> consumer, boolean condition) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public QueryWrapper or(Consumer<QueryWrapper> consumer, boolean condition) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper connectMap(Map<String, Object> mapConditions, SqlOperators operators, SqlConnector outerConnector, SqlConnector innerConnector) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected <T extends QueryWrapper> Joiner<T> joining(String type, QueryTable table, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected <T extends QueryWrapper> Joiner<T> joining(String type, Class<?> entityClass, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected <T extends QueryWrapper> Joiner<T> joining(String type, QueryWrapper queryWrapper, boolean when) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public String toSQL() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public void clear() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper addSelectColumn(QueryColumn queryColumn) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper addJoin(Join join) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper setWhereQueryCondition(QueryCondition queryCondition) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper addWhereQueryCondition(QueryCondition queryCondition, SqlConnector connector) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper addGroupByColumns(QueryColumn queryColumn) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper addHavingQueryCondition(QueryCondition queryCondition, SqlConnector connector) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryWrapper addOrderBy(QueryOrderBy queryOrderBy) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void addJoinTable(QueryTable queryTable) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void addEndFragment(String fragment) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<QueryTable> getQueryTables() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setQueryTables(List<QueryTable> queryTables) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected String getDataSource() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setDataSource(String dataSource) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected String getHint() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setHint(String hint) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<QueryColumn> getSelectColumns() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setSelectColumns(List<QueryColumn> selectColumns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<Join> getJoins() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setJoins(List<Join> joins) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<QueryTable> getJoinTables() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setJoinTables(List<QueryTable> joinTables) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryCondition getWhereQueryCondition() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<QueryColumn> getGroupByColumns() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setGroupByColumns(List<QueryColumn> groupByColumns) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected QueryCondition getHavingQueryCondition() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setHavingQueryCondition(QueryCondition havingQueryCondition) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<QueryOrderBy> getOrderBys() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setOrderBys(List<QueryOrderBy> orderBys) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<UnionWrapper> getUnions() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setUnions(List<UnionWrapper> unions) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected Long getLimitOffset() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setLimitOffset(Long limitOffset) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected Long getLimitRows() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setLimitRows(Long limitRows) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected List<String> getEndFragments() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setEndFragments(List<String> endFragments) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected Map<String, Object> getContext() {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void setContext(Map<String, Object> context) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected void putContext(String key, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     protected <R> R getContext(String key) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> set(String property, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> set(String property, Object value, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> set(String property, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> set(QueryColumn property, Object value, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> set(QueryColumn property, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> set(LambdaGetter<U> property, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> set(LambdaGetter<U> property, Object value, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> set(LambdaGetter<U> property, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> setRaw(String property, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> setRaw(String property, Object value, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> setRaw(String property, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> setRaw(QueryColumn property, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> setRaw(QueryColumn property, Object value, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <V> ConstrainedUpdateChain<T> setRaw(QueryColumn property, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> setRaw(LambdaGetter<U> property, Object value) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> setRaw(LambdaGetter<U> property, Object value, BooleanSupplier isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U, V> ConstrainedUpdateChain<T> setRaw(LambdaGetter<U> property, V value, Predicate<V> isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> set(String property, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> set(QueryColumn property, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> set(LambdaGetter<U> property, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> setRaw(String property, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public ConstrainedUpdateChain<T> setRaw(QueryColumn property, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
//     @Deprecated
//     @Override
//     public <U> ConstrainedUpdateChain<T> setRaw(LambdaGetter<U> property, Object value, boolean isEffective) {
//         throw new UnsupportedException();
//     }
//
// }
