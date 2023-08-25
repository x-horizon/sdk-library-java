package cn.srd.library.java.orm.mybatis.flex;

import cn.srd.library.java.orm.mybatis.flex.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisFlexTest {

    @Autowired private StudentService studentService;

    @Test
    public void testl() {
        StudentPO studentPO = studentService.getById(1);
    }

}
