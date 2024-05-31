// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.kafka.model;

import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerAckMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerListenerMode;
import cn.srd.library.java.message.engine.kafka.model.enums.MessageKafkaConsumerOffsetResetMode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.List;

/**
 * @author wjm
 * @since 2024-05-31 16:41
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class MessageKafkaConsumerConfig {

    private String consumerPointer;

    private String groupId;

    private List<String> topics;

    private boolean allowToAutoCreateTopic;

    private MessageKafkaConsumerAckMode ackMode;

    private String autoCommitOffsetInterval;

    private MessageKafkaConsumerListenerMode listenerMode;

    private MessageKafkaConsumerOffsetResetMode offsetResetMode;

    @JsonProperty("kafka_auto.offset.reset")
    private String kafkaAutoOffsetReset;

    @JsonProperty("kafka_group.id")
    private String kafkaGroupId;

    @JsonProperty("kafka_allow.auto.create.topics")
    private String kafkaAllowAutoCreateTopics;

    @JsonProperty("kafka_listenerMode")
    private KafkaMessageDrivenChannelAdapter.ListenerMode kafkaListenerMode;

    @JsonProperty("kafka_ackMode")
    private ContainerProperties.AckMode kafkaAckMode;

    @JsonProperty("kafka_enable.auto.commit")
    private String kafkaEnableAutoCommit;

    @JsonProperty("kafka_auto.commit.interval.ms")
    private String kafkaAutoCommitIntervalMs;

}