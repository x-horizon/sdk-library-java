package cn.srd.itcp.sugar.component.expression.all.core;

import cn.srd.itcp.sugar.component.expression.spring.core.SpringExpressions;

/**
 * expression operation
 *
 * @author wjm
 * @since 2023-06-08 10:14:52
 */
public class Expressions {

    /**
     * private block constructor
     */
    private Expressions() {
    }

    /**
     * spring implement
     *
     * @return expression operation object
     */
    public static SpringExpressions withSpring() {
        return SpringExpressions.getInstance();
    }

}
