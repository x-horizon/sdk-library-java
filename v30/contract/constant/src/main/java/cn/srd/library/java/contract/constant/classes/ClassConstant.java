// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.classes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * class constant
 *
 * @author wjm
 * @since 2023-10-09 09:11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassConstant {

    public static final String CLASS_PATH_PREFIX = "classpath*:";

    public static final String JAVA_MAIN_PATH = "/java/main/";

    public static final String JAVA_TEST_PATH = "/java/test/";

}
