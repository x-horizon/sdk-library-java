// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.bo;

import cn.srd.library.java.doc.knife4j.contract.constant.ApiDocConstant;
import cn.srd.library.java.tool.convert.jackson.NullableObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * 学生兴趣爱好信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Schema(description = "学生兴趣爱好信息")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class StudentHobbyBO implements NullableObject, Serializable {

    @Serial private static final long serialVersionUID = -3729928508090089728L;

    private transient boolean isNull;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = ApiDocConstant.STRING)
    private String name;

}