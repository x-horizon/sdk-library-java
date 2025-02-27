package org.horizon.sdk.library.java.message.engine.client.rocketmq.model.dto;

import org.horizon.sdk.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
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
public class RocketMqClientConfigDTO extends MessageClientConfigDTO {

    @Serial private static final long serialVersionUID = -8383847948889418567L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageClientConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = 9064152407080103059L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageClientConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = 7177174287471719319L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageClientConfigDTO.ProducerDTO {

        @Serial private static final long serialVersionUID = 4613932739517443446L;

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

        @Serial private static final long serialVersionUID = -4471032586211381158L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageClientConfigDTO.ProducerDTO forwardProducerDTO;

        private List<String> topics;

    }

}