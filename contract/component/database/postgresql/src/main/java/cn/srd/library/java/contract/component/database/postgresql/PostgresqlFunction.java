// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.component.database.postgresql;

import lombok.*;

/**
 * the postgresql jsonb function.
 *
 * @author wjm
 * @since 2024-04-18 20:34
 */
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostgresqlFunction {

    @Builder.Default
    private String jsonbArrayUnnest = "JSONB_ARRAY_ELEMENTS";

    @Builder.Default
    private String jsonbObjectExtract = "JSONB_EXTRACT_PATH";

}