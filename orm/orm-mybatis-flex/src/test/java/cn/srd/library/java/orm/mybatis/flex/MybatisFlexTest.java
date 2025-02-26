package cn.srd.library.java.orm.mybatis.flex;

import cn.srd.library.java.id.snowflake.core.EnableSnowflakeId;
import cn.srd.library.java.orm.mybatis.flex.demo.school.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.demo.school.service.BookService;
import cn.srd.library.java.orm.mybatis.flex.demo.school.service.StudentService;
import com.test.api.EnableMybatisFlexCustomizer;
import com.test.core.audit.AuditConfig;
import com.test.core.id.IdGenerateConfig;
import com.test.core.id.IdGenerateType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@EnableSnowflakeId
@MapperScan("cn.srd.library.java.orm.mybatis.flex.demo.school.dao")
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdGenerateConfig(type = IdGenerateType.AUTO_INCREMENT),
        auditConfig = @AuditConfig(enableLogSQL = "${library.java.orm.enable-audit-message}")
)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisFlexTest {

    @Autowired private StudentService studentService;

    @Autowired private BookService bookService;

    @Test
    public void testl() {
        StudentPO studentPO = StudentPO.builder()
                // .type(StudentType.A)
                .build();
        studentService.save(studentPO);

        // BookPO bookPO = BookPO.builder()
        //         // .type(StudentType.A)
        //         .build();
        // bookService.save(bookPO);
        //
        // StudentPO studentPO2 = studentService.getById(1);
    }

}
