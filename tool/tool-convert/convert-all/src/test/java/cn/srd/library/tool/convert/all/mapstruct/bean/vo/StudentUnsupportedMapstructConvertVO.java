package cn.srd.library.tool.convert.all.mapstruct.bean.vo;

import cn.hutool.core.util.RandomUtil;
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
public class StudentUnsupportedMapstructConvertVO implements Serializable {

    @Serial private static final long serialVersionUID = 732740561937200747L;

    private Integer id;
    private String name;
    private Integer age;
    private String sex;

    public static StudentUnsupportedMapstructConvertVO newVO() {
        return StudentUnsupportedMapstructConvertVO.builder()
                .id(RandomUtil.randomInt(99))
                .name("name" + RandomUtil.randomNumbers(2))
                .age(RandomUtil.randomInt(15, 20))
                .sex("GIRL")
                .build();
    }

    public static List<StudentUnsupportedMapstructConvertVO> newVOs() {
        return new ArrayList<>() {
            @Serial private static final long serialVersionUID = 8443466674450392777L;

            {
                add(StudentUnsupportedMapstructConvertVO.builder().build());
                add(StudentUnsupportedMapstructConvertVO.builder().build());
                add(StudentUnsupportedMapstructConvertVO.builder().build());
            }
        };
    }

}