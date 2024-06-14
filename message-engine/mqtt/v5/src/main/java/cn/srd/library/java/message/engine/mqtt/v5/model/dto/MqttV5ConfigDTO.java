// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.mqtt.v5.model.dto;

import cn.srd.library.java.message.engine.contract.model.dto.MessageConfigDTO;
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
public class MqttV5ConfigDTO extends MessageConfigDTO {

    @Serial private static final long serialVersionUID = -2724372746800727500L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = -4800363923662405494L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = -7062379549884979698L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageConfigDTO.ProducerDTO {

        @Serial private static final long serialVersionUID = -4450021328129932093L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        private String topic;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ConsumerDTO implements MessageConfigDTO.ConsumerDTO {

        @Serial private static final long serialVersionUID = 3688570547157125372L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageConfigDTO.ProducerDTO forwardProducerDTO;

        private List<String> topics;

    }

}