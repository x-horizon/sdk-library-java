// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.doc.knife4j.contract.constant;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wjm
 * @since 2024-04-16 23:39
 */
@SuppressWarnings(SuppressWarningConstant.UNUSED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiDocConstant {

    public static final String BOOLEAN = "true";

    public static final String NUMBER = "1";

    public static final String STRING = "example-string";

    public static final String TIMESTAMP = "1713341740";

    public static final String LIST_NUMBER = "[1, 2]";

    public static final String LIST_STRING = "[example-string1, example-string2]";

}