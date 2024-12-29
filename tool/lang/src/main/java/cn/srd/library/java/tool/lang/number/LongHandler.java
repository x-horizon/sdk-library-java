package cn.srd.library.java.tool.lang.number;

import cn.srd.library.java.tool.lang.object.Classes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * long number handler
 *
 * @author wjm
 * @since 2023-09-21 21:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LongHandler implements NumberHandler {

    /**
     * the singleton instance
     */
    static final LongHandler INSTANCE = new LongHandler();

    @Override
    public boolean isAssignable(Class<?> input) {
        return Classes.isAssignable(Long.class, input);
    }

    @Override
    public <T extends Number> Number getValue(T input) {
        return input.longValue();
    }

}