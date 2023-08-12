package cn.srd.library.tool.expression.spring.core;

import cn.srd.library.contract.tool.expression.core.ExpressionTemplate;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Parameter;

/**
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
public interface SpringExpressionTemplate extends ExpressionTemplate {

    /**
     * parse parameter
     *
     * @param parameters      method parameter
     * @param parameterValues method parameter value
     * @param expression      the expression
     * @return parse result
     */
    default Object parse(Parameter[] parameters, Object[] parameterValues, String expression) {
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        for (int index = 0; index < parameters.length; index++) {
            standardEvaluationContext.setVariable(parameters[index].getName(), parameterValues[index]);
        }
        return parse(standardEvaluationContext, expression);
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
