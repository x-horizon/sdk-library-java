package cn.srd.itcp.sugar.mybatis.plus.core;

import java.util.Collection;

/**
 * {@link MpQueryWrapper}的使用方式
 *
 * @author wjm
 * @date 2021/3/31 15:17
 */
public class MpQueryWrappers {

    private MpQueryWrappers() {

    }

    public static <T> MpQueryWrapper<T> likeIfPresent(String column, String value) {
        return new MpQueryWrapper<T>().likeIfPresent(column, value);
    }

    public static <T> MpQueryWrapper<T> inIfPresent(String column, Collection<?> values) {
        return new MpQueryWrapper<T>().inIfPresent(column, values);
    }

    public static <T> MpQueryWrapper<T> inIfPresent(String column, Object... values) {
        return new MpQueryWrapper<T>().inIfPresent(column, values);
    }

    public static <T> MpQueryWrapper<T> eqIfPresent(String column, Object value) {
        return new MpQueryWrapper<T>().eqIfPresent(column, value);
    }

    public static <T> MpQueryWrapper<T> gtIfPresent(String column, Object value) {
        return new MpQueryWrapper<T>().gtIfPresent(column, value);
    }

    public static <T> MpQueryWrapper<T> betweenIfPresent(String column, Object value1, Object value2) {
        return new MpQueryWrapper<T>().betweenIfPresent(column, value1, value2);
    }

    public static <T> MpQueryWrapper<T> eq(boolean condition, String column, Object value) {
        return new MpQueryWrapper<T>().eq(condition, column, value);
    }

    public static <T> MpQueryWrapper<T> eq(String column, Object value) {
        return new MpQueryWrapper<T>().eq(column, value);
    }

    public static <T> MpQueryWrapper<T> in(String column, Object... value) {
        return new MpQueryWrapper<T>().in(column, value);
    }

    public static <T> MpQueryWrapper<T> exists(String existsSql) {
        return new MpQueryWrapper<T>().exists(existsSql);
    }

    public static <T> MpQueryWrapper<T> exists(boolean condition, String existsSql) {
        return new MpQueryWrapper<T>().exists(condition, existsSql);
    }

    public static <T> MpQueryWrapper<T> notExists(String existsSql) {
        return new MpQueryWrapper<T>().notExists(existsSql);
    }

    public static <T> MpQueryWrapper<T> notExists(boolean condition, String existsSql) {
        return new MpQueryWrapper<T>().notExists(condition, existsSql);
    }

    public static <T> MpQueryWrapper<T> orderByAsc(String column) {
        return new MpQueryWrapper<T>().orderByAsc(column);
    }

    public static <T> MpQueryWrapper<T> orderByDesc(String column) {
        return new MpQueryWrapper<T>().orderByDesc(column);
    }

    public static <T> MpQueryWrapper<T> apply(String applySql, Object... value) {
        return new MpQueryWrapper<T>().apply(applySql, value);
    }

    public static <T> MpQueryWrapper<T> apply(boolean condition, String applySql, Object... value) {
        return new MpQueryWrapper<T>().apply(condition, applySql, value);
    }

    public static <T> MpQueryWrapper<T> last(String lastSql) {
        return new MpQueryWrapper<T>().last(lastSql);
    }

    public static <T> MpQueryWrapper<T> isNotNull(String lastSql) {
        return new MpQueryWrapper<T>().isNotNull(lastSql);
    }

}
