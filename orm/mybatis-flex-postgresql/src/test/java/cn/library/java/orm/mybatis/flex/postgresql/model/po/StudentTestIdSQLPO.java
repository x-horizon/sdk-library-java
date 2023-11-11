package cn.library.java.orm.mybatis.flex.postgresql.model.po;

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
@Table(value = "student_test_id_sql")
public class StudentTestIdSQLPO extends BasePO {

    @Serial private static final long serialVersionUID = 1719575586110517425L;

    @Id
    @Column(value = "id")
    private String id;

    @Column(value = "name")
    private String name;

}
