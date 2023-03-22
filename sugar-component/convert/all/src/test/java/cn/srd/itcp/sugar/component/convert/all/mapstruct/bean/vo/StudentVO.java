package cn.srd.itcp.sugar.component.convert.all.mapstruct.bean.vo;

import cn.hutool.core.util.RandomUtil;
import cn.srd.itcp.sugar.component.convert.all.mapstruct.bean.domain.StudentDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class StudentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5535158525166867335L;

    private Integer studentId;
    private String studentName;
    private Integer studentAge;
    private Integer studentSexNumber;
    private String studentSexName;

    public static StudentVO newVO() {
        return StudentVO.builder()
                .studentId(RandomUtil.randomInt(99))
                .studentName("name" + RandomUtil.randomNumbers(2))
                .studentAge(RandomUtil.randomInt(15, 20))
                .studentSexName(StudentDO.Sex.GIRL.name())
                .studentSexNumber(StudentDO.Sex.GIRL.getNumber())
                .build();
    }

    public static List<StudentVO> newVOs() {
        return new ArrayList<>() {{
            add(newVO());
            add(newVO());
            add(newVO());
        }};
    }

}