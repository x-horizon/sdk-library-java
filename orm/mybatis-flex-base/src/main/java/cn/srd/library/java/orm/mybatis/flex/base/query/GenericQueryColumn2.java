// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.object.Nil;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryCondition;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * TODO wjm 在使用函数时，一些数字、字符串常量需要通过特定的方法去构造，例如：
 *  TODO wjm number()：构建数字常量
 *  TODO wjm string()：构建字符串常量
 *  TODO wjm column()：构建自定义列
 *  TODO wjm 目前，MyBatis-Flex 已支持 110+ 个常见的 SQL 函数，查看已支持的 所有函数。 若还不满足，您可以参考 QueryMethods ，然后在自己的项目里进行自定义扩展。
 *
 * @author wjm
 * @since 2023-11-24 11:38
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.ALL)
public class GenericQueryColumn2 extends QueryColumn {

    // ================================================ less than conditdion ================================================

    // ================================================ not like "%value%" conditdion ================================================

    /**
     * append not like {@code "%value%"} conditdion if the column value is not null
     *
     * @param value the column value
     * @return not like {@code "%value%"} conditdion
     * @see Nil#isNotNull(Object)
     */
    public QueryCondition notLikeIfNotNull(Object value) {
        return notLikeIfCondition(value, Nil.isNotNull(value));
    }

    /**
     * append not like {@code "%value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeIfNotBlank(CharSequence value) {
        return notLikeIfCondition(value, Nil.isNotBlank(value));
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
        return notLikeLeftIfCondition(value, Nil.isNotNull(value));
    }

    /**
     * append not like {@code "value%"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value%"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeLeftIfNotBlank(CharSequence value) {
        return notLikeLeftIfCondition(value, Nil.isNotBlank(value));
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
        return notLikeRightIfCondition(value, Nil.isNotNull(value));
    }

    /**
     * append not like {@code "%value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "%value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeRightIfNotBlank(CharSequence value) {
        return notLikeRightIfCondition(value, Nil.isNotBlank(value));
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
        return notLikeRawIfCondition(value, Nil.isNotNull(value));
    }

    /**
     * append not like {@code "value"} conditdion if the column value is not blank string
     *
     * @param value the column value
     * @return not like {@code "value"} conditdion
     * @see Nil#isNotBlank(CharSequence)
     */
    public QueryCondition notLikeRawIfNotBlank(CharSequence value) {
        return notLikeRawIfCondition(value, Nil.isNotBlank(value));
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

}
