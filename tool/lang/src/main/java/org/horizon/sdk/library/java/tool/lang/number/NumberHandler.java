package org.horizon.sdk.library.java.tool.lang.number;

/**
 * number handler
 *
 * @author wjm
 * @since 2023-09-21 21:36
 */
public interface NumberHandler {

    /**
     * check the input element is assignable from {@link Number} subclass
     *
     * @param input the input element
     * @return return true if the input element is assignable from {@link Number} subclass
     */
    boolean isAssignable(Class<?> input);

    /**
     * return the actual number value
     *
     * @param input the input element
     * @param <T>   the actual number type
     * @return the actual number value
     */
    <T extends Number> Number getValue(T input);

}