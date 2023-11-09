package cn.library.java.orm.mybatis.flex.postgresql.model.po;

import cn.library.java.orm.mybatis.contract.base.handler.JdbcCharMappingJavaUUIDTypeHandler;
import cn.library.java.orm.mybatis.contract.base.handler.JdbcUUIDMappingJavaStringTypeHandler;
import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbMappingJavaListEnumIntegerTypeHandler;
import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbMappingJavaListLongTypeHandler;
import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbMappingJavaNullableEntityTypeHandler;
import cn.library.java.orm.mybatis.contract.postgresql.handler.JdbcJsonbMappingJavaNullableListEntityTypeHandler;
import cn.library.java.orm.mybatis.flex.postgresql.model.enums.JobType;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Table(value = "student")
public class StudentPO extends BasePO {

    @Serial private static final long serialVersionUID = 8974991316575686572L;

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "teacher_id", typeHandler = JdbcUUIDMappingJavaStringTypeHandler.class)
    private String teacherId;

    @Column(value = "class_id", typeHandler = JdbcCharMappingJavaUUIDTypeHandler.class)
    private UUID classId;

    @Column(value = "family_ids", typeHandler = JdbcJsonbMappingJavaListLongTypeHandler.class)
    private List<Long> familyIds;

    @Column(value = "detail_info", typeHandler = JdbcJsonbMappingJavaNullableEntityTypeHandler.class)
    private DetailPO detailPO;

    @Column(value = "class_infos", typeHandler = JdbcJsonbMappingJavaNullableListEntityTypeHandler.class)
    private List<ClassPO> classPOs;

    @Column(value = "job_types", typeHandler = JdbcJsonbMappingJavaListEnumIntegerTypeHandler.class)
    private List<JobType> jobTypes;

}
