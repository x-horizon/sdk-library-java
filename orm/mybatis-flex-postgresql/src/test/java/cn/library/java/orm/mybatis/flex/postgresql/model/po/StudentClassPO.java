package cn.library.java.orm.mybatis.flex.postgresql.model.po;

import cn.library.java.orm.mybatis.flex.postgresql.handler.JavaListObjectMappingJdbcJsonbTypeHandler;
import cn.library.java.orm.mybatis.flex.postgresql.handler.JavaObjectMappingJdbcJsonbTypeHandler;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "school_student_class")
public class StudentClassPO extends BasePO implements Serializable {

    @Serial private static final long serialVersionUID = 7251891028954508667L;

    @Id
    @Column(value = "relation_id")
    private Long id;

    @Column(value = "student_id")
    private Long studentId;

    @Column(value = "class_id")
    private Long classId;

    @Column(value = "detail_info", typeHandler = JavaObjectMappingJdbcJsonbTypeHandler.class)
    private DetailPO detailPO;

    @Column(value = "class_infos", typeHandler = JavaListObjectMappingJdbcJsonbTypeHandler.class)
    // @Column(value = "class_infos", typeHandler = ClassPO.ClassPOMappingJsonbTypeHandler.class)
    private List<ClassPO> classPOs;

}
