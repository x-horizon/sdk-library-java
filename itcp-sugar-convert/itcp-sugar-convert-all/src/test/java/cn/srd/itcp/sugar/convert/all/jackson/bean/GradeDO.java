package cn.srd.itcp.sugar.convert.all.jackson.bean;

import cn.hutool.core.util.RandomUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class GradeDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5570807991030073183L;

    @NegativeOrZero
    private Integer id;

    @NotNull
    private String name;

    @NotEmpty
    @Valid
    private List<StudentDO> students;

    public static GradeDO newDO() {
        return new GradeDO()
                .setId(RandomUtil.randomInt(99))
                .setName(RandomUtil.randomInt(1, 7) + "年级")
                .setStudents(StudentDO.newDOs());
    }

    public static List<GradeDO> newDOs() {
        return new ArrayList<GradeDO>() {{
            add(newDO());
            add(newDO());
            add(newDO());
        }};
    }

}