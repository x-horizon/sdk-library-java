package org.horizon.sdk.library.java.studio.low.code.model.bo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.orm.contract.mybatis.flex.model.bo.BaseWithVersionBO;
import org.horizon.sdk.library.java.studio.low.code.model.enums.SchoolType;
import org.horizon.sdk.library.java.studio.low.code.model.po.SchoolPO;
import org.horizon.sdk.library.java.studio.low.code.model.vo.SchoolVO;
import org.horizon.sdk.library.java.tool.convert.jackson.deserializer.JacksonEnumValueToEnumDeserializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonEnumToIntegerSerializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonLongToStringSerializer;

import java.io.Serial;

/**
 * 学校信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Schema(description = "学校信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = SchoolPO.class), @AutoMapper(target = SchoolVO.class)})
public class SchoolBO extends BaseWithVersionBO {

    @Serial private static final long serialVersionUID = -9052089371242697920L;

    @Schema(description = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "id")
    @Id
    @JsonSerialize(using = JacksonLongToStringSerializer.class)
    private Long id;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    @Column(value = "name")
    private String name;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "type")
    @JsonSerialize(using = JacksonEnumToIntegerSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private SchoolType type;

    @Schema(description = "地址", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    @Column(value = "address")
    private String address;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.BOOLEAN)
    @Column(value = "enable_is")
    private Boolean enableIs;

}