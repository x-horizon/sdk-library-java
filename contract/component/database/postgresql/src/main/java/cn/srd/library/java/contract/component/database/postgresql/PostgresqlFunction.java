// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.component.database.postgresql;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * the postgresql jsonb function.
 *
 * @author wjm
 * @since 2024-04-18 20:34
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostgresqlFunction {

    public static final String JSONB_OBJECT_EXTRACT = "JSONB_EXTRACT_PATH";

    public static final String JSONB_OBJECT_EXTRACT_APPENDER = JSONB_OBJECT_EXTRACT + "({}, {})";

    public static final String JSONB_ARRAY_UNNEST = "JSONB_ARRAY_ELEMENTS";

    public static final String JSONB_ARRAY_UNNEST_APPENDER = JSONB_ARRAY_UNNEST + "({})";

}