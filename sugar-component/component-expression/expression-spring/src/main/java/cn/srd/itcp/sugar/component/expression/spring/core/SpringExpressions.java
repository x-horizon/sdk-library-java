package cn.srd.itcp.sugar.component.expression.spring.core;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * spring expression operation
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
public class SpringExpressions implements SpringExpressionTemplate {

    /**
     * private block constructor
     */
    private SpringExpressions() {
    }

    /**
     * singleton pattern
     */
    private static final class SingleTonHolder {
        private static final SpringExpressions INSTANCE = new SpringExpressions();
        private static final ExpressionParser EXPRESSION_INSTANCE = new SpelExpressionParser();
    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static SpringExpressions getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    /**
     * get singleton {@link ExpressionParser} instance
     *
     * @return {@link ExpressionParser} instance
     */
    private static ExpressionParser getExpressionInstance() {
        return SingleTonHolder.EXPRESSION_INSTANCE;
    }

    @Override
    public <R> R parse(String expression, Class<R> resultType) {
        return getExpressionInstance().parseExpression(expression).getValue(resultType);
    }

    @Override
    public <T, R> R parse(T object, String expression, Class<R> resultType) {
        return getExpressionInstance().parseExpression(expression).getValue(object, resultType);
    }

    @Override
    public <R> R parse(StandardEvaluationContext context, String expression, Class<R> resultType) {
        return getExpressionInstance().parseExpression(expression).getValue(context, resultType);
    }

}
