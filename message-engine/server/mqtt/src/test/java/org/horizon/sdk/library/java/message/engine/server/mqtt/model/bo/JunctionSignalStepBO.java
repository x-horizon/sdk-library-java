package org.horizon.sdk.library.java.message.engine.server.mqtt.model.bo;

import org.horizon.sdk.library.java.contract.model.base.BO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.message.engine.server.mqtt.model.po.JunctionSignalStepPO;
import org.horizon.sdk.library.java.tool.convert.jackson.deserializer.JacksonLongToLocalDateTimeDeserializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonLocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2025-01-12 19:15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AutoMappers({@AutoMapper(target = JunctionSignalStepPO.class)})
public class JunctionSignalStepBO implements BO {

    @Serial private static final long serialVersionUID = 7504337795223580355L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "id")
    @Id
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.TIMESTAMP)
    @Column(value = "create_time")
    @JsonSerialize(using = JacksonLocalDateTimeToLongSerializer.class)
    @JsonDeserialize(converter = JacksonLongToLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

}