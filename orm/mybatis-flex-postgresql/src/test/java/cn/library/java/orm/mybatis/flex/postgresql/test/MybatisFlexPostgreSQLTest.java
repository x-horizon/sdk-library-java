package cn.library.java.orm.mybatis.flex.postgresql.test;

import cn.library.java.orm.mybatis.flex.postgresql.dao.BookDao;
import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentClassPO;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentPO;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.table.StudentClassPOTableDef;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.table.StudentPOTableDef;
import cn.library.java.orm.mybatis.flex.postgresql.service.BookService;
import cn.library.java.orm.mybatis.flex.postgresql.service.StudentClassService;
import cn.library.java.orm.mybatis.flex.postgresql.service.StudentService;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @EnableSnowflakeId
@MapperScan("cn.library.java.orm.mybatis.flex.postgresql.dao")
// @EnableMybatisFlexCustomizer(
//         globalIdGenerateConfig = @IdGenerateConfig(type = IdGenerateType.AUTO_INCREMENT),
//         auditConfig = @AuditConfig(enableLogSQL = "${library.java.orm.enable-audit-message}")
// )
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisFlexPostgreSQLTest {

    @Autowired private StudentClassService studentClassService;

    @Autowired private StudentService studentService;

    @Autowired private BookService bookService;

    @Autowired private BookDao bookDao;

    @Autowired private StudentDao studentDao;

    @Test
    public void testGet() {
        StudentPO studentPO1 = studentDao.selectOneById(2L);
        StudentPO studentPO11 = studentDao.selectOneById(2L);
        StudentClassPO studentClassPO1 = studentClassService.getById(1L);
        StudentClassPO studentClassPO11 = studentClassService.getById(1L);
        StudentClassPO studentClassPO111 = studentClassService.getById(1L);
        StudentClassPO studentClassPO1111 = studentClassService.getById(1L);
        StudentClassPO studentClassPO11111 = studentClassService.getById(1L);
        StudentClassPO studentClassPO111111 = studentClassService.getById(1L);
        List<StudentPO> studentPO3 = studentDao.selectAll();
        List<StudentPO> studentPO31 = studentDao.selectAll();
        List<StudentPO> studentPO311 = studentDao.selectAll();
        List<StudentPO> studentPO3111 = studentDao.selectAll();
        List<StudentPO> studentPO31111 = studentDao.selectAll();
        List<StudentPO> studentPO311111 = studentDao.selectAll();

        QueryWrapper wrapper = QueryWrapper.create().select()
                .from(StudentPOTableDef.STUDENT_P_O)
                .leftJoin(StudentClassPOTableDef.STUDENT_CLASS_P_O)
                .on(StudentPOTableDef.STUDENT_P_O.ID.eq(StudentClassPOTableDef.STUDENT_CLASS_P_O.STUDENT_ID));

        studentClassService.list(wrapper);
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
