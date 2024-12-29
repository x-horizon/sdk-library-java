package cn.srd.library.java.contract.constant.spring;

import cn.srd.library.java.contract.constant.number.NumberConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * spring initialize constant
 *
 * @author wjm
 * @since 2023-10-15 19:51
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringInitializeConstant {

    public static final int HIGHEST_INITIALIZE_PRIORITY = NumberConstant.MIN_INT_VALUE;

    public static final int HIGHER_INITIALIZE_PRIORITY = HIGHEST_INITIALIZE_PRIORITY + 1;

    public static final int HIGH_INITIALIZE_PRIORITY = HIGHER_INITIALIZE_PRIORITY + 1;

    public static final int LOWEST_INITIALIZE_PRIORITY = NumberConstant.MAX_INT_VALUE;

    public static final int LOWER_INITIALIZE_PRIORITY = LOWEST_INITIALIZE_PRIORITY - 1;

    public static final int LOW_INITIALIZE_PRIORITY = LOWER_INITIALIZE_PRIORITY - 1;

}