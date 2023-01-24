package cn.srd.itcp.sugar.convert.all.mapstruct.bean.vo;

import cn.hutool.core.util.RandomUtil;
import cn.srd.itcp.sugar.convert.all.mapstruct.bean.domain.StudentDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class StudentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5535158525166867335L;

    private Integer studentId;
    private String studentName;
    private Integer studentAge;
    private Integer studentSexNumber;
    private String studentSexName;

    public static StudentVO newVO() {
        return new StudentVO()
                .setStudentId(RandomUtil.randomInt(99))
                .setStudentName("name" + RandomUtil.randomNumbers(2))
                .setStudentAge(RandomUtil.randomInt(15, 20))
                .setStudentSexName(StudentDO.Sex.GIRL.name())
                .setStudentSexNumber(StudentDO.Sex.GIRL.getNumber());
    }

    public static List<StudentVO> newVOs() {
        return new ArrayList<StudentVO>() {{
            add(newVO());
            add(newVO());
            add(newVO());
        }};
    }

}