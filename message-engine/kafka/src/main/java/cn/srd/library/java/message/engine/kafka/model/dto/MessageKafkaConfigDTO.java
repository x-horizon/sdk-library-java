// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.model.dto;

import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerListenerMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerOffsetResetMode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.listener.ContainerProperties;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author wjm
 * @since 2024-06-01 09:57
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MessageKafkaConfigDTO extends MessageConfigDTO {

    @Serial private static final long serialVersionUID = 1721566725873588079L;

    private BrokerDTO brokerDTO;

    private List<ProducerDTO> producerDTOs;

    private List<ConsumerDTO> consumerDTOs;

    @JsonIgnore
    private Map<Method, ProducerDTO> producerRouters;

    @JsonIgnore
    private Map<Method, ConsumerDTO> consumerRouters;

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
    public static class ProducerDTO implements Serializable {

        @Serial private static final long serialVersionUID = -7957770669868555274L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        private String topic;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ConsumerDTO implements Serializable {

        @Serial private static final long serialVersionUID = -3363832601343322864L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        private ProducerDTO forwardProducerDTO;

        private String groupId;

        private List<String> topics;

        private boolean allowToAutoCreateTopic;

        private MessageKafkaConsumerAckMode ackMode;

        private String autoCommitOffsetInterval;

        private MessageKafkaConsumerListenerMode listenerMode;

        private MessageKafkaConsumerOffsetResetMode offsetResetMode;

        @JsonProperty("kafka_auto.offset.reset")
        private String originalAutoOffsetReset;

        @JsonProperty("kafka_group.id")
        private String originalGroupId;

        @JsonProperty("kafka_allow.auto.create.topics")
        private String originalAllowAutoCreateTopics;

        @JsonProperty("kafka_listenerMode")
        private KafkaMessageDrivenChannelAdapter.ListenerMode originalListenerMode;

        @JsonProperty("kafka_ackMode")
        private ContainerProperties.AckMode originalAckMode;

        @JsonProperty("kafka_enable.auto.commit")
        private String originalEnableAutoCommit;

        @JsonProperty("kafka_auto.commit.interval.ms")
        private String originalAutoCommitIntervalMs;

    }

}