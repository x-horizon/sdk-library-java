// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.model.page;

import cn.srd.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import cn.srd.library.java.orm.contract.model.base.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * the page result model
 *
 * @param <T> the page result element data type
 * @author wjm
 * @since 2020-01-05 13:45
 */
@Schema(description = "page result model")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class PageResult<T extends Serializable> implements DTO {

    @Serial private static final long serialVersionUID = -6490851620278359181L;

    @Schema(description = "total record number", requiredMode = Schema.RequiredMode.REQUIRED, example = ApiDocConstant.NUMBER)
    private Long totalNumber;

    @Schema(description = "total page number", requiredMode = Schema.RequiredMode.REQUIRED, example = ApiDocConstant.NUMBER)
    private Long totalPageNumber;

    @Schema(description = "current page number", requiredMode = Schema.RequiredMode.REQUIRED, example = ApiDocConstant.NUMBER)
    private Long currentPageNumber;

    @Schema(description = "record number per page", requiredMode = Schema.RequiredMode.REQUIRED, example = ApiDocConstant.NUMBER)
    private Long pageSize;

    @Schema(description = "collection record", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<T> data;

    @Schema(description = "object record")
    private Serializable datum;

}