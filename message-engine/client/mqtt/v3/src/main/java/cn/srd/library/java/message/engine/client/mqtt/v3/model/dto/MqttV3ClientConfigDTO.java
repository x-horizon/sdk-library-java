package cn.srd.library.java.message.engine.client.mqtt.v3.model.dto;

import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
import cn.srd.library.java.message.engine.client.contract.model.enums.MessageQosType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

/**
 * @author wjm
 * @since 2024-06-01 14:34
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MqttV3ClientConfigDTO extends MessageClientConfigDTO {

    @Serial private static final long serialVersionUID = 4736521459419470931L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageClientConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = 7988629260563027098L;

        private String username;

        private String password;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageClientConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = 1647940393744696107L;

        protected MessageQosType qosType;

        protected String completionTimeout;

        protected String disconnectCompletionTimeout;

        @JsonProperty("mqttV3_completionTimeout")
        protected long nativeCompletionTimeout;

        @JsonProperty("mqttV3_disconnectCompletionTimeout")
        protected long nativeDisconnectCompletionTimeout;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageClientConfigDTO.ProducerDTO {

        @Serial private static final long serialVersionUID = 3866458534946916451L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        private String topic;

        private Boolean dynamicIs;

        private boolean needToSendAsync;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ConsumerDTO implements MessageClientConfigDTO.ConsumerDTO {

        @Serial private static final long serialVersionUID = -5886254179329714404L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageClientConfigDTO.ProducerDTO forwardProducerDTO;

        private List<String> topics;

    }

}