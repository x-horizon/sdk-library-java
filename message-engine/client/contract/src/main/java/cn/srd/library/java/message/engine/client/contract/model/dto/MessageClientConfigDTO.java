package cn.srd.library.java.message.engine.client.contract.model.dto;

import cn.srd.library.java.message.engine.client.contract.model.enums.MessageClientIdGenerateType;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
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
 * @since 2024-06-01 17:56
 */
@Data
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MessageClientConfigDTO implements Serializable {

    @Serial private static final long serialVersionUID = 8893739317361752384L;

    private BrokerDTO brokerDTO;

    private List<? extends ProducerDTO> producerDTOs;

    private List<? extends ConsumerDTO> consumerDTOs;

    @JsonIgnore
    @Builder.Default
    private Map<String, ProducerDTO> dynamicProducerRouter = Collections.newConcurrentHashMap();

    @JsonIgnore
    private Map<Method, ? extends ProducerDTO> producerRouter;

    @JsonIgnore
    private Map<Method, ? extends ConsumerDTO> consumerRouter;

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class BrokerDTO implements Serializable {

        @Serial private static final long serialVersionUID = -5861291771843201177L;

        protected List<String> serverUrls;

    }

    @Data
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    @NoArgsConstructor
    public static class ClientDTO implements Serializable {

        @Serial private static final long serialVersionUID = 5434779839371423102L;

        protected String clientId;

        protected String flowId;

        protected MessageClientIdGenerateType idGenerateType;

        @JsonIgnore
        protected transient Method executeMethod;

    }

    public interface ProducerDTO extends Serializable {

        ClientDTO getClientDTO();

        String getTopic();

        Boolean getDynamicIs();

        ProducerDTO setTopic(String topic);

    }

    public interface ConsumerDTO extends Serializable {

        ClientDTO getClientDTO();

        MessageClientConfigDTO.ProducerDTO getForwardProducerDTO();

        List<String> getTopics();

    }

}