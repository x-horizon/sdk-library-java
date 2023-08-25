package cn.srd.library.java.orm.mybatis.flex.model.po;

import cn.srd.library.java.tool.convert.jackson.support.JacksonLocalDateTimeToStringRFC3339Serializer;
import cn.srd.library.java.tool.convert.jackson.support.JacksonStringToLocalDateTimeRFC3339Deserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mybatisflex.annotation.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BasePO implements Serializable {

    @Serial private static final long serialVersionUID = 7657638461455071443L;

    @Column(value = "version")
    private Integer version;

    @Column(value = "creator_id")
    private Long creatorId;

    @Column(value = "create_time")
    @JsonSerialize(using = JacksonLocalDateTimeToStringRFC3339Serializer.class)
    @JsonDeserialize(converter = JacksonStringToLocalDateTimeRFC3339Deserializer.class)
    private LocalDateTime createTime;

    @Column(value = "row_is_delete")
    private Boolean rowIsDelete;

}
