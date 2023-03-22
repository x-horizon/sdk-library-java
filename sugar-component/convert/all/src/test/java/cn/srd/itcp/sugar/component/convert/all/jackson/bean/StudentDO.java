package cn.srd.itcp.sugar.component.convert.all.jackson.bean;

import cn.hutool.core.util.RandomUtil;
import cn.srd.itcp.sugar.component.convert.jackson.support.JacksonEnumToIntegerSerializer;
import cn.srd.itcp.sugar.component.convert.jackson.support.JacksonEnumValueToEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StudentDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6221890475348794702L;

    @NegativeOrZero
    private Integer id;

    @Null
    private String name;

    @NegativeOrZero
    private Integer age;

    @JsonSerialize(using = JacksonEnumToIntegerSerializer.class)
    @JsonDeserialize(using = JacksonEnumValueToEnumDeserializer.class)
    private Sex sex;

    @Getter
    @AllArgsConstructor
    public enum Sex {
        GIRL(1, "女"),
        BOY(2, "男");
        private final int number;
        private final String description;
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
        return new ArrayList<>() {{
            add(newDO());
            add(newDO());
            add(newDO());
        }};
    }

}