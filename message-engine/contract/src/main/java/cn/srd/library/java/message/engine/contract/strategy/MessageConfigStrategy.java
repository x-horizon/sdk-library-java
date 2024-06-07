// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.contract.strategy;

import cn.srd.library.java.message.engine.contract.MessageProducer;
import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;

import java.lang.reflect.Method;

/**
 * @author wjm
 * @since 2024-06-04 11:55
 */
public interface MessageConfigStrategy<C extends MessageConfigDTO> {

    C initialize();

    void onInitializeComplete();

    void registerProducerRouter(Method executeMethod, MessageConfigDTO.ProducerDTO producerDTO);

    MessageConfigDTO.ProducerDTO registerProducer(Method executeMethod, MessageProducer producerAnnotation);

}