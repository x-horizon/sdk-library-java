package cn.library.java.orm.contract.mybatis.flex.model.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybatisflex.annotation.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class BaseWithVersionBO extends BaseBO {

    @Serial private static final long serialVersionUID = 2152316212528302390L;

    @Schema(description = "版本号")
    @Column(value = "version", version = true)
    @JsonIgnore
    private Long version;

}