package cn.srd.itcp.sugar.convert.all.jackson.bean;

import cn.hutool.core.util.RandomUtil;
import cn.srd.itcp.sugar.convert.jackson.support.JacksonEnumToIntegerSerializer;
import cn.srd.itcp.sugar.convert.jackson.support.JacksonEnumValueToEnumDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
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
        return new StudentDO()
                .setId(RandomUtil.randomInt(99))
                .setName("name" + RandomUtil.randomNumbers(2))
                .setAge(RandomUtil.randomInt(15, 20))
                .setSex(Sex.GIRL);
    }

    public static List<StudentDO> newDOs() {
        return new ArrayList<StudentDO>() {{
            add(newDO());
            add(newDO());
            add(newDO());
        }};
    }

}