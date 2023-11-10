package cn.library.java.orm.mybatis.flex.postgresql.test;

import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentDao;
import cn.library.java.orm.mybatis.flex.postgresql.model.enums.JobType;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.ClassPO;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.DetailPO;
import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentPO;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.time.Times;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

// @EnableSnowflakeId
@MapperScan("cn.library.java.orm.mybatis.flex.postgresql.dao")
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(type = IdGenerateType.AUTO_INCREMENT)
        // test wrong id config
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.AUTO_INCREMENT, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.UUID, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.SNOWFLAKE, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.CUSTOMER, generator = IdInvalidGenerator.class, generateSQL = "dsdcsdcd")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.SQL, generator = IdSnowflakeGenerator.class, generateSQL = "")
        // globalIdGenerateConfig = @IdConfig(type = IdGenerateType.UNCONTROLLED, generator = IdSnowflakeGenerator.class, generateSQL = "dsdcsdcd")
)
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGenerateTest {

    @Autowired private StudentDao studentDao;

    @Test
    public void testl() {
        studentDao.insertSelective(StudentPO.builder()
                // .id(94L)
                .classId(UUID.fromString("2af3acfd-5d1d-414c-b72f-5815c595fb6e"))
                .teacherId("2af3acfd-5d1d-414c-b72f-5815c595fb6e")
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

        System.out.println();
    }

}
