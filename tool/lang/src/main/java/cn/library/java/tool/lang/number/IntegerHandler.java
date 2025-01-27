package cn.library.java.tool.lang.number;

import cn.library.java.tool.lang.object.Classes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * integer number handler
 *
 * @author wjm
 * @since 2023-09-21 21:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntegerHandler implements NumberHandler {

    /**
     * the singleton instance
     */
    static final IntegerHandler INSTANCE = new IntegerHandler();

    @Override
    public boolean isAssignable(Class<?> input) {
        return Classes.isAssignable(Integer.class, input);
    }

    @Override
    public <T extends Number> Number getValue(T input) {
        return input.intValue();
    }

}