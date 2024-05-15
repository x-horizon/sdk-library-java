// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.kafka;

import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.spring.contract.Springs;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;

/**
 * {@link StreamBridge} Manager
 *
 * @author wjm
 * @since 2023-03-25 16:53
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamBridgeManager {

    /**
     * see {@link StreamBridge}
     */
    private static StreamBridge streamBridge;

    /**
     * 获取 {@link StreamBridge}
     *
     * @return {@link StreamBridge}
     */
    public static StreamBridge get() {
        if (Nil.isNull(streamBridge)) {
            streamBridge = Springs.getBean(StreamBridge.class);
        }
        return streamBridge;
    }

}