// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.mybatis.flex.base.support.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.support.MybatisFlexs;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.functional.If;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * @param <C> the chainer extends {@link BaseChainer}
 * @param <W> the wrapper extends {@link QueryWrapper}
 * @author wjm
 * @since 2023-12-03 23:35
 */
@CanIgnoreReturnValue
@AllArgsConstructor
public class QueryConditional<C extends BaseChainer, W extends QueryWrapper> extends BaseQueryConditional<W> {

    @Getter(AccessLevel.PRIVATE)
    private final C chainer;

    @Getter(AccessLevel.PROTECTED)
    private final QueryConditionBuilder<W> nativeQueryConditional;

    private static final String ADD_WHERE_QUERY_CONDITION_METHOD_NAME = "addWhereQueryCondition";

    private static final String QUERY_COLUMN_FIELD_NAME = "queryColumn";

    private static final String LIKE_RAW_METHOD_NAME = "likeRaw";

    private static final String NOT_LIKE_RAW_METHOD_NAME = "notLikeRaw";

    // ================================================ equals condition ================================================

    /**
     * append equals condition
     *
     * @param value the column value
     * @return equals condition
     */
    public C equalsTo(Object value) {
        return equalsTo(value, true);
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public C equalsTo(Object value, BooleanSupplier condition) {
        return equalsTo(value, condition.getAsBoolean());
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public <U> C equalsTo(U value, Predicate<U> condition) {
        return equalsTo(value, condition.test(value));
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public C equalsTo(Object value, boolean condition) {
        getNativeQueryConditional().eq(value, condition);
        return getChainer();
    }

    /**
     * append equals condition
     *
     * @param columnNameGetter the column value getter
     * @return equals condition
     */
    public <P1 extends PO> C equalsTo(ColumnNameGetter<P1> columnNameGetter) {
        return equalsTo(columnNameGetter, true);
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return equals condition
     */
    public <P1 extends PO> C equalsTo(ColumnNameGetter<P1> columnNameGetter, BooleanSupplier condition) {
        return equalsTo(columnNameGetter, condition.getAsBoolean());
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return equals condition
     */
    public <P1 extends PO> C equalsTo(ColumnNameGetter<P1> columnNameGetter, boolean condition) {
        return equalsTo(MybatisFlexs.getQueryColumn(columnNameGetter), condition);
    }

    /**
     * append equals condition if the column value is not null
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotNull(Object)
     */
    public C equalsIfNotNull(Object value) {
        return equalsTo(value, If::notNull);
    }

    /**
     * append equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public C equalsIfNotZeroValue(Number value) {
        return equalsTo(value, If::notZeroValue);
    }

    /**
     * append equals condition if the column value is not empty string
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotEmpty(CharSequence)
     */
    public C equalsIfNotEmpty(CharSequence value) {
        return equalsTo(value, If::notEmpty);
    }

    /**
     * append equals condition if the column value is not blank string
     *
     * @param value the column value
     * @return equals condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C equalsIfNotBlank(CharSequence value) {
        return equalsTo(value, If::notBlank);
    }

    // ================================================ not equals condition ================================================

    /**
     * append not equals condition
     *
     * @param value the column value
     * @return not equals condition
     */
    public C notEquals(Object value) {
        return notEquals(value, true);
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public C notEquals(Object value, BooleanSupplier condition) {
        return notEquals(value, condition.getAsBoolean());
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public <U> C notEquals(U value, Predicate<U> condition) {
        return notEquals(value, condition.test(value));
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public C notEquals(Object value, boolean condition) {
        getNativeQueryConditional().ne(value, condition);
        return getChainer();
    }

    /**
     * append not equals condition
     *
     * @param columnNameGetter the column value getter
     * @return not equals condition
     */
    public <P1 extends PO> C notEquals(ColumnNameGetter<P1> columnNameGetter) {
        return notEquals(columnNameGetter, true);
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return not equals condition
     */
    public <P1 extends PO> C notEquals(ColumnNameGetter<P1> columnNameGetter, BooleanSupplier condition) {
        return notEquals(columnNameGetter, condition.getAsBoolean());
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return not equals condition
     */
    public <P1 extends PO> C notEquals(ColumnNameGetter<P1> columnNameGetter, boolean condition) {
        return notEquals(MybatisFlexs.getQueryColumn(columnNameGetter), condition);
    }

    /**
     * append not equals condition if the column value is not null
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotNull(Object)
     */
    public C notEqualsIfNotNull(Object value) {
        return notEquals(value, If::notNull);
    }

    /**
     * append not equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public C notEqualsIfNotZeroValue(Number value) {
        return notEquals(value, If::notZeroValue);
    }

    /**
     * append not equals condition if the column value is not empty string
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotEmpty(CharSequence)
     */
    public C notEqualsIfNotEmpty(CharSequence value) {
        return notEquals(value, If::notEmpty);
    }

    /**
     * append not equals condition if the column value is not blank string
     *
     * @param value the column value
     * @return not equals condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C notEqualsIfNotBlank(CharSequence value) {
        return notEquals(value, If::notBlank);
    }

    // ================================================ greater than condition ================================================

    /**
     * append greater than condition
     *
     * @param value the column value
     * @return greater than condition
     */
    public C greaterThan(Object value) {
        return greaterThan(value, true);
    }

    /**
     * append greater than condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than condition
     */
    public C greaterThan(Object value, BooleanSupplier condition) {
        return greaterThan(value, condition.getAsBoolean());
    }

    /**
     * append greater than condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than condition
     */
    public <U> C greaterThan(U value, Predicate<U> condition) {
        return greaterThan(value, condition.test(value));
    }

    /**
     * append greater than condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than condition
     */
    public C greaterThan(Object value, boolean condition) {
        getNativeQueryConditional().gt(value, condition);
        return getChainer();
    }

    /**
     * append greater than condition
     *
     * @param columnNameGetter the column value getter
     * @return greater than condition
     */
    public <P1 extends PO> C greaterThan(ColumnNameGetter<P1> columnNameGetter) {
        return greaterThan(columnNameGetter, true);
    }

    /**
     * append greater than condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return greater than condition
     */
    public <P1 extends PO> C greaterThan(ColumnNameGetter<P1> columnNameGetter, BooleanSupplier condition) {
        return greaterThan(columnNameGetter, condition.getAsBoolean());
    }

    /**
     * append greater than condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return greater than condition
     */
    public <P1 extends PO> C greaterThan(ColumnNameGetter<P1> columnNameGetter, boolean condition) {
        return greaterThan(MybatisFlexs.getQueryColumn(columnNameGetter), condition);
    }

    /**
     * append greater than condition if the column value is not null
     *
     * @param value the column value
     * @return greater than condition
     * @see Nil#isNotNull(Object)
     */
    public C greaterThanIfNotNull(Object value) {
        return greaterThan(value, If::notNull);
    }

    /**
     * append greater than condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return greater than condition
     * @see Nil#isNotZeroValue(Number)
     */
    public C greaterThanIfNotZeroValue(Number value) {
        return greaterThan(value, If::notZeroValue);
    }

    // ================================================ greater than or equals condition ================================================

    /**
     * append greater than or equals condition
     *
     * @param value the column value
     * @return greater than or equals condition
     */
    public C greaterThanOrEquals(Object value) {
        return greaterThanOrEquals(value, true);
    }

    /**
     * append greater than or equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals condition
     */
    public C greaterThanOrEquals(Object value, BooleanSupplier condition) {
        return greaterThanOrEquals(value, condition.getAsBoolean());
    }

    /**
     * append greater than or equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals condition
     */
    public <U> C greaterThanOrEquals(U value, Predicate<U> condition) {
        return greaterThanOrEquals(value, condition.test(value));
    }

    /**
     * append greater than or equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals condition
     */
    public C greaterThanOrEquals(Object value, boolean condition) {
        getNativeQueryConditional().ge(value, condition);
        return getChainer();
    }

    /**
     * append greater than or equals condition
     *
     * @param columnNameGetter the column value getter
     * @return greater than or equals condition
     */
    public <P1 extends PO> C greaterThanOrEquals(ColumnNameGetter<P1> columnNameGetter) {
        return greaterThanOrEquals(columnNameGetter, true);
    }

    /**
     * append greater than or equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return greater than or equals condition
     */
    public <P1 extends PO> C greaterThanOrEquals(ColumnNameGetter<P1> columnNameGetter, BooleanSupplier condition) {
        return greaterThanOrEquals(columnNameGetter, condition.getAsBoolean());
    }

    /**
     * append greater than or equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return greater than or equals condition
     */
    public <P1 extends PO> C greaterThanOrEquals(ColumnNameGetter<P1> columnNameGetter, boolean condition) {
        return greaterThanOrEquals(MybatisFlexs.getQueryColumn(columnNameGetter), condition);
    }

    /**
     * append greater than or equals condition if the column value is not null
     *
     * @param value the column value
     * @return greater than or equals condition
     * @see Nil#isNotNull(Object)
     */
    public C greaterThanOrEqualsIfNotNull(Object value) {
        return greaterThanOrEquals(value, If::notNull);
    }

    /**
     * append greater than or equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return greater than or equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public C greaterThanOrEqualsIfNotZeroValue(Number value) {
        return greaterThanOrEquals(value, If::notZeroValue);
    }

    // ================================================ less than condition ================================================

    /**
     * append less than condition
     *
     * @param value the column value
     * @return less than condition
     */
    public C lessThan(Object value) {
        return lessThan(value, true);
    }

    /**
     * append less than condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than condition
     */
    public C lessThan(Object value, BooleanSupplier condition) {
        return lessThan(value, condition.getAsBoolean());
    }

    /**
     * append less than condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than condition
     */
    public <U> C lessThan(U value, Predicate<U> condition) {
        return lessThan(value, condition.test(value));
    }

    /**
     * append less than condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than condition
     */
    public C lessThan(Object value, boolean condition) {
        getNativeQueryConditional().lt(value, condition);
        return getChainer();
    }

    /**
     * append less than condition
     *
     * @param columnNameGetter the column value getter
     * @return less than condition
     */
    public <P1 extends PO> C lessThan(ColumnNameGetter<P1> columnNameGetter) {
        return lessThan(columnNameGetter, true);
    }

    /**
     * append less than condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return less than condition
     */
    public <P1 extends PO> C lessThan(ColumnNameGetter<P1> columnNameGetter, BooleanSupplier condition) {
        return lessThan(columnNameGetter, condition.getAsBoolean());
    }

    /**
     * append less than condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return less than condition
     */
    public <P1 extends PO> C lessThan(ColumnNameGetter<P1> columnNameGetter, boolean condition) {
        return lessThan(MybatisFlexs.getQueryColumn(columnNameGetter), condition);
    }

    /**
     * append less than condition if the column value is not null
     *
     * @param value the column value
     * @return less than condition
     * @see Nil#isNotNull(Object)
     */
    public C lessThanIfNotNull(Object value) {
        return lessThan(value, If::notNull);
    }

    /**
     * append less than condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return less than condition
     * @see Nil#isNotZeroValue(Number)
     */
    public C lessThanIfNotZeroValue(Number value) {
        return lessThan(value, If::notZeroValue);
    }

    // ================================================ less than or equals condition ================================================

    /**
     * append less than or equals condition
     *
     * @param value the column value
     * @return less than or equals condition
     */
    public C lessThanOrEquals(Object value) {
        return lessThanOrEquals(value, true);
    }

    /**
     * append less than or equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals condition
     */
    public C lessThanOrEquals(Object value, BooleanSupplier condition) {
        return lessThanOrEquals(value, condition.getAsBoolean());
    }

    /**
     * append less than or equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals condition
     */
    public <U> C lessThanOrEquals(U value, Predicate<U> condition) {
        return lessThanOrEquals(value, condition.test(value));
    }

    /**
     * append less than or equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals condition
     */
    public C lessThanOrEquals(Object value, boolean condition) {
        getNativeQueryConditional().le(value, condition);
        return getChainer();
    }

    /**
     * append less than or equals condition
     *
     * @param columnNameGetter the column value getter
     * @return less than or equals condition
     */
    public <P1 extends PO> C lessThanOrEquals(ColumnNameGetter<P1> columnNameGetter) {
        return lessThanOrEquals(columnNameGetter, true);
    }

    /**
     * append less than or equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return less than or equals condition
     */
    public <P1 extends PO> C lessThanOrEquals(ColumnNameGetter<P1> columnNameGetter, BooleanSupplier condition) {
        return lessThanOrEquals(columnNameGetter, condition.getAsBoolean());
    }

    /**
     * append less than or equals condition if the append condition return true
     *
     * @param columnNameGetter the column value getter
     * @param condition        the append condition
     * @return less than or equals condition
     */
    public <P1 extends PO> C lessThanOrEquals(ColumnNameGetter<P1> columnNameGetter, boolean condition) {
        return lessThanOrEquals(MybatisFlexs.getQueryColumn(columnNameGetter), condition);
    }

    /**
     * append less than or equals condition if the column value is not null
     *
     * @param value the column value
     * @return less than or equals condition
     * @see Nil#isNotNull(Object)
     */
    public C lessThanOrEqualsIfNotNull(Object value) {
        return lessThanOrEquals(value, If::notNull);
    }

    /**
     * append less than or equals condition if the column value is not zero value, the zero value is {@code null} or {@code 0}
     *
     * @param value the column value
     * @return less than or equals condition
     * @see Nil#isNotZeroValue(Number)
     */
    public C lessThanOrEqualsIfNotZeroValue(Number value) {
        return lessThanOrEquals(value, If::notZeroValue);
    }

    // ================================================ in condition ================================================

    /**
     * append in condition
     *
     * @param values the column values
     * @return in condition
     */
    public C in(Object... values) {
        return in(values, true);
    }

    /**
     * append in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in condition
     */
    public C in(Object[] values, BooleanSupplier condition) {
        return in(values, condition.getAsBoolean());
    }

    /**
     * append in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in condition
     */
    public <U> C in(U[] values, Predicate<U[]> condition) {
        return in(values, condition.test(values));
    }

    /**
     * append in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in condition
     */
    public C in(Object[] values, boolean condition) {
        if (Nil.isEmpty(values)) {
            getChainer().setAllowToRunSql(false);
        }
        getNativeQueryConditional().in(values, condition);
        return getChainer();
    }

    /**
     * append in condition
     *
     * @param values the column values
     * @return in condition
     */
    public C in(Iterable<?> values) {
        return in(values, true);
    }

    /**
     * append in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in condition
     */
    public C in(Iterable<?> values, BooleanSupplier condition) {
        return in(values, condition.getAsBoolean());
    }

    /**
     * append in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in condition
     */
    public <U> C in(Iterable<U> values, Predicate<Iterable<U>> condition) {
        return in(values, condition.test(values));
    }

    /**
     * append in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in condition
     */
    public C in(Iterable<?> values, boolean condition) {
        if (values instanceof Collection<?> collectionTypeValues) {
            in(collectionTypeValues.toArray(), condition);
        } else {
            in(Converts.toArray(values), condition);
        }
        return getChainer();
    }

    /**
     * append in condition if the column values is not null and at least one size
     *
     * @param values the column values
     * @return in condition
     * @see Nil#isNotEmpty(Object...)
     */
    public C inIfNotEmpty(Object... values) {
        return in(values, If::notEmpty);
    }

    /**
     * append in condition if the column values is not null and at least one size
     *
     * @param values the column values
     * @return in condition
     * @see Nil#isNotEmpty(Iterable)
     */
    public C inIfNotEmpty(Iterable<?> values) {
        return in(values, If::notEmpty);
    }

    // ================================================ not in condition ================================================

    /**
     * append not in condition
     *
     * @param values the column values
     * @return not in condition
     */
    public C notIn(Object... values) {
        return notIn(values, true);
    }

    /**
     * append not in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in condition
     */
    public C notIn(Object[] values, BooleanSupplier condition) {
        return notIn(values, condition.getAsBoolean());
    }

    /**
     * append not in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in condition
     */
    public <U> C notIn(U[] values, Predicate<U[]> condition) {
        return notIn(values, condition.test(values));
    }

    /**
     * append not in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in condition
     */
    public C notIn(Object[] values, boolean condition) {
        getNativeQueryConditional().notIn(values, condition);
        return getChainer();
    }

    /**
     * append not in condition
     *
     * @param values the column values
     * @return not in condition
     */
    public C notIn(Iterable<?> values) {
        return notIn(values, true);
    }

    /**
     * append not in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in condition
     */
    public C notIn(Iterable<?> values, BooleanSupplier condition) {
        return notIn(values, condition.getAsBoolean());
    }

    /**
     * append not in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in condition
     */
    public <U> C notIn(Iterable<U> values, Predicate<Iterable<U>> condition) {
        return notIn(values, condition.test(values));
    }

    /**
     * append not in condition if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in condition
     */
    public C notIn(Iterable<?> values, boolean condition) {
        if (values instanceof Collection<?> collectionTypeValues) {
            notIn(collectionTypeValues.toArray(), condition);
        } else {
            notIn(Converts.toArray(values), condition);
        }
        return getChainer();
    }

    /**
     * append not in condition if the column values is not null and at least one size
     *
     * @param values the column values
     * @return not in condition
     * @see Nil#isNotEmpty(Object...)
     */
    public C notInIfNotEmpty(Object... values) {
        return notIn(values, If::notEmpty);
    }

    /**
     * append not in condition if the column values is not null and at least one size
     *
     * @param values the column values
     * @return not in condition
     * @see Nil#isNotEmpty(Iterable)
     */
    public C notInIfNotEmpty(Iterable<?> values) {
        return notIn(values, If::notEmpty);
    }

    // ================================================ between condition ================================================

    /**
     * append between condition
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @return between condition
     */
    public C between(Object startValue, Object endValue) {
        return between(startValue, endValue, true);
    }

    /**
     * append between condition if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return between condition
     */
    public C between(Object startValue, Object endValue, BooleanSupplier condition) {
        return between(startValue, endValue, condition.getAsBoolean());
    }

    /**
     * append between condition if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return between condition
     */
    public <S, E> C between(S startValue, E endValue, BiPredicate<S, E> condition) {
        return between(startValue, endValue, condition.test(startValue, endValue));
    }

    /**
     * append between condition if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return between condition
     */
    public C between(Object startValue, Object endValue, boolean condition) {
        getNativeQueryConditional().between(startValue, endValue, condition);
        return getChainer();
    }

    /**
     * append between condition if start column value and end column value are not null
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @return between condition
     * @see Nil#isAllNotNull(Object...)
     */
    public C betweenIfAllNotNull(Object startValue, Object endValue) {
        return between(startValue, endValue, Nil.isAllNotNull(startValue, endValue));
    }

    // ================================================ not between condition ================================================

    /**
     * append not between condition
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @return not between condition
     */
    public C notBetween(Object startValue, Object endValue) {
        return notBetween(startValue, endValue, true);
    }

    /**
     * append not between condition if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return not between condition
     */
    public C notBetween(Object startValue, Object endValue, BooleanSupplier condition) {
        return notBetween(startValue, endValue, condition.getAsBoolean());
    }

    /**
     * append not between condition if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return not between condition
     */
    public <S, E> C notBetween(S startValue, E endValue, BiPredicate<S, E> condition) {
        return notBetween(startValue, endValue, condition.test(startValue, endValue));
    }

    /**
     * append not between condition if the append condition return true
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @param condition  the append condition
     * @return not between condition
     */
    public C notBetween(Object startValue, Object endValue, boolean condition) {
        getNativeQueryConditional().notBetween(startValue, endValue, condition);
        return getChainer();
    }

    /**
     * append not between condition if start column value and end column value are not null
     *
     * @param startValue the between start column value
     * @param endValue   the between end column value
     * @return not between condition
     * @see Nil#isAllNotNull(Object...)
     */
    public C notBetweenIfAllNotNull(Object startValue, Object endValue) {
        return notBetween(startValue, endValue, Nil.isAllNotNull(startValue, endValue));
    }

    // ================================================ like "%value%" condition ================================================

    /**
     * append like {@code "%value%"} condition
     *
     * @param value the column value
     * @return like {@code "%value%"} condition
     */
    public C like(Object value) {
        return like(value, true);
    }

    /**
     * append like {@code "%value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} condition
     */
    public C like(Object value, BooleanSupplier condition) {
        return like(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "%value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} condition
     */
    public <U> C like(U value, Predicate<U> condition) {
        return like(value, condition.test(value));
    }

    /**
     * append like {@code "%value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} condition
     */
    public C like(Object value, boolean condition) {
        getNativeQueryConditional().like(value, condition);
        return getChainer();
    }

    /**
     * append like {@code "%value%"} condition if the column value is not null
     *
     * @param value the column value
     * @return like {@code "%value%"} condition
     * @see Nil#isNotNull(Object)
     */
    public C likeIfNotNull(Object value) {
        return like(value, If::notNull);
    }

    /**
     * append like {@code "%value%"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "%value%"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C likeIfNotBlank(CharSequence value) {
        return like(value, If::notBlank);
    }

    // ================================================ like "value%" condition ================================================

    /**
     * append like {@code "value%"} condition
     *
     * @param value the column value
     * @return like {@code "value%"} condition
     */
    public C likeLeft(Object value) {
        return likeLeft(value, true);
    }

    /**
     * append like {@code "value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} condition
     */
    public C likeLeft(Object value, BooleanSupplier condition) {
        return likeLeft(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} condition
     */
    public <U> C likeLeft(U value, Predicate<U> condition) {
        return likeLeft(value, condition.test(value));
    }

    /**
     * append like {@code "value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} condition
     */
    public C likeLeft(Object value, boolean condition) {
        getNativeQueryConditional().likeLeft(value, condition);
        return getChainer();
    }

    /**
     * append like {@code "value%"} condition if the column value is not null
     *
     * @param value the column value
     * @return like {@code "value%"} condition
     * @see Nil#isNotNull(Object)
     */
    public C likeLeftIfNotNull(Object value) {
        return likeLeft(value, If::notNull);
    }

    /**
     * append like {@code "value%"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "value%"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C likeLeftIfNotBlank(CharSequence value) {
        return likeLeft(value, If::notBlank);
    }
    // ================================================ like "%value" condition ================================================

    /**
     * append like {@code "%value"} condition
     *
     * @param value the column value
     * @return like {@code "%value"} condition
     */
    public C likeRight(Object value) {
        return likeRight(value, true);
    }

    /**
     * append like {@code "%value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} condition
     */
    public C likeRight(Object value, BooleanSupplier condition) {
        return likeRight(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "%value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} condition
     */
    public <U> C likeRight(U value, Predicate<U> condition) {
        return likeRight(value, condition.test(value));
    }

    /**
     * append like {@code "%value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} condition
     */
    public C likeRight(Object value, boolean condition) {
        getNativeQueryConditional().likeRight(value, condition);
        return getChainer();
    }

    /**
     * append like {@code "%value"} condition if the column value is not null
     *
     * @param value the column value
     * @return like {@code "%value"} condition
     * @see Nil#isNotNull(Object)
     */
    public C likeRightIfNotNull(Object value) {
        return likeRight(value, If::notNull);
    }

    /**
     * append like {@code "%value"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "%value"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C likeRightIfNotBlank(CharSequence value) {
        return likeRight(value, If::notBlank);
    }
    // ================================================ like "value" condition ================================================

    /**
     * append like {@code "value"} condition
     *
     * @param value the column value
     * @return like {@code "value"} condition
     */
    public C likeRaw(Object value) {
        return likeRaw(value, true);
    }

    /**
     * append like {@code "value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} condition
     */
    public C likeRaw(Object value, BooleanSupplier condition) {
        return likeRaw(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} condition
     */
    public <U> C likeRaw(U value, Predicate<U> condition) {
        return likeRaw(value, condition.test(value));
    }

    /**
     * append like {@code "value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} condition
     */
    public C likeRaw(Object value, boolean condition) {
        QueryColumn queryColumn = Reflects.getFieldValue(getNativeQueryConditional(), QUERY_COLUMN_FIELD_NAME);
        QueryCondition queryCondition = Reflects.invoke(queryColumn, LIKE_RAW_METHOD_NAME, value, condition);
        Reflects.invoke(nativeQueryConditional, ADD_WHERE_QUERY_CONDITION_METHOD_NAME, queryCondition);
        return getChainer();
    }

    /**
     * append like {@code "value"} condition if the column value is not null
     *
     * @param value the column value
     * @return like {@code "value"} condition
     * @see Nil#isNotNull(Object)
     */
    public C likeRawIfNotNull(Object value) {
        return likeRaw(value, If::notNull);
    }

    /**
     * append like {@code "value"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return like {@code "value"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C likeRawIfNotBlank(CharSequence value) {
        return likeRaw(value, If::notBlank);
    }
    // ================================================ not like "%value%" condition ================================================

    /**
     * append not like {@code "%value%"} condition
     *
     * @param value the column value
     * @return not like {@code "%value%"} condition
     */
    public C notLike(Object value) {
        return notLike(value, true);
    }

    /**
     * append not like {@code "%value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} condition
     */
    public C notLike(Object value, BooleanSupplier condition) {
        return notLike(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "%value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} condition
     */
    public <U> C notLike(U value, Predicate<U> condition) {
        return notLike(value, condition.test(value));
    }

    /**
     * append not like {@code "%value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} condition
     */
    public C notLike(Object value, boolean condition) {
        getNativeQueryConditional().notLike(value, condition);
        return getChainer();
    }

    /**
     * append not like {@code "%value%"} condition if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "%value%"} condition
     * @see Nil#isNotNull(Object)
     */
    public C notLikeIfNotNull(Object value) {
        return notLike(value, If::notNull);
    }

    /**
     * append not like {@code "%value%"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value%"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C notLikeIfNotBlank(CharSequence value) {
        return notLike(value, If::notBlank);
    }

    // ================================================ not like "value%" condition ================================================

    /**
     * append not like {@code "value%"} condition
     *
     * @param value the column value
     * @return not like {@code "value%"} condition
     */
    public C notLikeLeft(Object value) {
        return notLikeLeft(value, true);
    }

    /**
     * append not like {@code "value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} condition
     */
    public C notLikeLeft(Object value, BooleanSupplier condition) {
        return notLikeLeft(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} condition
     */
    public <U> C notLikeLeft(U value, Predicate<U> condition) {
        return notLikeLeft(value, condition.test(value));
    }

    /**
     * append not like {@code "value%"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} condition
     */
    public C notLikeLeft(Object value, boolean condition) {
        getNativeQueryConditional().notLikeLeft(value, condition);
        return getChainer();
    }

    /**
     * append not like {@code "value%"} condition if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "value%"} condition
     * @see Nil#isNotNull(Object)
     */
    public C notLikeLeftIfNotNull(Object value) {
        return notLikeLeft(value, If::notNull);
    }

    /**
     * append not like {@code "value%"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value%"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C notLikeLeftIfNotBlank(CharSequence value) {
        return notLikeLeft(value, If::notBlank);
    }
    // ================================================ not like "%value" condition ================================================

    /**
     * append not like {@code "%value"} condition
     *
     * @param value the column value
     * @return not like {@code "%value"} condition
     */
    public C notLikeRight(Object value) {
        return notLikeRight(value, true);
    }

    /**
     * append not like {@code "%value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} condition
     */
    public C notLikeRight(Object value, BooleanSupplier condition) {
        return notLikeRight(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "%value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} condition
     */
    public <U> C notLikeRight(U value, Predicate<U> condition) {
        return notLikeRight(value, condition.test(value));
    }

    /**
     * append not like {@code "%value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} condition
     */
    public C notLikeRight(Object value, boolean condition) {
        getNativeQueryConditional().notLikeRight(value, condition);
        return getChainer();
    }

    /**
     * append not like {@code "%value"} condition if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "%value"} condition
     * @see Nil#isNotNull(Object)
     */
    public C notLikeRightIfNotNull(Object value) {
        return notLikeRight(value, If::notNull);
    }

    /**
     * append not like {@code "%value"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C notLikeRightIfNotBlank(CharSequence value) {
        return notLikeRight(value, If::notBlank);
    }

    // ================================================ not like "value" condition ================================================

    /**
     * append not like {@code "value"} condition
     *
     * @param value the column value
     * @return not like {@code "value"} condition
     */
    public C notLikeRaw(Object value) {
        return notLikeRaw(value, true);
    }

    /**
     * append not like {@code "value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} condition
     */
    public C notLikeRaw(Object value, BooleanSupplier condition) {
        return notLikeRaw(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} condition
     */
    public <U> C notLikeRaw(U value, Predicate<U> condition) {
        return notLikeRaw(value, condition.test(value));
    }

    /**
     * append not like {@code "value"} condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} condition
     */
    public C notLikeRaw(Object value, boolean condition) {
        QueryColumn queryColumn = Reflects.getFieldValue(getNativeQueryConditional(), QUERY_COLUMN_FIELD_NAME);
        QueryCondition queryCondition = Reflects.invoke(queryColumn, NOT_LIKE_RAW_METHOD_NAME, value, condition);
        Reflects.invoke(nativeQueryConditional, ADD_WHERE_QUERY_CONDITION_METHOD_NAME, queryCondition);
        return getChainer();
    }

    /**
     * append not like {@code "value"} condition if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "value"} condition
     * @see Nil#isNotNull(Object)
     */
    public C notLikeRawIfNotNull(Object value) {
        return notLikeRaw(value, If::notNull);
    }

    /**
     * append not like {@code "value"} condition if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value"} condition
     * @see Nil#isNotBlank(CharSequence)
     */
    public C notLikeRawIfNotBlank(CharSequence value) {
        return notLikeRaw(value, If::notBlank);
    }

    // ================================================ in null condition ================================================

    /**
     * append is null condition
     *
     * @return is null condition
     */
    public C isNull() {
        return isNull(true);
    }

    /**
     * append is null condition if the append condition return true
     *
     * @param condition the append condition
     * @return is null condition
     */
    public C isNull(BooleanSupplier condition) {
        return isNull(condition.getAsBoolean());
    }

    /**
     * append is null condition if the append condition return true
     *
     * @param condition the append condition
     * @return is null condition
     */
    public C isNull(boolean condition) {
        getNativeQueryConditional().isNull(condition);
        return getChainer();
    }

    // ================================================ in not null condition ================================================

    /**
     * append is not null condition
     *
     * @return is not null condition
     */
    public C isNotNull() {
        return isNotNull(true);
    }

    /**
     * append is not null condition if the append condition return true
     *
     * @param condition the append condition
     * @return is not null condition
     */
    public C isNotNull(BooleanSupplier condition) {
        return isNotNull(condition.getAsBoolean());
    }

    /**
     * append is not null condition if the append condition return true
     *
     * @param condition the append condition
     * @return is not null condition
     */
    public C isNotNull(boolean condition) {
        getNativeQueryConditional().isNotNull(condition);
        return getChainer();
    }

}