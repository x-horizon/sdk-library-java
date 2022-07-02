package cn.sugar.mybatis.plus.core;

import cn.sugar.tools.core.Objects;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Collection;

/**
 * 拓展 MyBatis Plus QueryWrapper 类，主要增加如下功能：
 * <pre>
 * 1. 拼接条件的方法，增加 xxxIfPresent 方法，用于判断值不存在的时候，不要拼接到条件中。
 * </pre>
 *
 * @author wjm
 * @date 2020/01/05 13:45
 */
public class MpQueryWrapper<T> extends QueryWrapper<T> {

    private static final long serialVersionUID = 8790186304790523031L;

    /**
     * 不建议直接 new 该实例，而是使用如：WQueryWrappers.<T>eqIfPresent(column,value)
     */
    MpQueryWrapper() {
    }

    public MpQueryWrapper<T> likeIfPresent(String column, String value) {
        if (Objects.isNotEmpty(value)) {
            return (MpQueryWrapper<T>) super.like(column, value);
        }
        return this;
    }

    public MpQueryWrapper<T> inIfPresent(String column, Collection<?> values) {
        if (Objects.isNotEmpty(values)) {
            return (MpQueryWrapper<T>) super.in(column, values);
        }
        return this;
    }

    public MpQueryWrapper<T> inIfPresent(String column, Object... values) {
        if (Objects.isNotEmpty(values)) {
            return (MpQueryWrapper<T>) super.in(column, values);
        }
        return this;
    }

    public MpQueryWrapper<T> eqIfPresent(String column, Object value) {
        if (Objects.isNotEmpty(value)) {
            return (MpQueryWrapper<T>) super.eq(column, value);
        }
        return this;
    }

    public MpQueryWrapper<T> gtIfPresent(String column, Object value) {
        if (Objects.isNotEmpty(value)) {
            return (MpQueryWrapper<T>) super.gt(column, value);
        }
        return this;
    }

    public MpQueryWrapper<T> betweenIfPresent(String column, Object value1, Object value2) {
        if (!Objects.isAllEmpty(value1, value2)) {
            return (MpQueryWrapper<T>) super.between(column, value1, value2);
        }
        if (Objects.isNotEmpty(value1)) {
            return (MpQueryWrapper<T>) ge(column, value1);
        }
        if (Objects.isNotEmpty(value2)) {
            return (MpQueryWrapper<T>) le(column, value2);
        }
        return this;
    }

    // ========== 重写父类方法，方便链式调用，如果有没加上去的，在这里加上并注册至 WQueryWrappers 即可 ==========

    @Override
    public MpQueryWrapper<T> eq(boolean condition, String column, Object value) {
        super.eq(condition, column, value);
        return this;
    }

    @Override
    public MpQueryWrapper<T> eq(String column, Object value) {
        super.eq(column, value);
        return this;
    }

    @Override
    public MpQueryWrapper<T> in(String column, Object... value) {
        super.in(column, value);
        return this;
    }

    @Override
    public MpQueryWrapper<T> exists(String existsSql) {
        super.exists(existsSql);
        return this;
    }

    @Override
    public MpQueryWrapper<T> exists(boolean condition, String existsSql) {
        super.exists(condition, existsSql);
        return this;
    }

    @Override
    public MpQueryWrapper<T> notExists(String existsSql) {
        super.notExists(existsSql);
        return this;
    }

    @Override
    public MpQueryWrapper<T> notExists(boolean condition, String existsSql) {
        super.notExists(condition, existsSql);
        return this;
    }

    @Override
    public MpQueryWrapper<T> orderByAsc(String column) {
        super.orderByAsc(true, column);
        return this;
    }

    @Override
    public MpQueryWrapper<T> orderByDesc(String column) {
        super.orderByDesc(true, column);
        return this;
    }

    @Override
    public MpQueryWrapper<T> apply(String applySql, Object... value) {
        super.apply(applySql, value);
        return this;
    }

    @Override
    public MpQueryWrapper<T> apply(boolean condition, String applySql, Object... value) {
        super.apply(condition, applySql, value);
        return this;
    }

    @Override
    public MpQueryWrapper<T> last(String lastSql) {
        super.last(lastSql);
        return this;
    }

    @Override
    public MpQueryWrapper<T> isNotNull(String column) {
        super.isNotNull(column);
        return this;
    }

}
