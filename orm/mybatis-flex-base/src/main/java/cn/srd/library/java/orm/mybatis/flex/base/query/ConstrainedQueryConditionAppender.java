// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.functional.If;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.LambdaGetter;

import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * @param <W> the wrapper extends {@link QueryWrapper}
 * @author wjm
 * @since 2023-12-03 23:35
 */
@SuppressWarnings(SuppressWarningConstant.ALL)
public class ConstrainedQueryConditionAppender<W extends QueryWrapper> extends QueryConditionBuilder<W> {

    private static final String ADD_WHERE_QUERY_CONDITION_METHOD_NAME = "addWhereQueryCondition";

    private final W queryWrapper;

    private final QueryColumn queryColumn;

    private final SqlConnector connector;

    public ConstrainedQueryConditionAppender(W queryWrapper, QueryColumn queryColumn, SqlConnector connector) {
        super(queryWrapper, queryColumn, connector);
        this.queryWrapper = queryWrapper;
        this.queryColumn = queryColumn;
        this.connector = connector;
    }

    // ================================================ equals conditdion ================================================

    /**
     * append equals condition
     *
     * @param value the column value
     * @return equals condition
     */
    public W equalsTo(Object value) {
        return equalsIfCondition(value, true);
    }

    /**
     * append equals condition if the column value is not null
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotNull(Object)
     */
    public W equalsIfNotNull(Object value) {
        return equalsIfCondition(value, If::notNull);
    }

    /**
     * append equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public W equalsIfNotZeroValue(Number value) {
        return equalsIfCondition(value, If::notZeroValue);
    }

    /**
     * append equals condition if the column value is not empty string
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotEmpty(CharSequence)
     */
    public W equalsIfNotEmpty(CharSequence value) {
        return equalsIfCondition(value, If::notEmpty);
    }

    /**
     * append equals condition if the column value is not blank string
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public W equalsIfNotBlank(CharSequence value) {
        return equalsIfCondition(value, If::notBlank);
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public W equalsIfCondition(Object value, BooleanSupplier condition) {
        return equalsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public <T> W equalsIfCondition(T value, Predicate<T> condition) {
        return equalsIfCondition(value, condition.test(value));
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public W equalsIfCondition(Object value, boolean condition) {
        return super.eq(value, condition);
    }

    // ================================================ not equals conditdion ================================================

    /**
     * append not equals condition
     *
     * @param value the column value
     * @return not equals condition
     */
    public W notEquals(Object value) {
        return notEqualsIfCondition(value, true);
    }

    /**
     * append not equals condition if the column value is not null
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotNull(Object)
     */
    public W notEqualsIfNotNull(Object value) {
        return notEqualsIfCondition(value, If::notNull);
    }

    /**
     * append not equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public W notEqualsIfNotZeroValue(Number value) {
        return notEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append not equals condition if the column value is not empty string
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotEmpty(CharSequence)
     */
    public W notEqualsIfNotEmpty(CharSequence value) {
        return notEqualsIfCondition(value, If::notEmpty);
    }

    /**
     * append not equals condition if the column value is not blank string
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public W notEqualsIfNotBlank(CharSequence value) {
        return notEqualsIfCondition(value, If::notBlank);
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public W notEqualsIfCondition(Object value, BooleanSupplier condition) {
        return notEqualsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public <T> W notEqualsIfCondition(T value, Predicate<T> condition) {
        return notEqualsIfCondition(value, condition.test(value));
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public W notEqualsIfCondition(Object value, boolean condition) {
        return super.ne(value, condition);
    }

    // ================================================ greater than conditdion ================================================

    /**
     * append greater than conditdion
     *
     * @param value the column value
     * @return greater than conditdion
     */
    public W greaterThan(Object value) {
        return greaterThanIfCondition(value, true);
    }

    /**
     * append greater than conditdion if the column value is not null
     *
     * @param value the column value
     * @return greater than conditdion
     * @see Nil#isNotNull(Object)
     */
    public W greaterThanIfNotNull(Object value) {
        return greaterThanIfCondition(value, If::notNull);
    }

