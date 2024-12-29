// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.contract.strategy;

import cn.srd.library.java.tool.id.uuid.support.UUIDs;

/**
 * @author wjm
 * @since 2024-05-30 10:54
 */
public class UniqueClientIdGenerateByUUIDStrategy implements UniqueClientIdGenerateStrategy<String> {

    @Override
    public String getId() {
        return UUIDs.fastGet();
    }

}