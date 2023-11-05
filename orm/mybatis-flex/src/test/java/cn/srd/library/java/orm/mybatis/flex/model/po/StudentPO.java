package cn.srd.library.java.orm.mybatis.flex.model.po;

import cn.library.java.orm.mybatis.data.postgresql.handler.JavaListObjectMappingJdbcJsonbTypeHandler;
import cn.library.java.orm.mybatis.data.postgresql.handler.JavaObjectMappingJdbcJsonbTypeHandler;
import cn.srd.library.java.orm.mybatis.flex.model.enums.StudentType;
import cn.srd.library.java.tool.convert.jackson.NullableObject;
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
@Table(value = "school_student")
public class StudentPO extends BasePO implements Serializable {

    @Serial private static final long serialVersionUID = -7680901283684311918L;

    @Id
    @Column(value = "student_id")
    private Long id;

    @Column(value = "detail_info", typeHandler = StudentDetailPOMappingJsonbTypeHandler.class)
    private StudentDetailPO detailPO;

    @Column(value = "class_infos", typeHandler = StudentClassPOMappingJsonbTypeHandler.class)
    private List<StudentClassPO> classPOs;

    @Column(value = "student_type")
    private StudentType type;

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    public static class StudentDetailPO implements NullableObject, Serializable {

        @Serial private static final long serialVersionUID = 2742055391867234666L;

        private String name;

        @Override
        public boolean isNull() {
            return false;
        }

    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    @SuperBuilder(toBuilder = true)
    public static class StudentClassPO implements NullableObject, Serializable {

        @Serial private static final long serialVersionUID = 88531220073385451L;

        private Long id;

        private String name;

        @Override
        public boolean isNull() {
            return false;
        }

    }

    public static class StudentDetailPOMappingJsonbTypeHandler extends JavaObjectMappingJdbcJsonbTypeHandler<StudentDetailPO> {

        @Override
        public Class<StudentDetailPO> getTargetClass() {
            return StudentDetailPO.class;
        }

    }

    public static class StudentClassPOMappingJsonbTypeHandler extends JavaListObjectMappingJdbcJsonbTypeHandler<StudentClassPO> {

        @Override
        public Class<StudentClassPO> getTargetClass() {
            return StudentClassPO.class;
        }

    }

}
