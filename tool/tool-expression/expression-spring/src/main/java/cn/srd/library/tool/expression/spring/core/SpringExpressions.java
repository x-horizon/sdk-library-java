package cn.srd.library.tool.expression.spring.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * spring expression operation
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringExpressions implements SpringExpressionTemplate {

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
