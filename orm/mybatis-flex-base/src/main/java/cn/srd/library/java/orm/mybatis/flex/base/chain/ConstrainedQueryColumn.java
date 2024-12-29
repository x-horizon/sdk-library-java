package cn.srd.library.java.orm.mybatis.flex.base.chain;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
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
 * constrained query column
 *
 * @author wjm
 * @since 2023-11-24 11:38
 */
@Deprecated
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.ALL)
public class ConstrainedQueryColumn extends QueryColumn {

    @Serial private static final long serialVersionUID = -6751799572163676067L;

    public ConstrainedQueryColumn() {
    }

    public ConstrainedQueryColumn(String name) {
        super(name);
    }

    public ConstrainedQueryColumn(String tableName, String name) {
        super(tableName, name);
    }

    public ConstrainedQueryColumn(String schema, String tableName, String name) {
        super(schema, tableName, name);
    }

    public ConstrainedQueryColumn(String schema, String tableName, String name, String alias) {
        super(schema, tableName, name, alias);
    }

    public ConstrainedQueryColumn(QueryTable queryTable, String name) {
        super(queryTable, name);
    }

    public ConstrainedQueryColumn(TableDef tableDef, String name) {
        super(tableDef, name);
    }

    public ConstrainedQueryColumn(TableDef tableDef, String name, String alias) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public QueryCondition equalsIfCondition(Object value, BooleanSupplier condition) {
        return equalsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public <P> QueryCondition equalsIfCondition(P value, Predicate<P> condition) {
        return equalsIfCondition(value, condition.test(value));
    }

    /**
     * append equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return equals condition
     */
    public QueryCondition equalsIfCondition(Object value, boolean condition) {
        return super.eq(value, condition);
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
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public QueryCondition notEqualsIfCondition(Object value, BooleanSupplier condition) {
        return notEqualsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public <P> QueryCondition notEqualsIfCondition(P value, Predicate<P> condition) {
        return notEqualsIfCondition(value, condition.test(value));
    }

    /**
     * append not equals condition if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not equals condition
     */
    public QueryCondition notEqualsIfCondition(Object value, boolean condition) {
        return super.ne(value, condition);
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
     * @param value     the column value
     * @param condition the append condition
     * @return greater than conditdion
     */
    public QueryCondition greaterThanIfCondition(Object value, BooleanSupplier condition) {
        return greaterThanIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than conditdion
     */
    public <P> QueryCondition greaterThanIfCondition(P value, Predicate<P> condition) {
        return greaterThanIfCondition(value, condition.test(value));
    }

    /**
     * append greater than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than conditdion
     */
    public QueryCondition greaterThanIfCondition(Object value, boolean condition) {
        return super.gt(value, condition);
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
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals conditdion
     */
    public QueryCondition greaterThanOrEqualsIfCondition(Object value, BooleanSupplier condition) {
        return greaterThanOrEqualsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals conditdion
     */
    public <P> QueryCondition greaterThanOrEqualsIfCondition(P value, Predicate<P> condition) {
        return greaterThanOrEqualsIfCondition(value, condition.test(value));
    }

    /**
     * append greater than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return greater than or equals conditdion
     */
    public QueryCondition greaterThanOrEqualsIfCondition(Object value, boolean condition) {
        return super.ge(value, condition);
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
     * @param value     the column value
     * @param condition the append condition
     * @return less than conditdion
     */
    public QueryCondition lessThanIfCondition(Object value, BooleanSupplier condition) {
        return lessThanIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than conditdion
     */
    public <P> QueryCondition lessThanIfCondition(P value, Predicate<P> condition) {
        return lessThanIfCondition(value, condition.test(value));
    }

    /**
     * append less than conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than conditdion
     */
    public QueryCondition lessThanIfCondition(Object value, boolean condition) {
        return super.lt(value, condition);
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
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals conditdion
     */
    public QueryCondition lessThanOrEqualsIfCondition(Object value, BooleanSupplier condition) {
        return lessThanOrEqualsIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals conditdion
     */
    public <P> QueryCondition lessThanOrEqualsIfCondition(P value, Predicate<P> condition) {
        return lessThanOrEqualsIfCondition(value, condition.test(value));
    }

    /**
     * append less than or equals conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return less than or equals conditdion
     */
    public QueryCondition lessThanOrEqualsIfCondition(Object value, boolean condition) {
        return super.le(value, condition);
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
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Object[] values, BooleanSupplier condition) {
        return inIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public <P> QueryCondition inIfCondition(P[] values, Predicate<P[]> condition) {
        return inIfCondition(values, condition.test(values));
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Object[] values, boolean condition) {
        return super.in(values, condition);
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Iterable<?> values, BooleanSupplier condition) {
        return inIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public <P> QueryCondition inIfCondition(Iterable<P> values, Predicate<Iterable<P>> condition) {
        return inIfCondition(values, condition.test(values));
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(Iterable<?> values, boolean condition) {
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
    public QueryCondition inIfCondition(QueryWrapper queryWrapper, BooleanSupplier condition) {
        return inIfCondition(queryWrapper, condition.getAsBoolean());
    }

    /**
     * append in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return in conditdion
     */
    public QueryCondition inIfCondition(QueryWrapper queryWrapper, boolean condition) {
        return super.in(queryWrapper, condition);
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
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Object[] values, BooleanSupplier condition) {
        return notInIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public <P> QueryCondition notInIfCondition(P[] values, Predicate<P[]> condition) {
        return notInIfCondition(values, condition.test(values));
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Object[] values, boolean condition) {
        return super.notIn(values, condition);
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Iterable<?> values, BooleanSupplier condition) {
        return notInIfCondition(values, condition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public <P> QueryCondition notInIfCondition(Iterable<P> values, Predicate<Iterable<P>> condition) {
        return notInIfCondition(values, condition.test(values));
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(Iterable<?> values, boolean condition) {
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
    public QueryCondition notInIfCondition(QueryWrapper queryWrapper, BooleanSupplier condition) {
        return notInIfCondition(queryWrapper, condition.getAsBoolean());
    }

    /**
     * append not in conditdion if the append condition return true
     *
     * @param values    the column values
     * @param condition the append condition
     * @return not in conditdion
     */
    public QueryCondition notInIfCondition(QueryWrapper queryWrapper, boolean condition) {
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
    public QueryCondition betweenIfAllNotNull(Object startValue, Object endValue) {
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
    public QueryCondition betweenIfCondition(Object startValue, Object endValue, BooleanSupplier condition) {
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
    public <S, E> QueryCondition betweenIfCondition(S startValue, E endValue, BiPredicate<S, E> condition) {
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
    public QueryCondition betweenIfCondition(Object startValue, Object endValue, boolean condition) {
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
    public QueryCondition notBetweenIfAllNotNull(Object startValue, Object endValue) {
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
    public QueryCondition notBetweenIfCondition(Object startValue, Object endValue, BooleanSupplier condition) {
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
    public <S, E> QueryCondition notBetweenIfCondition(S startValue, E endValue, BiPredicate<S, E> condition) {
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
    public QueryCondition notBetweenIfCondition(Object startValue, Object endValue, boolean condition) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public QueryCondition likeIfCondition(Object value, BooleanSupplier condition) {
        return likeIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public <P> QueryCondition likeIfCondition(P value, Predicate<P> condition) {
        return likeIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value%"} conditdion
     */
    public QueryCondition likeIfCondition(Object value, boolean condition) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} conditdion
     */
    public QueryCondition likeLeftIfCondition(Object value, BooleanSupplier condition) {
        return likeLeftIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} conditdion
     */
    public <P> QueryCondition likeLeftIfCondition(P value, Predicate<P> condition) {
        return likeLeftIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value%"} conditdion
     */
    public QueryCondition likeLeftIfCondition(Object value, boolean condition) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} conditdion
     */
    public QueryCondition likeRightIfCondition(Object value, BooleanSupplier condition) {
        return likeRightIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} conditdion
     */
    public <P> QueryCondition likeRightIfCondition(P value, Predicate<P> condition) {
        return likeRightIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "%value"} conditdion
     */
    public QueryCondition likeRightIfCondition(Object value, boolean condition) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} conditdion
     */
    public QueryCondition likeRawIfCondition(Object value, BooleanSupplier condition) {
        return likeRawIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} conditdion
     */
    public <P> QueryCondition likeRawIfCondition(P value, Predicate<P> condition) {
        return likeRawIfCondition(value, condition.test(value));
    }

    /**
     * append like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return like {@code "value"} conditdion
     */
    public QueryCondition likeRawIfCondition(Object value, boolean condition) {
        return super.likeRaw(value, condition);
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
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public QueryCondition notLikeIfCondition(Object value, BooleanSupplier condition) {
        return notLikeIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public <P> QueryCondition notLikeIfCondition(P value, Predicate<P> condition) {
        return notLikeIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "%value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value%"} conditdion
     */
    public QueryCondition notLikeIfCondition(Object value, boolean condition) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public QueryCondition notLikeLeftIfCondition(Object value, BooleanSupplier condition) {
        return notLikeLeftIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public <P> QueryCondition notLikeLeftIfCondition(P value, Predicate<P> condition) {
        return notLikeLeftIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "value%"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value%"} conditdion
     */
    public QueryCondition notLikeLeftIfCondition(Object value, boolean condition) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public QueryCondition notLikeRightIfCondition(Object value, BooleanSupplier condition) {
        return notLikeRightIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public <P> QueryCondition notLikeRightIfCondition(P value, Predicate<P> condition) {
        return notLikeRightIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "%value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "%value"} conditdion
     */
    public QueryCondition notLikeRightIfCondition(Object value, boolean condition) {
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
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} conditdion
     */
    public QueryCondition notLikeRawIfCondition(Object value, BooleanSupplier condition) {
        return notLikeRawIfCondition(value, condition.getAsBoolean());
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} conditdion
     */
    public <P> QueryCondition notLikeRawIfCondition(P value, Predicate<P> condition) {
        return notLikeRawIfCondition(value, condition.test(value));
    }

    /**
     * append not like {@code "value"} conditdion if the append condition return true
     *
     * @param value     the column value
     * @param condition the append condition
     * @return not like {@code "value"} conditdion
     */
    public QueryCondition notLikeRawIfCondition(Object value, boolean condition) {
        return super.notLikeRaw(value, condition);
    }

    // ================================================ in null conditdion ================================================

    @Override
    public QueryCondition isNull() {
        return isNullIfCondition(true);
    }

    /**
     * append is null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public QueryCondition isNullIfCondition(BooleanSupplier condition) {
        return isNullIfCondition(condition.getAsBoolean());
    }

    /**
     * append is null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public QueryCondition isNullIfCondition(boolean condition) {
        return super.isNull(condition);
    }

    // ================================================ in not null conditdion ================================================

    @Override
    public QueryCondition isNotNull() {
        return isNotNullIfCondition(true);
    }

    /**
     * append is not null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public QueryCondition isNotNullIfCondition(BooleanSupplier condition) {
        return isNotNullIfCondition(condition.getAsBoolean());
    }

    /**
     * append is not null conditdion if the append condition return true
     *
     * @param condition the append condition
     * @return is null conditdion
     */
    public QueryCondition isNotNullIfCondition(boolean condition) {
        return super.isNotNull(condition);
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked most mybatis-flex original functions as deprecated, since mybatis-flex version 1.7.5, it is not recommended to use as following:

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
    public <P> QueryCondition eq(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition ne(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition gt(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition ge(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition lt(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition le(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition in(P[] value, Predicate<P[]> isEffective) {
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
    public <P extends Collection<?>> QueryCondition in(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition notIn(P[] value, Predicate<P[]> isEffective) {
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
    public <P extends Collection<?>> QueryCondition notIn(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition like(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition likeLeft(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition likeRight(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition likeRaw(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition notLike(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition notLikeLeft(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition notLikeRight(P value, Predicate<P> isEffective) {
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
    public <P> QueryCondition notLikeRaw(P value, Predicate<P> isEffective) {
        throw new UnsupportedException();
    }

}