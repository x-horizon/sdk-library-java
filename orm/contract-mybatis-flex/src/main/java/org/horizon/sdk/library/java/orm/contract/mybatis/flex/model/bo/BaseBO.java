package org.horizon.sdk.library.java.orm.contract.mybatis.flex.model.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatisflex.annotation.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.constant.number.NumberConstant;
import org.horizon.sdk.library.java.contract.constant.text.SymbolConstant;
import org.horizon.sdk.library.java.contract.model.base.BO;
import org.horizon.sdk.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import org.horizon.sdk.library.java.tool.convert.jackson.deserializer.JacksonLongToLocalDateTimeDeserializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonLocalDateTimeToLongSerializer;
import org.horizon.sdk.library.java.tool.convert.jackson.serializer.JacksonLongToStringSerializer;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
@Schema(description = "基础信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BaseBO implements BO {

    @Serial private static final long serialVersionUID = -6744701187675528956L;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    @Column(value = "remark")
    @Builder.Default
    private String remark = SymbolConstant.EMPTY;

    @Schema(description = "创建人id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "creator_id")
    @Builder.Default
    @JsonSerialize(using = JacksonLongToStringSerializer.class)
    private Long creatorId = NumberConstant.ZERO_LONG_VALUE;

    @Schema(description = "创建人名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    @Column(value = "creator_name")
    @Builder.Default
    private String creatorName = SymbolConstant.EMPTY;

    @Schema(description = "更新人id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.NUMBER)
    @Column(value = "updater_id")
    @Builder.Default
    @JsonSerialize(using = JacksonLongToStringSerializer.class)
    private Long updaterId = NumberConstant.ZERO_LONG_VALUE;

    @Schema(description = "更新人名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    @Column(value = "updater_name")
    @Builder.Default
    private String updaterName = SymbolConstant.EMPTY;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.TIMESTAMP)
    @Column(value = "create_time")
    @JsonSerialize(using = JacksonLocalDateTimeToLongSerializer.class)
    @JsonDeserialize(using = JacksonLongToLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.TIMESTAMP)
    @Column(value = "update_time")
    @JsonSerialize(using = JacksonLocalDateTimeToLongSerializer.class)
    @JsonDeserialize(using = JacksonLongToLocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    @Schema(description = "删除时间")
    @Column(value = "delete_time", isLogicDelete = true)
    @JsonIgnore
    private LocalDateTime deleteTime;

}