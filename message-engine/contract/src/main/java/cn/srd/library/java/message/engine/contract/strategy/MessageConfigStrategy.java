// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.strategy;

import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;

/**
 * @author wjm
 * @since 2024-06-04 11:55
 */
public interface MessageConfigStrategy<T extends MessageConfigDTO> {

    T customize();

}