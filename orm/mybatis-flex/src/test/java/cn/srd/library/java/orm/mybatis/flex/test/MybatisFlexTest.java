package cn.srd.library.java.orm.mybatis.flex.test;

import cn.srd.library.java.orm.mybatis.flex.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.service.BookService;
import cn.srd.library.java.orm.mybatis.flex.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @EnableSnowflakeId
@MapperScan("cn.srd.library.java.orm.mybatis.flex.dao")
// @EnableMybatisFlexCustomizer(
//         globalIdGenerateConfig = @IdGenerateConfig(type = IdGenerateType.AUTO_INCREMENT),
//         auditConfig = @AuditConfig(enableLogSQL = "${library.java.orm.enable-audit-message}")
// )
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisFlexTest {

    @Autowired private StudentService studentService;

    @Autowired private BookService bookService;

    @Test
    public void testGet() {
        StudentPO studentPO = studentService.getById(455779089948741L);

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
