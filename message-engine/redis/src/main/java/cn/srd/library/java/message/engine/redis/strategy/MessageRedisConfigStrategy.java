// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.redis.strategy;

import cn.srd.library.java.message.engine.contract.strategy.MessageConfigStrategy;
import cn.srd.library.java.message.engine.redis.model.dto.MessageRedisConfigDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wjm
 * @since 2024-06-04 17:10
 */
@Slf4j
public class MessageRedisConfigStrategy implements MessageConfigStrategy<MessageRedisConfigDTO> {

    @Override
    public MessageRedisConfigDTO customize() {
        return MessageRedisConfigDTO.builder().build();
    }

}