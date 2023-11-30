// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.functional.If;
import cn.srd.library.java.tool.lang.object.Nil;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryTable;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * TODO wjm 在使用函数时，一些数字、字符串常量需要通过特定的方法去构造，例如：
 *  TODO wjm number()：构建数字常量
 *  TODO wjm string()：构建字符串常量
 *  TODO wjm column()：构建自定义列
 *  TODO wjm 目前，MyBatis-Flex 已支持 110+ 个常见的 SQL 函数，查看已支持的 所有函数。 若还不满足，您可以参考 QueryMethods ，然后在自己的项目里进行自定义扩展。
 * generic query column
 *
 * @author wjm
 * @since 2023-11-24 11:38
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.ALL)
public class GenericQueryColumn extends QueryColumn {

    @Serial private static final long serialVersionUID = -6751799572163676067L;

    public GenericQueryColumn() {
    }

    public GenericQueryColumn(String name) {
        super(name);
    }

    public GenericQueryColumn(String tableName, String name) {
        super(tableName, name);
    }

    public GenericQueryColumn(String schema, String tableName, String name) {
        super(schema, tableName, name);
    }

    public GenericQueryColumn(String schema, String tableName, String name, String alias) {
        super(schema, tableName, name, alias);
    }

    public GenericQueryColumn(QueryTable queryTable, String name) {
        super(queryTable, name);
    }

    public GenericQueryColumn(TableDef tableDef, String name) {
        super(tableDef, name);
    }

    public GenericQueryColumn(TableDef tableDef, String name, String alias) {
        super(tableDef, name, alias);
    }

    // ================================================ equals conditdion ================================================

    /**
     * append equals condition
     *
     * @param value the column value
     * @return equals condition
     */
    public QueryCondition equalsTo(Object value) {
        return equalsIfCondition(value, true);
    }

    /**
     * append equals condition if the column value is not null
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition equalsIfNotNull(Object value) {
        return equalsIfCondition(value, If::notNull);
    }

    /**
     * append equals condition if the column value is not zero value, the zero value is {@code null} or {@code false}
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotZeroValue(Boolean)
     */
    public QueryCondition equalsIfNotZeroValue(Boolean value) {
        return equalsIfCondition(value, If::notZeroValue);
    }

    /**
     * append equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public QueryCondition equalsIfNotZeroValue(Number value) {
        return equalsIfCondition(value, If::notZeroValue);
    }

    /**
     * append equals condition if the column value is not zero value, the zero value is {@code null} or {@code ""}
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotZeroValue(CharSequence)
     */
    public QueryCondition equalsIfNotZeroValue(CharSequence value) {
        return equalsIfCondition(value, If::notZeroValue);
    }

    /**
     * append equals condition if the column value is not empty string
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotEmpty(CharSequence)
     */
    public QueryCondition equalsIfNotEmpty(CharSequence value) {
        return equalsIfCondition(value, If::notEmpty);
    }

