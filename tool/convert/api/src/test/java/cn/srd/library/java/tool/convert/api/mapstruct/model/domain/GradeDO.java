// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.api.mapstruct.model.domain;

import cn.hutool.core.util.RandomUtil;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.convert.api.mapstruct.model.vo.GradeVO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AutoMapper(target = GradeVO.class)
public class GradeDO implements Serializable {

    @Serial private static final long serialVersionUID = 5570807991030073183L;

    private Integer id;

    private String name;

    private List<StudentDO> students;

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static GradeDO newDO() {
        return GradeDO.builder()
                .id(RandomUtil.randomInt(99))
                .name(STR."\{RandomUtil.randomInt(1, 7)}年级")
                .students(StudentDO.newDOs())
                .build();
    }

    public static List<GradeDO> newDOs() {
        return new ArrayList<>() {
            @Serial private static final long serialVersionUID = -3467029187608444051L;

            {
                add(newDO());
                add(newDO());
                add(newDO());
            }
        };
    }

}