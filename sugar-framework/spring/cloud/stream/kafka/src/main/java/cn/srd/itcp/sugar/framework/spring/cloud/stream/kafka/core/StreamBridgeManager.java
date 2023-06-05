package cn.srd.itcp.sugar.framework.spring.cloud.stream.kafka.core;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import org.springframework.cloud.stream.function.StreamBridge;

/**
 * {@link StreamBridge} Manager
 *
 * @author wjm
 * @since 2023-03-25 16:53:22
 */
public class StreamBridgeManager {

    /**
     * private block constructor
     */
    private StreamBridgeManager() {
    }

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
