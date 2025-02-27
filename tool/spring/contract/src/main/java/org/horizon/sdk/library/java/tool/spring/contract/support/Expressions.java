package org.horizon.sdk.library.java.tool.spring.contract.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * spring expression operation
 *
 * @author wjm
 * @since 2023-06-08 10:14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Expressions implements ExpressionTemplate {

    /**
     * singleton pattern
     */
    private static final class SingleTonHolder {

        private static final Expressions INSTANCE = new Expressions();

        private static final ExpressionParser EXPRESSION_INSTANCE = new SpelExpressionParser();

    }

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static Expressions getInstance() {
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
    public Object parse(String expression) {
        return getExpressionInstance().parseExpression(expression).getValue();
    }

    @Override
    public <T> Object parse(T object, String expression) {
        return getExpressionInstance().parseExpression(expression).getValue(object);
    }

    @Override
    public Object parse(StandardEvaluationContext context, String expression) {
        return getExpressionInstance().parseExpression(expression).getValue(context);
    }

}