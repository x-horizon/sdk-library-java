// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import com.mybatisflex.core.constant.SqlConnector;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryConditionBuilder;
import com.mybatisflex.core.query.QueryWrapper;

import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * @author wjm
 * @since 2023-12-03 23:35
 */
public class ConstrainedQueryConditionAppender<W extends QueryWrapper> extends QueryConditionBuilder<W> {

    public ConstrainedQueryConditionAppender(W queryWrapper, QueryColumn queryColumn, SqlConnector connector) {
        super(queryWrapper, queryColumn, connector);
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked most mybatis-flex original functions as deprecated, since mybatis-flex version 1.7.5, it is not recommended to use as following:

    @Override
    public W eq(Object value) {
        return null;
    }

    @Override
    public W eq(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W eq(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W eq(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W ne(Object value) {
        return null;
    }

    @Override
    public W ne(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W ne(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W ne(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W gt(Object value) {
        return null;
    }

    @Override
    public W gt(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W gt(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W gt(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W ge(Object value) {
        return null;
    }

    @Override
    public W ge(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W ge(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W ge(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W lt(Object value) {
        return null;
    }

    @Override
    public W lt(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W lt(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W lt(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W le(Object value) {
        return null;
    }

    @Override
    public W le(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W le(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W le(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W in(Object... value) {
        return null;
    }

    @Override
    public W in(Object[] value, boolean isEffective) {
        return null;
    }

    @Override
    public W in(Object[] value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W in(T[] value, Predicate<T[]> isEffective) {
        return null;
    }

    @Override
    public W in(Collection<?> value) {
        return null;
    }

    @Override
    public W in(Collection<?> value, boolean isEffective) {
        return null;
    }

    @Override
    public W in(Collection<?> value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T extends Collection<?>> W in(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W in(QueryWrapper queryWrapper) {
        return null;
    }

    @Override
    public W in(QueryWrapper queryWrapper, boolean isEffective) {
        return null;
    }

    @Override
    public W in(QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public W notIn(Object... value) {
        return null;
    }

    @Override
    public W notIn(Object[] value, boolean isEffective) {
        return null;
    }

    @Override
    public W notIn(Object[] value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W notIn(T[] value, Predicate<T[]> isEffective) {
        return null;
    }

    @Override
    public W notIn(Collection<?> value) {
        return null;
    }

    @Override
    public W notIn(Collection<?> value, boolean isEffective) {
        return null;
    }

    @Override
    public W notIn(Collection<?> value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T extends Collection<?>> W notIn(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W notIn(QueryWrapper queryWrapper) {
        return null;
    }

    @Override
    public W notIn(QueryWrapper queryWrapper, boolean isEffective) {
        return null;
    }

    @Override
    public W notIn(QueryWrapper queryWrapper, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public W between(Object start, Object end) {
        return null;
    }

    @Override
    public W between(Object start, Object end, boolean isEffective) {
        return null;
    }

    @Override
    public W between(Object start, Object end, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <S, E> W between(S start, E end, BiPredicate<S, E> isEffective) {
        return null;
    }

    @Override
    public W notBetween(Object start, Object end) {
        return null;
    }

    @Override
    public W notBetween(Object start, Object end, boolean isEffective) {
        return null;
    }

    @Override
    public W notBetween(Object start, Object end, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <S, E> W notBetween(S start, E end, BiPredicate<S, E> isEffective) {
        return null;
    }

    @Override
    public W like(Object value) {
        return null;
    }

    @Override
    public W like(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W like(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W like(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W likeLeft(Object value) {
        return null;
    }

    @Override
    public W likeLeft(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W likeLeft(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W likeLeft(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W likeRight(Object value) {
        return null;
    }

    @Override
    public W likeRight(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W likeRight(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W likeRight(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W notLike(Object value) {
        return null;
    }

    @Override
    public W notLike(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W notLike(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W notLike(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W notLikeLeft(Object value) {
        return null;
    }

    @Override
    public W notLikeLeft(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W notLikeLeft(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W notLikeLeft(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W notLikeRight(Object value) {
        return null;
    }

    @Override
    public W notLikeRight(Object value, boolean isEffective) {
        return null;
    }

    @Override
    public W notLikeRight(Object value, BooleanSupplier isEffective) {
        return null;
    }

    @Override
    public <T> W notLikeRight(T value, Predicate<T> isEffective) {
        return null;
    }

    @Override
    public W isNull(boolean isEffective) {
        return null;
    }

    @Override
    public W isNotNull(boolean isEffective) {
        return null;
    }

}
