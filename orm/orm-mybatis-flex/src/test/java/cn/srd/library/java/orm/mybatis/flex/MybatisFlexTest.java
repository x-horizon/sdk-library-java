package cn.srd.library.java.orm.mybatis.flex;

import cn.srd.library.java.id.snowflake.core.EnableSnowflakeId;
import cn.srd.library.java.orm.mybatis.flex.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.service.StudentService;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.core.keygen.impl.UUIDKeyGenerator;
import com.test.EnableMybatisFlexCustomizer;
import com.test.IdGenerateConfig;
import com.test.id.IdGenerateType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@EnableSnowflakeId
@MapperScan("cn.srd.library.java.orm.mybatis.flex.dao")
@EnableMybatisFlexCustomizer(globalIdGenerateConfig = @IdGenerateConfig(type = IdGenerateType.SNOWFLAKE))
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisFlexTest {

    @Autowired private StudentService studentService;

    @Test
    public void testl() {
        KeyGeneratorFactory.register("myUUID", new UUIDKeyGenerator());
        StudentPO studentPO = studentService.getById(1);
    }

}
