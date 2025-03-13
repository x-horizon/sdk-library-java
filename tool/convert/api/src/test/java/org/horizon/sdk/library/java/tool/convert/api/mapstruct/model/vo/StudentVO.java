package org.horizon.sdk.library.java.tool.convert.api.mapstruct.model.vo;

import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.dromara.hutool.core.util.RandomUtil;
import org.horizon.sdk.library.java.contract.constant.suppress.SuppressWarningConstant;
import org.horizon.sdk.library.java.tool.convert.api.mapstruct.model.domain.StudentDO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@AutoMapper(target = StudentDO.class)
public class StudentVO implements Serializable {

    @Serial private static final long serialVersionUID = 5535158525166867335L;

    @AutoMapping(target = "id")
    private Integer studentId;

    @AutoMapping(target = "name")
    private String studentName;

    @AutoMapping(target = "age")
    private Integer studentAge;

    private Integer studentSexNumber;

    private String studentSexName;

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    public static StudentVO newVO() {
        return StudentVO.builder()
                .studentId(RandomUtil.randomInt(99))
                .studentName(STR."name\{RandomUtil.randomNumbers(2)}")
                .studentAge(RandomUtil.randomInt(15, 20))
                .studentSexName(StudentDO.Sex.GIRL.name())
                .studentSexNumber(StudentDO.Sex.GIRL.getNumber())
                .build();
    }

    public static List<StudentVO> newVOs() {
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