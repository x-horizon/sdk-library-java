package org.horizon.sdk.library.java.tool.spring.webmvc.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author wjm
 * @since 2024-06-15 14:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class FooVO implements Serializable {

    @Serial private static final long serialVersionUID = -7576755740450265152L;

    @NotNull(message = "the field [id] value is not allow to be null")
    private Long id;

    @NotBlank(message = "the field [name] value is not allow to be blank")
    private String name;

    @NotEmpty(message = "the field [randomNumbers] value is not allow to be empty")
    private List<Long> randomNumbers;

    @Valid
    @NotEmpty(message = "the field [fooInternalInfos] value is not allow to be empty")
    @JsonProperty("fooInternalInfos")
    private List<FooInternalVO> fooInternalVOs;

    public static class FooInternalVO implements Serializable {

        @Serial private static final long serialVersionUID = 574795573904344407L;

        @NotNull(message = "the field [internalId] value is not allow to be null")
        private Long internalId;

        @NotBlank(message = "the field [internalName] value is not allow to be blank")
        private String internalName;

    }

}