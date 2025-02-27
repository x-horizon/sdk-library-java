package org.horizon.sdk.library.java.message.engine.client.kafka.model.dto;

import org.horizon.sdk.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.enums.KafkaConsumerAckMode;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.enums.KafkaConsumerListenerMode;
import org.horizon.sdk.library.java.message.engine.client.kafka.model.enums.KafkaConsumerOffsetResetMode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.listener.ContainerProperties;

import java.io.Serial;
import java.util.List;

/**
 * @author wjm
 * @since 2024-06-01 09:57
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class KafkaClientConfigDTO extends MessageClientConfigDTO {

    @Serial private static final long serialVersionUID = 1721566725873588079L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageClientConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = 6478172587588977016L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageClientConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = 7833921985689603695L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageClientConfigDTO.ProducerDTO {

        @Serial private static final long serialVersionUID = -7957770669868555274L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        private String topic;

        private Boolean dynamicIs;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ConsumerDTO implements MessageClientConfigDTO.ConsumerDTO {

        @Serial private static final long serialVersionUID = -3363832601343322864L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageClientConfigDTO.ProducerDTO forwardProducerDTO;

        private String groupId;

        private List<String> topics;

        private boolean allowToAutoCreateTopic;

        private KafkaConsumerAckMode ackMode;

        private String autoCommitOffsetInterval;

        private KafkaConsumerListenerMode listenerMode;

        private KafkaConsumerOffsetResetMode offsetResetMode;

        @JsonProperty("kafka_auto.offset.reset")
        private String nativeAutoOffsetReset;

        @JsonProperty("kafka_group.id")
        private String nativeGroupId;

        @JsonProperty("kafka_allow.auto.create.topics")
        private String nativeAllowAutoCreateTopics;

        @JsonProperty("kafka_listenerMode")
        private KafkaMessageDrivenChannelAdapter.ListenerMode nativeListenerMode;

        @JsonProperty("kafka_ackMode")
        private ContainerProperties.AckMode nativeAckMode;

        @JsonProperty("kafka_enable.auto.commit")
        private String nativeEnableAutoCommit;

        @JsonProperty("kafka_auto.commit.interval.ms")
        private String nativeAutoCommitIntervalMs;

    }

}