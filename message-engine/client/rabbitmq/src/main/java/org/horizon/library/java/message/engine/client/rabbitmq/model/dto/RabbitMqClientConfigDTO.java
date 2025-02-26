package org.horizon.library.java.message.engine.client.rabbitmq.model.dto;

import org.horizon.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
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
public class RabbitMqClientConfigDTO extends MessageClientConfigDTO {

    @Serial private static final long serialVersionUID = 7663509272022032010L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageClientConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = 7562463634776198698L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageClientConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = -3261367951861305869L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageClientConfigDTO.ProducerDTO {

        @Serial private static final long serialVersionUID = 1777294829702718096L;

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

        @Serial private static final long serialVersionUID = 5051387101298720688L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageClientConfigDTO.ProducerDTO forwardProducerDTO;

        private List<String> topics;

    }

}