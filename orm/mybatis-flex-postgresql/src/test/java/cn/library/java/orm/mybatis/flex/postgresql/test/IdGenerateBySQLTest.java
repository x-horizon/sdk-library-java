// package cn.library.java.orm.mybatis.flex.postgresql.test;
//
// import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSQLDao;
// import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSQLPO;
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
// @EnableMybatisFlexCustomizer(globalIdGenerateConfig = @IdConfig(type = IdGenerateType.SQL, generateSQL = "SELECT SUBSTRING(MD5(RANDOM()::VARCHAR), 2, 8) AS id"))
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class IdGenerateBySQLTest {
//
//     @Autowired private StudentTestIdSQLDao studentTestIdSQLDao;
//
//     @Test
//     public void testIt() {
//         // studentTestIdSQLDao.insert(StudentTestIdSQLPO.builder().build());
//         studentTestIdSQLDao.insertSelective(StudentTestIdSQLPO.builder().build());
//
//         System.out.println();
//     }
//
// }
