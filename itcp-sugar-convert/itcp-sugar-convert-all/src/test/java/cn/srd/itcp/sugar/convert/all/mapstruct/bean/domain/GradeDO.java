package cn.srd.itcp.sugar.convert.all.mapstruct.bean.domain;

import cn.hutool.core.util.RandomUtil;
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

    private Integer id;

    private String name;

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