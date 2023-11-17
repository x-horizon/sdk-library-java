// package cn.srd.library.java.orm.mybatis.flex.postgresql.test;
//
// import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
// import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
// import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.StudentTestTypeHandlerDao;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.model.enums.JobType;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.ClassPO;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.DetailPO;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestTypeHandlerPO;
// import cn.srd.library.java.tool.lang.collection.Collections;
// import lombok.AllArgsConstructor;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import java.util.List;
// import java.util.UUID;
//
// @AllArgsConstructor
// @MapperScan("cn.srd.library.java.orm.mybatis.flex.postgresql.dao")
// @EnableMybatisFlexCustomizer(
//         globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE, deletedValue = BooleanConstant.TRUE),
//         globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
//         globalAuditConfig = @AuditLogConfig(enable = true)
// )
// @SpringBootTest
// @RunWith(SpringRunner.class)
// public class JdbcTypeHandlerTest {
//
//     private final StudentTestTypeHandlerDao studentTestTypeHandlerDao;
//
//     @Test
//     public void testGet() {
//         StudentTestTypeHandlerPO studentTestTypeHandlerPO1 = studentTestTypeHandlerDao.selectOneById(1L);
//         StudentTestTypeHandlerPO studentTestTypeHandlerPO2 = studentTestTypeHandlerDao.selectOneById(2L);
//         StudentTestTypeHandlerPO studentTestTypeHandlerPO3 = studentTestTypeHandlerDao.selectOneById(3L);
//         List<StudentTestTypeHandlerPO> studentTestTypeHandlerPOs1 = studentTestTypeHandlerDao.selectAll();
//         List<StudentTestTypeHandlerPO> studentTestTypeHandlerPOs2 = studentTestTypeHandlerDao.selectAll();
//
//         studentTestTypeHandlerDao.insert(StudentTestTypeHandlerPO.builder()
//                 .id(94L)
//                 .classId(UUID.fromString("2af3acfd-5d1d-414c-b72f-5815c595fb6e"))
//                 .teacherId("2af3acfd-5d1d-414c-b72f-5815c595fb6e")
//                 .phoneId(UUID.fromString("2af3acfd-5d1d-414c-b72f-5815c595fb6e"))
//                 .familyIds(Collections.ofImmutableList(1L, 2L, 3L))
//                 .jobTypes(Collections.ofImmutableList(JobType.A, JobType.B, JobType.A))
//                 .classPOs(Collections.ofImmutableList(ClassPO.builder().id(1L).name("mmm").build(), ClassPO.builder().id(2L).name("mmm1").build()))
//                 .bookInfos("[{\"id\": 1, \"name\": \"myBook1\"}, {\"id\": 2, \"name\": \"myBook2\"}]")
//                 .detailPO(DetailPO.builder().name("vvv").age((short) 19).build())
//                 .build()
//         );
//
//         // QueryWrapper wrapper = QueryWrapper.create().select()
//         //         .from(StudentPOTableDef.STUDENT_P_O)
//         //         .leftJoin(StudentClassPOTableDef.STUDENT_CLASS_P_O)
//         //         .on(StudentPOTableDef.STUDENT_P_O.ID.eq(StudentClassPOTableDef.STUDENT_CLASS_P_O.STUDENT_ID));
//
//         System.out.println();
//
//         // StudentPO studentPO = StudentPO.builder()
//         //         // .type(StudentType.A)
//         //         .build();
//         // studentService.save(studentPO);
//
//         // BookPO bookPO = BookPO.builder()
//         //         // .type(StudentType.A)
//         //         .build();
//         // bookService.save(bookPO);
//         //
//         // StudentPO studentPO2 = studentService.getById(1);
//     }
//
// }
