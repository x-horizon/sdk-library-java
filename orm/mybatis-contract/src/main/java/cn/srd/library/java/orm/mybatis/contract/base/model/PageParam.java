// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.contract.base.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

/**
 * the page param model
 *
 * @author wjm
 * @since 2020-01-05 13:45
 */
@Schema(description = "page param model")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class PageParam implements Serializable {

    @Serial private static final long serialVersionUID = -2956893884714618641L;

    @Schema(description = "page number", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "page number is required")
    @Min(value = 1, message = "the minimum page number is 1")
    private Integer pageIndex;

    @Schema(description = "record number per page", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @NotNull(message = "record number per page is required")
    @Range(min = 1, max = 100, message = "the range of record number per page is [1, 100]")
    private Integer pageSize;

}