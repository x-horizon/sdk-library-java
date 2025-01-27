package cn.library.java.contract.constant.time;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * time  constant
 *
 * @author wjm
 * @since 2022-11-14 21:16
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeConstant {

    /**
     * the earliest date
     */
    public static final String EARLIEST_DATE = "1970-01-01";

    /**
     * the latest date
     */
    public static final String LATEST_DATE = "9999-12-31";

}