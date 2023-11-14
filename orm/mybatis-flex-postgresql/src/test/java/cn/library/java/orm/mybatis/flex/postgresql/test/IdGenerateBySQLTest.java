// package cn.library.java.orm.mybatis.flex.postgresql.test;
//
// import cn.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
// import cn.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
// import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSQLDao;
// import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSQLPO;
// import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
// import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
// import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
// import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// @MapperScan("cn.library.java.orm.mybatis.flex.postgresql.dao")
// @EnableMybatisFlexCustomizer(
//         globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SQL, generateSQL = "SELECT SUBSTRING(MD5(RANDOM()::VARCHAR), 2, 8) AS id"),
//         globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE, deletedValue = BooleanConstant.TRUE),
//         globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
//         auditConfig = @AuditLogConfig(enable = true)
// )
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
