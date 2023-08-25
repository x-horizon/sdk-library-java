package cn.srd.library.java.orm.mybatis.flex.model.po;

import cn.srd.library.java.orm.mybatis.flex.model.enums.StudentType;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
@Table(value = "student")
public class StudentPO extends BasePO implements Serializable {

    @Serial private static final long serialVersionUID = -7680901283684311918L;

    @Id(keyType = KeyType.Generator)
    @Column(value = "student_id")
    private Long id;

    @Column(value = "detail_info")
    private StudentDetailPO detailPO;

    @Column(value = "class_infos")
    private List<StudentClassPO> classPOs;

    @Column(value = "student_type")
    private StudentType type;

    public static class StudentDetailPO implements Serializable {

        @Serial private static final long serialVersionUID = 2742055391867234666L;

        private String name;

    }

    public static class StudentClassPO implements Serializable {

        @Serial private static final long serialVersionUID = 88531220073385451L;

        private Long id;

        private String name;

    }

}
