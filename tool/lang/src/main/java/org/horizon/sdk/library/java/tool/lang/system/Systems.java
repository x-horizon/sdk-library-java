package org.horizon.sdk.library.java.tool.lang.system;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.contract.constant.system.SystemProperty;

/**
 * system toolkit
 *
 * @author wjm
 * @since 2020-01-15 11:02
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Systems {

    /**
     * return absolute path of the root project，example: /Users/user/Documents/IDE/WorkSpace/project
     *
     * @return absolute path of the root project
     */
    public static String getProjectAbsolutePath() {
        return System.getProperty(SystemProperty.USER_DIR);
    }

    /**
     * return available cpu processors
     *
     * @return available cpu processors
     */
    public static int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

}