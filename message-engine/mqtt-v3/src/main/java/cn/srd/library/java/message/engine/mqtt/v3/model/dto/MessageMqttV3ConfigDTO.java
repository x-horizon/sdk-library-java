// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v3.model.dto;

import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
import cn.srd.library.java.message.engine.contract.model.enums.MessageQosType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author wjm
 * @since 2024-06-01 14:34
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MessageMqttV3ConfigDTO extends MessageConfigDTO {

    @Serial private static final long serialVersionUID = 4736521459419470931L;

    private BrokerDTO brokerDTO;

    private List<ProducerDTO> producerDTOs;

    private List<ConsumerDTO> consumerDTOs;

    @JsonIgnore
    private transient Map<Method, ProducerDTO> producerRouter;

    @JsonIgnore
    private transient Map<Method, ConsumerDTO> consumerRouter;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = 7988629260563027098L;

        private String username;

        private String password;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = 1647940393744696107L;

        protected MessageQosType qosType;

        protected String completionTimeout;

        protected String disconnectCompletionTimeout;

        @JsonProperty("mqttV3_completionTimeout")
        protected long originalCompletionTimeout;

        @JsonProperty("mqttV3_disconnectCompletionTimeout")
        protected long originalDisconnectCompletionTimeout;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageConfigDTO.ProducerDTO {

        @Serial private static final long serialVersionUID = 3866458534946916451L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        private String topic;

        private boolean needToSendAsync;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ConsumerDTO implements Serializable {

        @Serial private static final long serialVersionUID = -5886254179329714404L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageConfigDTO.ProducerDTO forwardProducerDTO;

        private List<String> topics;

    }

}