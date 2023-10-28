// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.expression;

/**
 * expression template
 *
 * @author wjm
 * @since 2023-06-08 10:14
 */
public interface ExpressionTemplate {

    /**
     * parse string
     *
     * @param expression the expression
     * @return parse result
     */
    Object parse(String expression);

    /**
     * see {@link #parse(String)}
     *
     * @param expression the expression
     * @param resultType the result class type
     * @param <R>        the result type
     * @return parse result
     */
    default <R> R parse(String expression, Class<R> resultType) {
        return resultType.cast(parse(expression));
    }

    /**
     * parse object
     *
     * @param object     the parsed object
     * @param expression the expression
     * @param <T>        the parsed object type
     * @return parse result
     */
    <T> Object parse(T object, String expression);

    /**
     * see {@link #parse(Object, String)}
     *
     * @param object     the parsed object
     * @param expression the expression
     * @param resultType the result class type
     * @param <T>        the parsed object type
     * @param <R>        the result type
     * @return parse result
     */
    default <T, R> R parse(T object, String expression, Class<R> resultType) {
        return resultType.cast(parse(object, expression));
    }

}
