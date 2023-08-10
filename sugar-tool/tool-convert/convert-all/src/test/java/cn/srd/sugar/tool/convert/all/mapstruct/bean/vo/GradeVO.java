package cn.srd.sugar.tool.convert.all.mapstruct.bean.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class GradeVO implements Serializable {

    @Serial private static final long serialVersionUID = 4256178933382395583L;

    private Integer id;
    private String name;
    private JSONArray students;

}