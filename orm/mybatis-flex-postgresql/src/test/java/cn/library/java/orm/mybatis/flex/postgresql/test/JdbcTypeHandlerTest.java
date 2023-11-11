package cn.library.java.orm.mybatis.flex.postgresql.test;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestTypeHandlerDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.enums.JobType;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.ClassPO;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.DetailPO;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestTypeHandlerPO;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.time.Times;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@MapperScan("cn.library.java.orm.mybatis.flex.postgresql.dao")
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTypeHandlerTest {

    @Autowired private StudentTestTypeHandlerDao studentTestTypeHandlerDao;

    @Test
    public void testGet() {
        StudentTestTypeHandlerPO studentTestTypeHandlerPO1 = studentTestTypeHandlerDao.selectOneById(1L);
        StudentTestTypeHandlerPO studentTestTypeHandlerPO2 = studentTestTypeHandlerDao.selectOneById(2L);
        StudentTestTypeHandlerPO studentTestTypeHandlerPO3 = studentTestTypeHandlerDao.selectOneById(3L);
        List<StudentTestTypeHandlerPO> studentTestTypeHandlerPOs1 = studentTestTypeHandlerDao.selectAll();
        List<StudentTestTypeHandlerPO> studentTestTypeHandlerPOs2 = studentTestTypeHandlerDao.selectAll();

        studentTestTypeHandlerDao.insertSelective(StudentTestTypeHandlerPO.builder()
                .id(94L)
                .classId(UUID.fromString("2af3acfd-5d1d-414c-b72f-5815c595fb6e"))
                .teacherId("2af3acfd-5d1d-414c-b72f-5815c595fb6e")
                .phoneId(UUID.fromString("2af3acfd-5d1d-414c-b72f-5815c595fb6e"))
                .familyIds(Collections.ofImmutableList(1L, 2L, 3L))
                .jobTypes(Collections.ofImmutableList(JobType.A, JobType.B, JobType.A))
                .classPOs(Collections.ofImmutableList(ClassPO.builder().id(1L).name("mmm").build(), ClassPO.builder().id(2L).name("mmm1").build()))
                .bookInfos("[{\"id\": 1, \"name\": \"myBook1\"}, {\"id\": 2, \"name\": \"myBook2\"}]")
                .detailPO(DetailPO.builder().name("vvv").age((short) 19).build())
                .version(1L)
                .creatorId(1L)
                .createTime(Times.getCurrentDateTime())
                .rowIsDeleted(false)
                .build()
        );

        // QueryWrapper wrapper = QueryWrapper.create().select()
        //         .from(StudentPOTableDef.STUDENT_P_O)
        //         .leftJoin(StudentClassPOTableDef.STUDENT_CLASS_P_O)
        //         .on(StudentPOTableDef.STUDENT_P_O.ID.eq(StudentClassPOTableDef.STUDENT_CLASS_P_O.STUDENT_ID));

        System.out.println();

        // StudentPO studentPO = StudentPO.builder()
        //         // .type(StudentType.A)
        //         .build();
        // studentService.save(studentPO);

        // BookPO bookPO = BookPO.builder()
        //         // .type(StudentType.A)
        //         .build();
        // bookService.save(bookPO);
        //
        // StudentPO studentPO2 = studentService.getById(1);
    }

}