    /**
     * append equals condition if the column value is not blank string
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition equalsIfNotBlank(CharSequence value) {
        return equalsIfCondition(value, If::notBlank);
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return equals condition
     */
    public QueryCondition equalsIfCondition(Object value, BooleanSupplier appendCondition) {
        return equalsIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return equals condition
     */
    public <T> QueryCondition equalsIfCondition(T value, Predicate<T> appendCondition) {
        return equalsIfCondition(value, appendCondition.test(value));
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return equals condition
     */
    public QueryCondition equalsIfCondition(Object value, boolean appendCondition) {
        return super.eq(value, appendCondition);
    }

    // ================================================ not equals conditdion ================================================

    /**
     * append not equals condition
     *
     * @param value the column value
     * @return not equals condition
     */
    public QueryCondition notEquals(Object value) {
        return notEqualsIfCondition(value, true);
    }

    /**
     * append not equals condition if the column value is not null
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition notEqualsIfNotNull(Object value) {
        return notEqualsIfCondition(value, If::notNull);
    }

    /**
     * append not equals condition if the column value is not zero value, the zero value is {@code null} or {@code false}
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotZeroValue(Boolean)
     */
    public QueryCondition notEqualsIfNotZeroValue(Boolean value) {
        return notEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append not equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public QueryCondition notEqualsIfNotZeroValue(Number value) {
        return notEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append not equals condition if the column value is not zero value, the zero value is {@code null} or {@code ""}
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotZeroValue(CharSequence)
     */
    public QueryCondition notEqualsIfNotZeroValue(CharSequence value) {
        return notEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append not equals condition if the column value is not empty string
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotEmpty(CharSequence)
     */
    public QueryCondition notEqualsIfNotEmpty(CharSequence value) {
        return notEqualsIfCondition(value, If::notEmpty);
    }

    /**
     * append not equals condition if the column value is not blank string
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notEqualsIfNotBlank(CharSequence value) {
        return notEqualsIfCondition(value, If::notBlank);
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not equals condition
     */
    public QueryCondition notEqualsIfCondition(Object value, BooleanSupplier appendCondition) {
        return notEqualsIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not equals condition
     */
    public <T> QueryCondition notEqualsIfCondition(T value, Predicate<T> appendCondition) {
        return notEqualsIfCondition(value, appendCondition.test(value));
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not equals condition
     */
    public QueryCondition notEqualsIfCondition(Object value, boolean appendCondition) {
        return super.ne(value, appendCondition);
    }

    // ================================================ greater than conditdion ================================================

    /**
     * append greater than conditdion
     *
     * @param value the column value
     * @return greater than conditdion
     */
    public QueryCondition greaterThan(Object value) {
        return greaterThanIfCondition(value, true);
    }

    /**
     * append greater than conditdion if the column value is not null
     *
     * @param value the column value
     * @return greater than conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition greaterThanIfNotNull(Object value) {
        return greaterThanIfCondition(value, If::notNull);
    }

    /**
     * append greater than conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return greater than conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public QueryCondition greaterThanIfNotZeroValue(Number value) {
        return greaterThanIfCondition(value, If::notZeroValue);
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return greater than conditdion
     */
    public QueryCondition greaterThanIfCondition(Object value, BooleanSupplier appendCondition) {
        return greaterThanIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return greater than conditdion
     */
    public <T> QueryCondition greaterThanIfCondition(T value, Predicate<T> appendCondition) {
        return greaterThanIfCondition(value, appendCondition.test(value));
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return greater than conditdion
     */
    public QueryCondition greaterThanIfCondition(Object value, boolean appendCondition) {
        return super.gt(value, appendCondition);
    }

    // ================================================ greater than or equals conditdion ================================================

    /**
     * append greater than or equals conditdion
     *
     * @param value the column value
     * @return greater than or equals conditdion
     */
    public QueryCondition greaterThanOrEquals(Object value) {
        return greaterThanOrEqualsIfCondition(value, true);
    }

    /**
     * append greater than or equals conditdion if the column value is not null
     *
     * @param value the column value
     * @return greater than or equals conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition greaterThanOrEqualsIfNotNull(Object value) {
        return greaterThanOrEqualsIfCondition(value, If::notNull);
    }

    /**
     * append greater than or equals conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return greater than or equals conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public QueryCondition greaterThanOrEqualsIfNotZeroValue(Number value) {
        return greaterThanOrEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return greater than or equals conditdion
     */
    public QueryCondition greaterThanOrEqualsIfCondition(Object value, BooleanSupplier appendCondition) {
        return greaterThanOrEqualsIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return greater than or equals conditdion
     */
    public <T> QueryCondition greaterThanOrEqualsIfCondition(T value, Predicate<T> appendCondition) {
        return greaterThanOrEqualsIfCondition(value, appendCondition.test(value));
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return greater than or equals conditdion
     */
    public QueryCondition greaterThanOrEqualsIfCondition(Object value, boolean appendCondition) {
        return super.ge(value, appendCondition);
    }

    // ================================================ less than conditdion ================================================

    /**
     * append less than conditdion
     *
     * @param value the column value
     * @return less than conditdion
     */
    public QueryCondition lessThan(Object value) {
        return lessThanIfCondition(value, true);
    }

    /**
     * append less than conditdion if the column value is not null
     *
     * @param value the column value
     * @return less than conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition lessThanIfNotNull(Object value) {
        return lessThanIfCondition(value, If::notNull);
    }

    /**
     * append less than conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return less than conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public QueryCondition lessThanIfNotZeroValue(Number value) {
        return lessThanIfCondition(value, If::notZeroValue);
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return less than conditdion
     */
    public QueryCondition lessThanIfCondition(Object value, BooleanSupplier appendCondition) {
        return lessThanIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return less than conditdion
     */
    public <T> QueryCondition lessThanIfCondition(T value, Predicate<T> appendCondition) {
        return lessThanIfCondition(value, appendCondition.test(value));
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return less than conditdion
     */
    public QueryCondition lessThanIfCondition(Object value, boolean appendCondition) {
        return super.lt(value, appendCondition);
    }

    // ================================================ less than or equals conditdion ================================================

    /**
     * append less than or equals conditdion
     *
     * @param value the column value
     * @return less than or equals conditdion
     */
    public QueryCondition lessThanOrEquals(Object value) {
        return lessThanOrEqualsIfCondition(value, true);
    }

    /**
     * append less than or equals conditdion if the column value is not null
     *
     * @param value the column value
     * @return less than or equals conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition lessThanOrEqualsIfNotNull(Object value) {
        return lessThanOrEqualsIfCondition(value, If::notNull);
    }

    /**
     * append less than or equals conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return less than or equals conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public QueryCondition lessThanOrEqualsIfNotZeroValue(Number value) {
        return lessThanOrEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return less than or equals conditdion
     */
    public QueryCondition lessThanOrEqualsIfCondition(Object value, BooleanSupplier appendCondition) {
        return lessThanOrEqualsIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return less than or equals conditdion
     */
    public <T> QueryCondition lessThanOrEqualsIfCondition(T value, Predicate<T> appendCondition) {
        return lessThanOrEqualsIfCondition(value, appendCondition.test(value));
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return less than or equals conditdion
     */
    public QueryCondition lessThanOrEqualsIfCondition(Object value, boolean appendCondition) {
        return super.le(value, appendCondition);
    }

    // ================================================ in conditdion ================================================

    /**
     * append in conditdion
     *
     * @param values the column values
     * @return in conditdion
     */
    public QueryCondition in(Iterable<?> values) {
        return inIfCondition(values, true);
    }

    /**
     * append in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return in conditdion
     * @see Nil#isNotEmpty(Object...)
     */
    public QueryCondition inIfNotEmpty(Object... values) {
        return inIfCondition(values, If::notEmpty);
    }

    /**
     * append in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return in conditdion
     * @see Nil#isNotEmpty(Iterable)
     */
    public QueryCondition inIfNotEmpty(Iterable<?> values) {
        return inIfCondition(values, If::notEmpty);
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Object[] values, BooleanSupplier appendCondition) {
        return inIfCondition(values, appendCondition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public <T> QueryCondition inIfCondition(T[] values, Predicate<T[]> appendCondition) {
        return inIfCondition(values, appendCondition.test(values));
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Object[] values, boolean appendCondition) {
        return super.in(values, appendCondition);
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Iterable<?> values, BooleanSupplier appendCondition) {
        return inIfCondition(values, appendCondition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public <T> QueryCondition inIfCondition(Iterable<T> values, Predicate<Iterable<T>> appendCondition) {
        return inIfCondition(values, appendCondition.test(values));
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Iterable<?> values, boolean appendCondition) {
        if (values instanceof Collection<?> collectionTypeValues) {
            return super.in(collectionTypeValues, appendCondition);
        }
        return super.in(Converts.toList(values), appendCondition);
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(QueryWrapper queryWrapper, BooleanSupplier appendCondition) {
        return inIfCondition(queryWrapper, appendCondition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(QueryWrapper queryWrapper, boolean appendCondition) {
        return super.in(queryWrapper, appendCondition);
    }

    // ================================================ not in conditdion ================================================

    /**
     * append not in conditdion
     *
     * @param values the column values
     * @return not in conditdion
     */
    public QueryCondition notIn(Iterable<?> values) {
        return notInIfCondition(values, true);
    }

    /**
     * append not in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return not in conditdion
     * @see Nil#isNotEmpty(Object...)
     */
    public QueryCondition notInIfNotEmpty(Object... values) {
        return notInIfCondition(values, If::notEmpty);
    }

    /**
     * append not in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return not in conditdion
     * @see Nil#isNotEmpty(Iterable)
     */
    public QueryCondition notInIfNotEmpty(Iterable<?> values) {
        return notInIfCondition(values, If::notEmpty);
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Object[] values, BooleanSupplier appendCondition) {
        return notInIfCondition(values, appendCondition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public <T> QueryCondition notInIfCondition(T[] values, Predicate<T[]> appendCondition) {
        return notInIfCondition(values, appendCondition.test(values));
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Object[] values, boolean appendCondition) {
        return super.notIn(values, appendCondition);
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Iterable<?> values, BooleanSupplier appendCondition) {
        return notInIfCondition(values, appendCondition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public <T> QueryCondition notInIfCondition(Iterable<T> values, Predicate<Iterable<T>> appendCondition) {
        return notInIfCondition(values, appendCondition.test(values));
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Iterable<?> values, boolean appendCondition) {
        if (values instanceof Collection<?> collectionTypeValues) {
            return super.notIn(collectionTypeValues, appendCondition);
        }
        return super.notIn(Converts.toList(values), appendCondition);
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(QueryWrapper queryWrapper, BooleanSupplier appendCondition) {
        return notInIfCondition(queryWrapper, appendCondition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values          the column values
     * @param appendCondition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(QueryWrapper queryWrapper, boolean appendCondition) {
        return super.notIn(queryWrapper, appendCondition);
    }

    // ================================================ between conditdion ================================================

    /**
     * append between conditdion if start column value and end column value are not null
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @return between conditdion
     * @see Nil#isAllNotNull(Object...)
     */
    public QueryCondition betweenIfAllNotNull(Object startValue, Object endValue) {
        return betweenIfCondition(startValue, endValue, Nil.isAllNotNull(startValue, endValue));
    }

    /**
     * append between conditdion if the append condition return true
     *
     * @param startValue      the between start column value
     * @param endValue        the between end column value
     * @param appendCondition the append condition
     * @return between conditdion
     */
    public QueryCondition betweenIfCondition(Object startValue, Object endValue, BooleanSupplier appendCondition) {
        return betweenIfCondition(startValue, endValue, appendCondition.getAsBoolean());
    }

    /**
     * append between conditdion if the append condition return true
     *
     * @param startValue      the between start column value
     * @param endValue        the between end column value
     * @param appendCondition the append condition
     * @return between conditdion
     */
    public <S, E> QueryCondition betweenIfCondition(S startValue, E endValue, BiPredicate<S, E> appendCondition) {
        return super.between(startValue, endValue, appendCondition);
    }

    /**
     * append between conditdion if the append condition return true
     *
     * @param startValue      the between start column value
     * @param endValue        the between end column value
     * @param appendCondition the append condition
     * @return between conditdion
     */
    public QueryCondition betweenIfCondition(Object startValue, Object endValue, boolean appendCondition) {
        return super.between(startValue, endValue, appendCondition);
    }

    // ================================================ not between conditdion ================================================

    /**
     * append not between conditdion if start column value and end column value are not null
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @return not between conditdion
     * @see Nil#isAllNotNull(Object...)
     */
    public QueryCondition notBetweenIfAllNotNull(Object startValue, Object endValue) {
        return notBetweenIfCondition(startValue, endValue, Nil.isAllNotNull(startValue, endValue));
    }

    /**
     * append not between conditdion if the append condition return true
     *
     * @param startValue      the between start column value
     * @param endValue        the between end column value
     * @param appendCondition the append condition
     * @return not between conditdion
     */
    public QueryCondition notBetweenIfCondition(Object startValue, Object endValue, BooleanSupplier appendCondition) {
        return notBetweenIfCondition(startValue, endValue, appendCondition.getAsBoolean());
    }

    /**
     * append not between conditdion if the append condition return true
     *
     * @param startValue      the between start column value
     * @param endValue        the between end column value
     * @param appendCondition the append condition
     * @return not between conditdion
     */
    public <S, E> QueryCondition notBetweenIfCondition(S startValue, E endValue, BiPredicate<S, E> appendCondition) {
        return super.notBetween(startValue, endValue, appendCondition);
    }

    /**
     * append not between conditdion if the append condition return true
     *
     * @param startValue      the between start column value
     * @param endValue        the between end column value
     * @param appendCondition the append condition
     * @return not between conditdion
     */
    public QueryCondition notBetweenIfCondition(Object startValue, Object endValue, boolean appendCondition) {
        return super.notBetween(startValue, endValue, appendCondition);
    }

    // ================================================ like "%value%" conditdion ================================================

    /**
     * append like {@code "%value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "%value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition likeIfNotNull(Object value) {
        return likeIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "%value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "%value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition likeIfNotBlank(CharSequence value) {
        return likeIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public QueryCondition likeIfCondition(Object value, BooleanSupplier appendCondition) {
        return likeIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public <T> QueryCondition likeIfCondition(T value, Predicate<T> appendCondition) {
        return likeIfCondition(value, appendCondition.test(value));
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public QueryCondition likeIfCondition(Object value, boolean appendCondition) {
        return super.like(value, appendCondition);
    }

    // ================================================ like "value%" conditdion ================================================

    /**
     * append like {@code "value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition likeLeftIfNotNull(Object value) {
        return likeLeftIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition likeLeftIfNotBlank(CharSequence value) {
        return likeLeftIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "value%"} conditdion
     */
    public QueryCondition likeLeftIfCondition(Object value, BooleanSupplier appendCondition) {
        return likeLeftIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "value%"} conditdion
     */
    public <T> QueryCondition likeLeftIfCondition(T value, Predicate<T> appendCondition) {
        return likeLeftIfCondition(value, appendCondition.test(value));
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "value%"} conditdion
     */
    public QueryCondition likeLeftIfCondition(Object value, boolean appendCondition) {
        return super.likeLeft(value, appendCondition);
    }

    // ================================================ like "%value" conditdion ================================================

    /**
     * append like {@code "%value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "%value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition likeRightIfNotNull(Object value) {
        return likeRightIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "%value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "%value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition likeRightIfNotBlank(CharSequence value) {
        return likeRightIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "%value"} conditdion
     */
    public QueryCondition likeRightIfCondition(Object value, BooleanSupplier appendCondition) {
        return likeRightIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "%value"} conditdion
     */
    public <T> QueryCondition likeRightIfCondition(T value, Predicate<T> appendCondition) {
        return likeRightIfCondition(value, appendCondition.test(value));
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "%value"} conditdion
     */
    public QueryCondition likeRightIfCondition(Object value, boolean appendCondition) {
        return super.likeRight(value, appendCondition);
    }

    // ================================================ like "value" conditdion ================================================

    /**
     * append like {@code "value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition likeRawIfNotNull(Object value) {
        return likeRawIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition likeRawIfNotBlank(CharSequence value) {
        return likeRawIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "value"} conditdion
     */
    public QueryCondition likeRawIfCondition(Object value, BooleanSupplier appendCondition) {
        return likeRawIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "value"} conditdion
     */
    public <T> QueryCondition likeRawIfCondition(T value, Predicate<T> appendCondition) {
        return likeRawIfCondition(value, appendCondition.test(value));
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return like {@code "value"} conditdion
     */
    public QueryCondition likeRawIfCondition(Object value, boolean appendCondition) {
        return super.likeRaw(value, appendCondition);
    }

    // ================================================ not like "%value%" conditdion ================================================

    /**
     * append not like {@code "%value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "%value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition notLikeIfNotNull(Object value) {
        return notLikeIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "%value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeIfNotBlank(CharSequence value) {
        return notLikeIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public QueryCondition notLikeIfCondition(Object value, BooleanSupplier appendCondition) {
        return notLikeIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public <T> QueryCondition notLikeIfCondition(T value, Predicate<T> appendCondition) {
        return notLikeIfCondition(value, appendCondition.test(value));
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public QueryCondition notLikeIfCondition(Object value, boolean appendCondition) {
        return super.notLike(value, appendCondition);
    }

    // ================================================ not like "value%" conditdion ================================================

    /**
     * append not like {@code "value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition notLikeLeftIfNotNull(Object value) {
        return notLikeLeftIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeLeftIfNotBlank(CharSequence value) {
        return notLikeLeftIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public QueryCondition notLikeLeftIfCondition(Object value, BooleanSupplier appendCondition) {
        return notLikeLeftIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public <T> QueryCondition notLikeLeftIfCondition(T value, Predicate<T> appendCondition) {
        return notLikeLeftIfCondition(value, appendCondition.test(value));
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public QueryCondition notLikeLeftIfCondition(Object value, boolean appendCondition) {
        return super.notLikeLeft(value, appendCondition);
    }

    // ================================================ not like "%value" conditdion ================================================

    /**
     * append not like {@code "%value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "%value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition notLikeRightIfNotNull(Object value) {
        return notLikeRightIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "%value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeRightIfNotBlank(CharSequence value) {
        return notLikeRightIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public QueryCondition notLikeRightIfCondition(Object value, BooleanSupplier appendCondition) {
        return notLikeRightIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public <T> QueryCondition notLikeRightIfCondition(T value, Predicate<T> appendCondition) {
        return notLikeRightIfCondition(value, appendCondition.test(value));
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public QueryCondition notLikeRightIfCondition(Object value, boolean appendCondition) {
        return super.notLikeRight(value, appendCondition);
    }

    // ================================================ not like "value" conditdion ================================================

    /**
     * append not like {@code "value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition notLikeRawIfNotNull(Object value) {
        return notLikeRawIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeRawIfNotBlank(CharSequence value) {
        return notLikeRawIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "value"} conditdion
     */
    public QueryCondition notLikeRawIfCondition(Object value, BooleanSupplier appendCondition) {
        return notLikeRawIfCondition(value, appendCondition.getAsBoolean());
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "value"} conditdion
     */
    public <T> QueryCondition notLikeRawIfCondition(T value, Predicate<T> appendCondition) {
        return notLikeRawIfCondition(value, appendCondition.test(value));
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value           the column value
     * @param appendCondition the append condition
     * @return not like {@code "value"} conditdion
     */
    public QueryCondition notLikeRawIfCondition(Object value, boolean appendCondition) {
        return super.notLikeRaw(value, appendCondition);
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked most mybatis-flex base mapper funcations as deprecated, since mybatis-flex version 1.7.3, it is not recommended to use as following:

    @Deprecated
    @Override
    public QueryCondition eq(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition eq(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition eq(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition eq(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition ne(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition ne(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition ne(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition ne(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition gt(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition gt(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition gt(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition gt(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition ge(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition ge(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition ge(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition ge(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition lt(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition lt(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition lt(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition lt(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition le(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition le(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition le(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition le(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition in(Object[] value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition in(Object[] value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition in(T[] value, Predicate<T[]> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition in(Collection<?> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition in(Collection<?> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T extends Collection<?>> QueryCondition in(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition in(QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition in(QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notIn(Object[] value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notIn(Object[] value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition notIn(T[] value, Predicate<T[]> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notIn(Collection<?> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notIn(Collection<?> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T extends Collection<?>> QueryCondition notIn(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notIn(QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notIn(QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition between(Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition between(Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition between(Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <S, E> QueryCondition between(S start, E end, BiPredicate<S, E> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notBetween(Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notBetween(Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notBetween(Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <S, E> QueryCondition notBetween(S start, E end, BiPredicate<S, E> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition like(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition like(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition like(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition likeLeft(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition likeLeft(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition likeLeft(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition likeRight(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition likeRight(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition likeRight(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition likeRaw(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition likeRaw(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition likeRaw(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLike(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLike(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition notLike(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLikeLeft(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLikeLeft(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition notLikeLeft(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLikeRight(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLikeRight(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition notLikeRight(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLikeRaw(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public QueryCondition notLikeRaw(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> QueryCondition notLikeRaw(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

}
