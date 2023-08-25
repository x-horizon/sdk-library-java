package cn.srd.library.java.orm.mybatis.flex.model.po;

import cn.srd.library.java.tool.convert.jackson.support.JacksonLocalDateTimeToStringRFC3339Serializer;
import cn.srd.library.java.tool.convert.jackson.support.JacksonStringToLocalDateTimeRFC3339Deserializer;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "基础持久化模型")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BasePO implements Serializable {

    @Serial private static final long serialVersionUID = -3271765063980344314L;

    @Schema(description = "版本号")
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT)
    private Integer version;

    @Schema(description = "备注", example = "备注信息")
    @TableField(value = "remark")
    private String remark;

    @Schema(description = "创建人id")
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Long creatorId;

    @Schema(description = "创建人名字")
    @TableField(value = "creator_name", fill = FieldFill.INSERT)
    private String creatorName;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonSerialize(using = JacksonLocalDateTimeToStringRFC3339Serializer.class)
    @JsonDeserialize(converter = JacksonStringToLocalDateTimeRFC3339Deserializer.class)
    private LocalDateTime createTime;

    @Schema(description = "更新人id")
    @TableField(value = "updater_id", fill = FieldFill.INSERT_UPDATE)
    private Long updaterId;

    @Schema(description = "更新人名字")
    @TableField(value = "updater_name", fill = FieldFill.INSERT_UPDATE)
    private String updaterName;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = JacksonLocalDateTimeToStringRFC3339Serializer.class)
    @JsonDeserialize(converter = JacksonStringToLocalDateTimeRFC3339Deserializer.class)
    private LocalDateTime updateTime;

    @Schema(description = "是否已删除")
    @TableLogic
    @TableField(value = "row_is_delete", fill = FieldFill.INSERT)
    private Boolean rowIsDelete;

}
