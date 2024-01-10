package cn.srd.library.java.orm.mybatis.flex.postgresql.model.po;

import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.curd.version.BaseWithVersionPO;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "student_test_id_uuid")
public class StudentTestIdUUIDPO extends BaseWithVersionPO {

    @Serial private static final long serialVersionUID = -8972261580701462874L;

    @Id
    @Column(value = "id")
    private String id;

    @Column(value = "name")
    private String name;

}