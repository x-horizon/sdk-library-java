// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.kafka.model.dto;

import cn.srd.library.java.message.engine.client.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.client.kafka.model.enums.KafkaConsumerAckMode;
import cn.srd.library.java.message.engine.client.kafka.model.enums.KafkaConsumerListenerMode;
import cn.srd.library.java.message.engine.client.kafka.model.enums.KafkaConsumerOffsetResetMode;
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
public class KafkaConfigDTO extends MessageConfigDTO {

    @Serial private static final long serialVersionUID = 1721566725873588079L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = 6478172587588977016L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = 7833921985689603695L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageConfigDTO.ProducerDTO {

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
    public static class ConsumerDTO implements MessageConfigDTO.ConsumerDTO {

        @Serial private static final long serialVersionUID = -3363832601343322864L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageConfigDTO.ProducerDTO forwardProducerDTO;

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