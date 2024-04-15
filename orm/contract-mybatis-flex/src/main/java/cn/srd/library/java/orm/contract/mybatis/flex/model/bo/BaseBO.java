package cn.srd.library.java.orm.contract.mybatis.flex.model.bo;

import cn.srd.library.java.orm.contract.model.base.BO;
import cn.srd.library.java.tool.convert.jackson.deserializer.JacksonLongToLocalDateTimeDeserializer;
import cn.srd.library.java.tool.convert.jackson.serializer.JacksonLocalDateTimeToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatisflex.annotation.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BaseBO implements BO {

    @Serial private static final long serialVersionUID = -6744701187675528956L;

    @Column(value = "remark")
    private String remark;

    @Column(value = "creator_id")
    private Long creatorId;

    @Column(value = "updater_id")
    private Long updaterId;

    @Column(value = "create_time")
    @JsonSerialize(using = JacksonLocalDateTimeToLongSerializer.class)
    @JsonDeserialize(converter = JacksonLongToLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @Column(value = "update_time")
    @JsonSerialize(using = JacksonLocalDateTimeToLongSerializer.class)
    @JsonDeserialize(converter = JacksonLongToLocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    @Column(value = "delete_time", isLogicDelete = true)
    private Boolean deleteTime;

}