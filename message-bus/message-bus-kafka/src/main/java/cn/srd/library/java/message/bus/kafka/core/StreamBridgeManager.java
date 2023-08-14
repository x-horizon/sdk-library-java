package cn.srd.library.java.message.bus.kafka.core;

import cn.srd.library.java.tool.lang.core.object.Objects;
import cn.srd.library.java.tool.spring.common.core.SpringsUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;

/**
 * {@link StreamBridge} Manager
 *
 * @author wjm
 * @since 2023-03-25 16:53:22
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
        if (Objects.isNull(streamBridge)) {
            streamBridge = SpringsUtil.getBean(StreamBridge.class);
        }
        return streamBridge;
    }

}
