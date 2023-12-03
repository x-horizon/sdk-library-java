// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.query;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
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

    public ConstrainedQueryConditionAppender(W queryWrapper, QueryColumn queryColumn, SqlConnector connector) {
        super(queryWrapper, queryColumn, connector);
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
    public W isNull() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W isNull(BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W isNotNull() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    public W isNotNull(BooleanSupplier isEffective) {
        throw new UnsupportedException();
    }

}
