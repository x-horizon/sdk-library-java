package cn.srd.itcp.sugar.component.expression.contract.core;

/**
 * expression template
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
public interface ExpressionTemplate {

    /**
     * parse string
     *
     * @param expression the expression
     * @param resultType the result class type
     * @param <R>        the result type
     * @return parse result
     */
    <R> R parse(String expression, Class<R> resultType);

    /**
     * parse object
     *
     * @param object     the parsed object
     * @param expression the expression
     * @param resultType the result class type
     * @param <T>        the parsed object type
     * @param <R>        the result type
     * @return parse result
     */
    <T, R> R parse(T object, String expression, Class<R> resultType);

}
