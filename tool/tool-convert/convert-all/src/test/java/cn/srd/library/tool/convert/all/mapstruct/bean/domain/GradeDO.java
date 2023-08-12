package cn.srd.library.tool.convert.all.mapstruct.bean.domain;

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
public class GradeDO implements Serializable {

    @Serial private static final long serialVersionUID = 5570807991030073183L;

    private Integer id;

    private String name;

    private List<StudentDO> students;

    public static GradeDO newDO() {
        return GradeDO.builder()
                .id(RandomUtil.randomInt(99))
                .name(RandomUtil.randomInt(1, 7) + "年级")
                .students(StudentDO.newDOs())
                .build();
    }

    public static List<GradeDO> newDOs() {
        return new ArrayList<>() {
            @Serial private static final long serialVersionUID = -3467029187608444051L;

            {
                add(newDO());
                add(newDO());
                add(newDO());
            }
        };
    }

}