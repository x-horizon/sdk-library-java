package org.horizon.sdk.library.java.tool.lang.number;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.object.Classes;

/**
 * float number handler
 *
 * @author wjm
 * @since 2023-09-21 21:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FloatHandler implements NumberHandler {

    /**
     * the singleton instance
     */
    static final FloatHandler INSTANCE = new FloatHandler();

    @Override
    public boolean isAssignable(Class<?> input) {
        return Classes.isAssignable(Float.class, input);
    }

    @Override
    public <T extends Number> Number getValue(T input) {
        return input.floatValue();
    }

}