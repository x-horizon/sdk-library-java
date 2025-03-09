package org.horizon.sdk.library.java.tool.lang.number;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.lang.object.Classes;

/**
 * byte number handler
 *
 * @author wjm
 * @since 2023-09-21 21:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ByteHandler implements NumberHandler {

    /**
     * the singleton instance
     */
    static final ByteHandler INSTANCE = new ByteHandler();

    @Override
    public boolean isAssignable(Class<?> input) {
        return Classes.isAssignable(Byte.class, input);
    }

    @Override
    public <T extends Number> Number getValue(T input) {
        return input.byteValue();
    }

}