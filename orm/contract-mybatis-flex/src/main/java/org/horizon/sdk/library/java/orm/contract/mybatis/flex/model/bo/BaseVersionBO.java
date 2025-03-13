package org.horizon.sdk.library.java.orm.contract.mybatis.flex.model.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybatisflex.annotation.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.horizon.sdk.library.java.contract.model.base.BO;

import java.io.Serial;

/**
 * @author wjm
 * @since 2024-07-11 17:42
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BaseVersionBO implements BO {

    @Serial private static final long serialVersionUID = 6721916228184516363L;

    @Schema(description = "版本号")
    @Column(value = "version", version = true)
    @JsonIgnore
    private Long version;

}