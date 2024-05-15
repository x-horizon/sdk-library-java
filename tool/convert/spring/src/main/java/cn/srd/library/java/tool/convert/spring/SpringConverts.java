// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.spring;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * convert toolkit for spring
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringConverts {

    /**
     * singleton pattern
     */
    private static final SpringConverts INSTANCE = new SpringConverts();

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static SpringConverts getInstance() {
        return INSTANCE;
    }

}