    /**
     * append greater than conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return greater than conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public W greaterThanIfNotZeroValue(Number value) {
        return greaterThanIfCondition(value, If::notZeroValue);
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than conditdion
     */
    public W greaterThanIfCondition(Object value, BooleanSupplier condition) {
        return greaterThanIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than conditdion
     */
    public <T> W greaterThanIfCondition(T value, Predicate<T> condition) {
        return greaterThanIfCondition(value, condition.test(value));
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than conditdion
     */
    public W greaterThanIfCondition(Object value, boolean condition) {
        return super.gt(value, condition);
    }

    // ================================================ greater than or equals conditdion ================================================

    /**
     * append greater than or equals conditdion
     *
     * @param value the column value
     * @return greater than or equals conditdion
     */
    public W greaterThanOrEquals(Object value) {
        return greaterThanOrEqualsIfCondition(value, true);
    }

    /**
     * append greater than or equals conditdion if the column value is not null
     *
     * @param value the column value
     * @return greater than or equals conditdion
     * @see Nil#isNotNull(Object)
     */
    public W greaterThanOrEqualsIfNotNull(Object value) {
        return greaterThanOrEqualsIfCondition(value, If::notNull);
    }

    /**
     * append greater than or equals conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return greater than or equals conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public W greaterThanOrEqualsIfNotZeroValue(Number value) {
        return greaterThanOrEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals conditdion
     */
    public W greaterThanOrEqualsIfCondition(Object value, BooleanSupplier condition) {
        return greaterThanOrEqualsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals conditdion
     */
    public <T> W greaterThanOrEqualsIfCondition(T value, Predicate<T> condition) {
        return greaterThanOrEqualsIfCondition(value, condition.test(value));
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals conditdion
     */
    public W greaterThanOrEqualsIfCondition(Object value, boolean condition) {
        return super.ge(value, condition);
    }

    // ================================================ less than conditdion ================================================

    /**
     * append less than conditdion
     *
     * @param value the column value
     * @return less than conditdion
     */
    public W lessThan(Object value) {
        return lessThanIfCondition(value, true);
    }

    /**
     * append less than conditdion if the column value is not null
     *
     * @param value the column value
     * @return less than conditdion
     * @see Nil#isNotNull(Object)
     */
    public W lessThanIfNotNull(Object value) {
        return lessThanIfCondition(value, If::notNull);
    }

    /**
     * append less than conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return less than conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public W lessThanIfNotZeroValue(Number value) {
        return lessThanIfCondition(value, If::notZeroValue);
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than conditdion
     */
    public W lessThanIfCondition(Object value, BooleanSupplier condition) {
        return lessThanIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than conditdion
     */
    public <T> W lessThanIfCondition(T value, Predicate<T> condition) {
        return lessThanIfCondition(value, condition.test(value));
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than conditdion
     */
    public W lessThanIfCondition(Object value, boolean condition) {
        return super.lt(value, condition);
    }

    // ================================================ less than or equals conditdion ================================================

    /**
     * append less than or equals conditdion
     *
     * @param value the column value
     * @return less than or equals conditdion
     */
    public W lessThanOrEquals(Object value) {
        return lessThanOrEqualsIfCondition(value, true);
    }

    /**
     * append less than or equals conditdion if the column value is not null
     *
     * @param value the column value
     * @return less than or equals conditdion
     * @see Nil#isNotNull(Object)
     */
    public W lessThanOrEqualsIfNotNull(Object value) {
        return lessThanOrEqualsIfCondition(value, If::notNull);
    }

    /**
     * append less than or equals conditdion if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return less than or equals conditdion
     * @see Nil#isNotZeroValue(Number)
     */
    public W lessThanOrEqualsIfNotZeroValue(Number value) {
        return lessThanOrEqualsIfCondition(value, If::notZeroValue);
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals conditdion
     */
    public W lessThanOrEqualsIfCondition(Object value, BooleanSupplier condition) {
        return lessThanOrEqualsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals conditdion
     */
    public <T> W lessThanOrEqualsIfCondition(T value, Predicate<T> condition) {
        return lessThanOrEqualsIfCondition(value, condition.test(value));
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals conditdion
     */
    public W lessThanOrEqualsIfCondition(Object value, boolean condition) {
        return super.le(value, condition);
    }

    // ================================================ in conditdion ================================================

    /**
     * append in conditdion
     *
     * @param values the column values
     * @return in conditdion
     */
    public W in(Iterable<?> values) {
        return inIfCondition(values, true);
    }

    /**
     * append in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return in conditdion
     * @see Nil#isNotEmpty(Object...)
     */
    public W inIfNotEmpty(Object... values) {
        return inIfCondition(values, If::notEmpty);
    }

    /**
     * append in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return in conditdion
     * @see Nil#isNotEmpty(Iterable)
     */
    public W inIfNotEmpty(Iterable<?> values) {
        return inIfCondition(values, If::notEmpty);
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public W inIfCondition(Object[] values, BooleanSupplier condition) {
        return inIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public <T> W inIfCondition(T[] values, Predicate<T[]> condition) {
        return inIfCondition(values, condition.test(values));
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public W inIfCondition(Object[] values, boolean condition) {
        return super.in(values, condition);
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public W inIfCondition(Iterable<?> values, BooleanSupplier condition) {
        return inIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public <T> W inIfCondition(Iterable<T> values, Predicate<Iterable<T>> condition) {
        return inIfCondition(values, condition.test(values));
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public W inIfCondition(Iterable<?> values, boolean condition) {
        if (values instanceof Collection<?> collectionTypeValues) {
            return super.in(collectionTypeValues, condition);
        }
        return super.in(Converts.toList(values), condition);
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public W inIfCondition(QueryWrapper queryWrapper, BooleanSupplier condition) {
        return inIfCondition(queryWrapper, condition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public W inIfCondition(QueryWrapper queryWrapper, boolean condition) {
        return super.in(queryWrapper, condition);
    }

    // ================================================ not in conditdion ================================================

    /**
     * append not in conditdion
     *
     * @param values the column values
     * @return not in conditdion
     */
    public W notIn(Iterable<?> values) {
        return notInIfCondition(values, true);
    }

    /**
     * append not in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return not in conditdion
     * @see Nil#isNotEmpty(Object...)
     */
    public W notInIfNotEmpty(Object... values) {
        return notInIfCondition(values, If::notEmpty);
    }

    /**
     * append not in conditdion if the column values is not null and at least one size
     *
     * @param values the column values
     * @return not in conditdion
     * @see Nil#isNotEmpty(Iterable)
     */
    public W notInIfNotEmpty(Iterable<?> values) {
        return notInIfCondition(values, If::notEmpty);
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public W notInIfCondition(Object[] values, BooleanSupplier condition) {
        return notInIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public <T> W notInIfCondition(T[] values, Predicate<T[]> condition) {
        return notInIfCondition(values, condition.test(values));
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public W notInIfCondition(Object[] values, boolean condition) {
        return super.notIn(values, condition);
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public W notInIfCondition(Iterable<?> values, BooleanSupplier condition) {
        return notInIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public <T> W notInIfCondition(Iterable<T> values, Predicate<Iterable<T>> condition) {
        return notInIfCondition(values, condition.test(values));
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public W notInIfCondition(Iterable<?> values, boolean condition) {
        if (values instanceof Collection<?> collectionTypeValues) {
            return super.notIn(collectionTypeValues, condition);
        }
        return super.notIn(Converts.toList(values), condition);
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public W notInIfCondition(QueryWrapper queryWrapper, BooleanSupplier condition) {
        return notInIfCondition(queryWrapper, condition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public W notInIfCondition(QueryWrapper queryWrapper, boolean condition) {
        return super.notIn(queryWrapper, condition);
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
    public W betweenIfAllNotNull(Object startValue, Object endValue) {
        return betweenIfCondition(startValue, endValue, Nil.isAllNotNull(startValue, endValue));
    }

    /**
     * append between conditdion if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return between conditdion
     */
    public W betweenIfCondition(Object startValue, Object endValue, BooleanSupplier condition) {
        return betweenIfCondition(startValue, endValue, condition.getAsBoolean());
    }

    /**
     * append between conditdion if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return between conditdion
     */
    public <S, E> W betweenIfCondition(S startValue, E endValue, BiPredicate<S, E> condition) {
        return super.between(startValue, endValue, condition);
    }

    /**
     * append between conditdion if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return between conditdion
     */
    public W betweenIfCondition(Object startValue, Object endValue, boolean condition) {
        return super.between(startValue, endValue, condition);
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
    public W notBetweenIfAllNotNull(Object startValue, Object endValue) {
        return notBetweenIfCondition(startValue, endValue, Nil.isAllNotNull(startValue, endValue));
    }

    /**
     * append not between conditdion if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return not between conditdion
     */
    public W notBetweenIfCondition(Object startValue, Object endValue, BooleanSupplier condition) {
        return notBetweenIfCondition(startValue, endValue, condition.getAsBoolean());
    }

    /**
     * append not between conditdion if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return not between conditdion
     */
    public <S, E> W notBetweenIfCondition(S startValue, E endValue, BiPredicate<S, E> condition) {
        return super.notBetween(startValue, endValue, condition);
    }

    /**
     * append not between conditdion if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return not between conditdion
     */
    public W notBetweenIfCondition(Object startValue, Object endValue, boolean condition) {
        return super.notBetween(startValue, endValue, condition);
    }

    // ================================================ like "%value%" conditdion ================================================

    /**
     * append like {@code "%value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "%value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W likeIfNotNull(Object value) {
        return likeIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "%value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "%value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W likeIfNotBlank(CharSequence value) {
        return likeIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public W likeIfCondition(Object value, BooleanSupplier condition) {
        return likeIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public <T> W likeIfCondition(T value, Predicate<T> condition) {
        return likeIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public W likeIfCondition(Object value, boolean condition) {
        return super.like(value, condition);
    }

    // ================================================ like "value%" conditdion ================================================

    /**
     * append like {@code "value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W likeLeftIfNotNull(Object value) {
        return likeLeftIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W likeLeftIfNotBlank(CharSequence value) {
        return likeLeftIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} conditdion
     */
    public W likeLeftIfCondition(Object value, BooleanSupplier condition) {
        return likeLeftIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} conditdion
     */
    public <T> W likeLeftIfCondition(T value, Predicate<T> condition) {
        return likeLeftIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} conditdion
     */
    public W likeLeftIfCondition(Object value, boolean condition) {
        return super.likeLeft(value, condition);
    }

    // ================================================ like "%value" conditdion ================================================

    /**
     * append like {@code "%value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "%value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W likeRightIfNotNull(Object value) {
        return likeRightIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "%value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "%value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W likeRightIfNotBlank(CharSequence value) {
        return likeRightIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} conditdion
     */
    public W likeRightIfCondition(Object value, BooleanSupplier condition) {
        return likeRightIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} conditdion
     */
    public <T> W likeRightIfCondition(T value, Predicate<T> condition) {
        return likeRightIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} conditdion
     */
    public W likeRightIfCondition(Object value, boolean condition) {
        return super.likeRight(value, condition);
    }

    // ================================================ like "value" conditdion ================================================

    /**
     * append like {@code "value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return like {@code "value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W likeRawIfNotNull(Object value) {
        return likeRawIfCondition(value, If::notNull);
    }

    /**
     * append like {@code "value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W likeRawIfNotBlank(CharSequence value) {
        return likeRawIfCondition(value, If::notBlank);
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} conditdion
     */
    public W likeRawIfCondition(Object value, BooleanSupplier condition) {
        return likeRawIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} conditdion
     */
    public <T> W likeRawIfCondition(T value, Predicate<T> condition) {
        return likeRawIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} conditdion
     */
    public W likeRawIfCondition(Object value, boolean condition) {
        Reflects.invoke(this, ADD_WHERE_QUERY_CONDITION_METHOD_NAME, this.queryColumn.likeRaw(value, condition));
        return this.queryWrapper;
    }

    // ================================================ not like "%value%" conditdion ================================================

    /**
     * append not like {@code "%value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "%value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W notLikeIfNotNull(Object value) {
        return notLikeIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "%value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W notLikeIfNotBlank(CharSequence value) {
        return notLikeIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public W notLikeIfCondition(Object value, BooleanSupplier condition) {
        return notLikeIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public <T> W notLikeIfCondition(T value, Predicate<T> condition) {
        return notLikeIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public W notLikeIfCondition(Object value, boolean condition) {
        return super.notLike(value, condition);
    }

    // ================================================ not like "value%" conditdion ================================================

    /**
     * append not like {@code "value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W notLikeLeftIfNotNull(Object value) {
        return notLikeLeftIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W notLikeLeftIfNotBlank(CharSequence value) {
        return notLikeLeftIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public W notLikeLeftIfCondition(Object value, BooleanSupplier condition) {
        return notLikeLeftIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public <T> W notLikeLeftIfCondition(T value, Predicate<T> condition) {
        return notLikeLeftIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public W notLikeLeftIfCondition(Object value, boolean condition) {
        return super.notLikeLeft(value, condition);
    }

    // ================================================ not like "%value" conditdion ================================================

    /**
     * append not like {@code "%value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "%value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W notLikeRightIfNotNull(Object value) {
        return notLikeRightIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "%value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W notLikeRightIfNotBlank(CharSequence value) {
        return notLikeRightIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public W notLikeRightIfCondition(Object value, BooleanSupplier condition) {
        return notLikeRightIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public <T> W notLikeRightIfCondition(T value, Predicate<T> condition) {
        return notLikeRightIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public W notLikeRightIfCondition(Object value, boolean condition) {
        return super.notLikeRight(value, condition);
    }

    // ================================================ not like "value" conditdion ================================================

    /**
     * append not like {@code "value"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "value"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public W notLikeRawIfNotNull(Object value) {
        return notLikeRawIfCondition(value, If::notNull);
    }

    /**
     * append not like {@code "value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public W notLikeRawIfNotBlank(CharSequence value) {
        return notLikeRawIfCondition(value, If::notBlank);
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} conditdion
     */
    public W notLikeRawIfCondition(Object value, BooleanSupplier condition) {
        return notLikeRawIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} conditdion
     */
    public <T> W notLikeRawIfCondition(T value, Predicate<T> condition) {
        return notLikeRawIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} conditdion
     */
    public W notLikeRawIfCondition(Object value, boolean condition) {
        Reflects.invoke(this, ADD_WHERE_QUERY_CONDITION_METHOD_NAME, this.queryColumn.notLikeRaw(value, condition));
        return this.queryWrapper;
    }

    // ================================================ in null conditdion ================================================

    @Override
    public W isNull() {
        return isNullIfCondition(true);
    }

    /**
     * append is null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public W isNullIfCondition(BooleanSupplier condition) {
        return isNullIfCondition(condition.getAsBoolean());
    }

    /**
     * append is null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public W isNullIfCondition(boolean condition) {
        return super.isNull(condition);
    }

    // ================================================ in not null conditdion ================================================

    @Override
    public W isNotNull() {
        return isNotNullIfCondition(true);
    }

    /**
     * append is not null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public W isNotNullIfCondition(BooleanSupplier condition) {
        return isNotNullIfCondition(condition.getAsBoolean());
    }

    /**
     * append is not null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public W isNotNullIfCondition(boolean condition) {
        return super.isNotNull(condition);
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked most mybatis-flex original functions as deprecated, since mybatis-flex version 1.7.5, it is not recommended to use as following:

    @Deprecated
    @Override
    public W eq(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W eq(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W eq(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W eq(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W eq(LambdaGetter<T> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W eq(LambdaGetter<T> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W eq(LambdaGetter<T> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W ne(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W ne(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W ne(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ne(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ne(LambdaGetter<T> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ne(LambdaGetter<T> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ne(LambdaGetter<T> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W gt(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W gt(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W gt(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W gt(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W gt(LambdaGetter<T> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W gt(LambdaGetter<T> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W gt(LambdaGetter<T> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W ge(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W ge(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W ge(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ge(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ge(LambdaGetter<T> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ge(LambdaGetter<T> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W ge(LambdaGetter<T> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W lt(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W lt(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W lt(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W lt(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W lt(LambdaGetter<T> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W lt(LambdaGetter<T> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W lt(LambdaGetter<T> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W le(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W le(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W le(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W le(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W le(LambdaGetter<T> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W le(LambdaGetter<T> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W le(LambdaGetter<T> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(Object... value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(Object[] value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(Object[] value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W in(T[] value, Predicate<T[]> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(Collection<?> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(Collection<?> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(Collection<?> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T extends Collection<?>> W in(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W in(QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(Object... value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(Object[] value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(Object[] value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W notIn(T[] value, Predicate<T[]> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(Collection<?> value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(Collection<?> value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(Collection<?> value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T extends Collection<?>> W notIn(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(QueryWrapper queryWrapper, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notIn(QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W between(Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W between(Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W between(Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <S, E> W between(S start, E end, BiPredicate<S, E> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notBetween(Object start, Object end) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notBetween(Object start, Object end, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notBetween(Object start, Object end, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <S, E> W notBetween(S start, E end, BiPredicate<S, E> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W like(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W like(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W like(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W like(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W likeLeft(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W likeLeft(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W likeLeft(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W likeLeft(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W likeRight(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W likeRight(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W likeRight(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W likeRight(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLike(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLike(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLike(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W notLike(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLikeLeft(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLikeLeft(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLikeLeft(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W notLikeLeft(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLikeRight(Object value) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLikeRight(Object value, boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W notLikeRight(Object value, BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public <T> W notLikeRight(T value, Predicate<T> isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W isNull(boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W isNotNull(boolean isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W isNull(BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W isNotNull(BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

}
