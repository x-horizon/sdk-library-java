// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.api.jackson.model;

import cn.hutool.core.util.RandomUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class GradeDO implements Serializable {

    @Serial private static final long serialVersionUID = 5570807991030073183L;

    @NegativeOrZero
    private Integer id;

    @NotNull
    private String name;

    @NotEmpty
    @Valid
    private List<StudentDO> students;

    public static GradeDO newDO() {
        return GradeDO.builder()
                .id(RandomUtil.randomInt(99))
                .name(RandomUtil.randomInt(1, 7) + "年级")
                .students(StudentDO.newDOs())
                .build();
    }

    public static List<GradeDO> newDOs() {
        return new ArrayList<>() {
            @Serial private static final long serialVersionUID = 8376136077097363162L;

            {
                add(newDO());
                add(newDO());
                add(newDO());
            }
        };
    }

}