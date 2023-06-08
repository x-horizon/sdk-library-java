package cn.srd.itcp.sugar.component.expression.spring.core;

import cn.srd.itcp.sugar.component.expression.contract.core.ExpressionTemplate;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
public interface SpringExpressionTemplate extends ExpressionTemplate {

    /**
     * parse {@link StandardEvaluationContext}
     *
     * @param context    {@link StandardEvaluationContext}
     * @param expression the expression
     * @param resultType the result class type
     * @param <R>        the result type
     * @return parse result
     */
    <R> R parse(StandardEvaluationContext context, String expression, Class<R> resultType);

}
