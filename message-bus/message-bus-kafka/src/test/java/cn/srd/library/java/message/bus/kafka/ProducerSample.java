package cn.srd.library.java.message.bus.kafka;

import cn.srd.library.java.message.bus.kafka.core.StreamBridgeManager;
import org.springframework.stereotype.Component;

@Component
public class ProducerSample {

    public void produce() {
        StreamBridgeManager.get().send("produce-out-0", "333");
    }

}
