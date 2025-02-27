package org.horizon.sdk.library.java.tool.spring.contract.support;

import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * spring expression template
 *
 * @author wjm
 * @since 2023-06-08 10:14
 */
public interface ExpressionTemplate extends org.horizon.sdk.library.java.tool.lang.expression.ExpressionTemplate {

    /**
     * parse parameter
     *
     * @param parameterNames  method parameter name
     * @param parameterValues method parameter value
     * @param expression      the expression
     * @return parse result
     */
    default Object parse(String[] parameterNames, Object[] parameterValues, String expression) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        for (int index = 0; index < parameterNames.length; index++) {
            standardEvaluationContext.setVariable(parameterNames[index], parameterValues[index]);
        }
        return parse(standardEvaluationContext, expression);
    }

    /**
     * parse parameter
     *
     * @param parameters      method parameter
     * @param parameterValues method parameter value
     * @param expression      the expression
     * @return parse result
     */
    default Object parse(Parameter[] parameters, Object[] parameterValues, String expression) {
        return parse(Arrays.stream(parameters).map(Parameter::getName).toArray(String[]::new), parameterValues, expression);
    }

    /**
     * see {@link #parse(Parameter[], Object[], String)}
     *
     * @param parameters      method parameter
     * @param parameterValues method parameter value
     * @param expression      the expression
     * @param resultType      the result class type
     * @param <R>             the result type
     * @return parse result
     */
    default <R> R parse(Parameter[] parameters, Object[] parameterValues, String expression, Class<R> resultType) {
        return resultType.cast(parse(parameters, parameterValues, expression));
    }

    /**
     * parse {@link StandardEvaluationContext}
     *
     * @param context    {@link StandardEvaluationContext}
     * @param expression the expression
     * @return parse result
     */
    Object parse(StandardEvaluationContext context, String expression);

    /**
     * see {@link #parse(StandardEvaluationContext, String)}
     *
     * @param context    {@link StandardEvaluationContext}
     * @param expression the expression
     * @param resultType the result class type
     * @param <R>        the result type
     * @return parse result
     */
    default <R> R parse(StandardEvaluationContext context, String expression, Class<R> resultType) {
        return resultType.cast(parse(context, expression));
    }

}