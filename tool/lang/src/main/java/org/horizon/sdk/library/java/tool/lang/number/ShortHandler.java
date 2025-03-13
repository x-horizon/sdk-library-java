package org.horizon.sdk.library.java.tool.lang.number;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.object.Classes;

/**
 * short number handler
 *
 * @author wjm
 * @since 2023-09-21 21:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShortHandler implements NumberHandler {

    /**
     * the singleton instance
     */
    static final ShortHandler INSTANCE = new ShortHandler();

    @Override
    public boolean isAssignable(Class<?> input) {
        return Classes.isAssignable(Short.class, input);
    }

    @Override
    public <T extends Number> Number getValue(T input) {
        return input.shortValue();
    }

}