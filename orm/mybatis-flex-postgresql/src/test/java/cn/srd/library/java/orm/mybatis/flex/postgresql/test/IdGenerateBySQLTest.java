// package cn.srd.library.java.orm.mybatis.flex.postgresql.test;
//
// import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
// import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
// import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
// import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
// import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSQLDao;
// import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSQLPO;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mybatis.spring.annotation.MapperScan;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// @MapperScan("cn.srd.library.java.orm.mybatis.flex.postgresql.dao")
// @EnableMybatisFlexCustomizer(
//         globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SQL, generateSQL = "SELECT SUBSTRING(MD5(RANDOM()::VARCHAR), 2, 8) AS id"),
//         globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE_STRING_LOWER_CASE, deletedValue = BooleanConstant.TRUE_STRING_LOWER_CASE),
//         globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
//         globalAuditConfig = @AuditLogConfig(enable = true)
// )
// @SpringBootTest
// @ExtendWith(SpringExtension.class)
// class IdGenerateBySQLTest {
//
//     @Autowired private StudentTestIdSQLDao studentTestIdSQLDao;
//
//     @Test
//     void testIt() {
//         // studentTestIdSQLDao.insert(StudentTestIdSQLPO.builder().build());
//         studentTestIdSQLDao.insertSelective(StudentTestIdSQLPO.builder().build());
//
//         System.out.println();
//     }
//
// }
