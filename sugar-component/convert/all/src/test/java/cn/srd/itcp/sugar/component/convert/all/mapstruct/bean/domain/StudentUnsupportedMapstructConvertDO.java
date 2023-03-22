package cn.srd.itcp.sugar.component.convert.all.mapstruct.bean.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StudentUnsupportedMapstructConvertDO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4236383322015236485L;

    private Integer id;
    private String name;
    private Integer age;
    private String sex;

}