package org.horizon.sdk.library.java.tool.convert.api.mapstruct.model.vo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.dromara.hutool.core.util.RandomUtil;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.mapstruct.model.domain.GradeDO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AutoMapper(target = GradeDO.class)
public class GradeVO implements Serializable {

    @Serial private static final long serialVersionUID = 4256178933382395583L;

    private Integer id;

    private String name;

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static GradeVO newVO() {
        return GradeVO.builder()
                .id(RandomUtil.randomInt(99))
                .name(STR."name\{RandomUtil.randomNumbers(2)}")
                .build();
    }

    public static List<GradeVO> newVOs() {
        return new ArrayList<>() {
            @Serial private static final long serialVersionUID = -4181236729723713872L;

            {
                add(newVO());
                add(newVO());
                add(newVO());
            }
        };
    }

}