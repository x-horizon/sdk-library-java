package cn.srd.itcp.sugar.convert.all.mapstruct.bean.vo;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class StudentUnsupportedMapstructConvertVO implements Serializable {

    private static final long serialVersionUID = 732740561937200747L;

    private Integer id;
    private String name;
    private Integer age;
    private String sex;

    public static StudentUnsupportedMapstructConvertVO newVO() {
        return new StudentUnsupportedMapstructConvertVO()
            .setId(RandomUtil.randomInt(99))
            .setName("name" + RandomUtil.randomNumbers(2))
            .setAge(RandomUtil.randomInt(15, 20))
            .setSex("GIRL");
    }

    public static List<StudentUnsupportedMapstructConvertVO> newVOs() {
        return new ArrayList<StudentUnsupportedMapstructConvertVO>() {{
            add(new StudentUnsupportedMapstructConvertVO());
            add(new StudentUnsupportedMapstructConvertVO());
            add(new StudentUnsupportedMapstructConvertVO());
        }};
    }

}