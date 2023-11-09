// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.database;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * the data type in database postgresql
 *
 * @author wjm
 * @since 2023-11-06 19:28
 */
@Getter
@AllArgsConstructor
public enum PostgresqlDataType {

    JSONB("jsonb"),

    ;

    private final String value;

}
