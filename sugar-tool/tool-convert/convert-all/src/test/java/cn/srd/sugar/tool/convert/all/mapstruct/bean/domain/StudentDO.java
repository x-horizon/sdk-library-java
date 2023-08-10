package cn.srd.sugar.tool.convert.all.mapstruct.bean.domain;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
public class StudentDO implements Serializable {

    @Serial private static final long serialVersionUID = 6221890475348794702L;

    private Integer id;

    private String name;

    private Integer age;

    private Sex sex;

    @Getter
    @AllArgsConstructor
    public enum Sex {
        GIRL(1),
        BOY(2);
        private final int number;
    }

    public static StudentDO newDO() {
        return StudentDO.builder()
                .id(RandomUtil.randomInt(99))
                .name("name" + RandomUtil.randomNumbers(2))
                .age(RandomUtil.randomInt(15, 20))
                .sex(Sex.GIRL)
                .build();
    }

    public static List<StudentDO> newDOs() {
        return new ArrayList<>() {
            @Serial private static final long serialVersionUID = 439600321414984404L;

            {
                add(newDO());
                add(newDO());
                add(newDO());
            }
        };
    }

}