// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.system;

import cn.srd.library.java.contract.constant.system.SystemProperty;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * system toolkit
 *
 * @author wjm
 * @since 2020-01-15 11:02
 */
@SuppressWarnings(SuppressWarningConstant.UNUSED)
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
