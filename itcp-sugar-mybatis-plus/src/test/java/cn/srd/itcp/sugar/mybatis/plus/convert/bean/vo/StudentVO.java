package cn.srd.itcp.sugar.mybatis.plus.convert.bean.vo;

import cn.hutool.core.util.RandomUtil;
import cn.srd.itcp.sugar.mybatis.plus.convert.bean.domain.StudentDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class StudentVO implements Serializable {

    private static final long serialVersionUID = 8117094738912289865L;

    private Integer id;
    private String name;
    private Integer age;
    private StudentDO.Sex sex;

    public static StudentVO newVO() {
        return new StudentVO()
                .setId(RandomUtil.randomInt(99))
                .setName("name" + RandomUtil.randomNumbers(2))
                .setAge(RandomUtil.randomInt(15, 20));
    }

    public static List<StudentVO> newVOs() {
        return new ArrayList<StudentVO>() {{
            add(newVO());
            add(newVO());
            add(newVO());
        }};
    }

}