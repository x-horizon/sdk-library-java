// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.enums;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * enum constant
 *
 * @author wjm
 * @since 2023-09-19 19:44
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumConstant {

    /**
     * enum internal field name
     */
    public static final Set<String> INTERNAL_FIELD_NAMES = Set.of("ENUM$VALUES", "$VALUES", "name", "ordinal");

}
