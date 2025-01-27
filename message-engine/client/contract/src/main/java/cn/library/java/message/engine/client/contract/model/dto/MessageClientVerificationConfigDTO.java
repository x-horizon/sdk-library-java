package cn.library.java.message.engine.client.contract.model.dto;

import cn.library.java.message.engine.client.contract.model.enums.MessageClientType;
import cn.library.java.tool.lang.collection.Collections;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author wjm
 * @since 2024-06-11 17:03
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MessageClientVerificationConfigDTO implements Serializable {

    @Serial private static final long serialVersionUID = -7031990249260811383L;

    @JsonIgnore
    private boolean verifyPassed;

    private MessageClientType engineType;

    @Builder.Default
    private Map<String, String> brokerFailedReason = Collections.newHashMap();

    @Builder.Default
    private List<ProducerDTO> producerFailedReasons = Collections.newArrayList();

    @Builder.Default
    private List<ConsumerDTO> consumerFailedReasons = Collections.newArrayList();

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ProducerDTO implements Serializable {

        @Serial private static final long serialVersionUID = -3434584165056712928L;

        protected String methodPoint;

        @Builder.Default
        private Map<String, String> failedReason = Collections.newHashMap();

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ConsumerDTO implements Serializable {

        @Serial private static final long serialVersionUID = -2857065800889023157L;

        protected String methodPoint;

        @Builder.Default
        private Map<String, String> failedReason = Collections.newHashMap();

    }

}