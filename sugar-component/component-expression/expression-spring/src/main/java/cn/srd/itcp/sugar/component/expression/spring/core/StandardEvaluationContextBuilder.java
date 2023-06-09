package cn.srd.itcp.sugar.component.expression.spring.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * {@link StandardEvaluationContext} builder
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StandardEvaluationContextBuilder {

    /**
     * internal {@link StandardEvaluationContext} instance
     */
    private StandardEvaluationContext context;

    /**
     * init self instance with {@link StandardEvaluationContext}
     *
     * @return instance
     */
    public static StandardEvaluationContextBuilder builder() {
        return new StandardEvaluationContextBuilder(new StandardEvaluationContext());
    }

    /**
     * see {@link StandardEvaluationContext#setVariable(String, Object)}
     *
     * @param name  the name of the variable to set
     * @param value the value to be placed in the variable
     * @return self
     */
    public StandardEvaluationContextBuilder setVariable(@Nullable String name, @Nullable Object value) {
        this.context.setVariable(name, value);
        return this;
    }

    /**
     * see {@link StandardEvaluationContext#registerFunction(String, Method)}
     *
     * @param name   the custom method name
     * @param method the method instance
     * @return self
     */
    public StandardEvaluationContextBuilder registerFunction(String name, Method method) {
        this.context.registerFunction(name, method);
        return this;
    }

    /**
     * build {@link StandardEvaluationContext}
     *
     * @return {@link StandardEvaluationContext} instance
     */
    public StandardEvaluationContext build() {
        return this.context;
    }

}
