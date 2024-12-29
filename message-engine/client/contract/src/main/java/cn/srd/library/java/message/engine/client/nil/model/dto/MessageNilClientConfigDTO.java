// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.message.engine.client.nil.model.dto;

import cn.srd.library.java.message.engine.client.contract.model.dto.MessageClientConfigDTO;
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
public class MessageNilClientConfigDTO extends MessageClientConfigDTO {

    @Serial private static final long serialVersionUID = 7986310374819427415L;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class BrokerDTO extends MessageClientConfigDTO.BrokerDTO {

        @Serial private static final long serialVersionUID = 5749649504672362588L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    public static class ClientDTO extends MessageClientConfigDTO.ClientDTO {

        @Serial private static final long serialVersionUID = 7457629948698791618L;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements MessageClientConfigDTO.ProducerDTO {

        @Serial private static final long serialVersionUID = 4935884918270194774L;

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

        @Serial private static final long serialVersionUID = -449994617235972579L;

        @JsonProperty("clientInfo")
        private ClientDTO clientDTO;

        @JsonProperty("forwardProducerInfo")
        private MessageClientConfigDTO.ProducerDTO forwardProducerDTO;

        private List<String> topics;

    }

}