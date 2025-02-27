package org.horizon.sdk.library.java.tool.lang.booleans;

import org.horizon.sdk.library.java.tool.lang.object.Nil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * toolkit for boolean
 *
 * @author wjm
 * @since 2022-08-11 10:59
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Booleans {

    /**
     * return true if the checked element is true
     *
     * @param input the checked element
     * @return return true if the checked element is true
     */
    public static boolean isTrue(Boolean input) {
        return Nil.isNotNull(input) && input;
    }

    /**
     * return true if the checked element is false
     *
     * @param input the checked element
     * @return return true if the checked element is false
     */
    public static boolean isFalse(Boolean input) {
        return Nil.isNotNull(input) && !input;
    }

    /**
     * return true if any checked element is true
     *
     * @param inputs the checked elements
     * @return return true if any checked element is true
     */
    public static boolean isAnyTrue(Boolean... inputs) {
        if (Nil.isNull(inputs)) {
            return false;
        }
        for (Boolean input : inputs) {
            if (isTrue(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if any checked element is false
     *
     * @param inputs the checked elements
     * @return return true if any checked element is false
     */
    public static boolean isAnyFalse(Boolean... inputs) {
        if (Nil.isNull(inputs)) {
            return false;
        }
        for (Boolean input : inputs) {
            if (isFalse(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return true if all checked elements are true
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are true
     */
    public static boolean isAllTrue(Boolean... inputs) {
        if (Nil.isNull(inputs)) {
            return false;
        }
        for (Boolean input : inputs) {
            if (isFalse(input)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return true if all checked elements are false
     *
     * @param inputs the checked elements
     * @return return true if all checked elements are false
     */
    public static boolean isAllFalse(Boolean... inputs) {
        if (Nil.isNull(inputs)) {
            return false;
        }
        for (Boolean input : inputs) {
            if (isTrue(input)) {
                return false;
            }
        }
        return true;
    }

}