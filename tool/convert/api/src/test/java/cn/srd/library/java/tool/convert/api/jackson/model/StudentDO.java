// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.api.jackson.model;

import cn.hutool.core.util.RandomUtil;
import cn.srd.library.java.tool.convert.jackson.deserializer.JacksonEnumValueToEnumDeserializer;
import cn.srd.library.java.tool.convert.jackson.serializer.JacksonEnumToIntegerSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Null;
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
        return new ArrayList<>() {
            @Serial private static final long serialVersionUID = -1402626797744036935L;

            {
                add(newDO());
                add(newDO());
                add(newDO());
            }
        };
    }

}