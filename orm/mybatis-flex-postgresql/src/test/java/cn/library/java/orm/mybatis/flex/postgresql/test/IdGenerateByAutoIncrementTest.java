// package cn.library.java.orm.mybatis.flex.postgresql.test;
//
// import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdAutoIncrementDao;
// import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdAutoIncrementPO;
// import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
// import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// @MapperScan("cn.library.java.orm.mybatis.flex.postgresql.dao")
// @EnableMybatisFlexCustomizer(globalIdGenerateConfig = @IdConfig(type = IdGenerateType.AUTO_INCREMENT))
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class IdGenerateByAutoIncrementTest {
//
//     @Autowired private StudentTestIdAutoIncrementDao studentTestIdAutoIncrementDao;
//
//     @Test
//     public void testIt() {
//         studentTestIdAutoIncrementDao.insert(StudentTestIdAutoIncrementPO.builder().name("test1").build());
//         studentTestIdAutoIncrementDao.insertSelective(StudentTestIdAutoIncrementPO.builder().build());
//
//         System.out.println();
//     }
//
// }
