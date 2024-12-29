package cn.srd.library.java.message.engine.client.kafka.model.enums;

import cn.srd.library.java.tool.lang.convert.Converts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;

/**
 * @author wjm
 * @since 2024-05-31 17:42
 */
@Getter
@AllArgsConstructor
public enum KafkaConsumerAdapterListenerMode {

    RECORD(KafkaConsumerListenerMode.RECORD.getCode(), KafkaMessageDrivenChannelAdapter.ListenerMode.record),
    BATCH(KafkaConsumerListenerMode.BATCH.getCode(), KafkaMessageDrivenChannelAdapter.ListenerMode.batch),

    ;

    private final int listenerModeCode;

    private final KafkaMessageDrivenChannelAdapter.ListenerMode kafkaListenerMode;

    public static KafkaConsumerAdapterListenerMode fromListenerMode(KafkaConsumerListenerMode listenerMode) {
        return Converts.toEnumByValue(listenerMode.getCode(), KafkaConsumerAdapterListenerMode.class);
    }

}