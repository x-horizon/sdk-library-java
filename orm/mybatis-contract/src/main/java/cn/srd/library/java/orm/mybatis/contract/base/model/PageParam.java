// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.contract.base.model;

import cn.srd.library.java.contract.constant.page.PageConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;
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
    @Min(value = 1, message = "the minimum page number is 1")
    @Builder.Default
    private Integer pageIndex = PageConstant.DEFAULT_PAGE_INDEX;

    @Schema(description = "record number per page", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @Range(min = 1, max = 100, message = "the range of record number per page is [1, 100]")
    @Builder.Default
    private Integer pageSize = PageConstant.DEFAULT_PAGE_SIZE;

}