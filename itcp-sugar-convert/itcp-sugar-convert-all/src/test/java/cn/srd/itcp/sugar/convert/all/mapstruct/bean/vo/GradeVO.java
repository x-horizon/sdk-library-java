package cn.srd.itcp.sugar.convert.all.mapstruct.bean.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GradeVO implements Serializable {

    private static final long serialVersionUID = 4256178933382395583L;

    private Integer id;
    private String name;
    private JSONArray students;

}