package cn.srd.library.java.orm.mybatis.flex.base.test;

import cn.srd.library.java.orm.contract.model.base.BO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class StudentHobbyBO implements BO {

    @Serial private static final long serialVersionUID = 7853038020563851207L;

    private String name;

}