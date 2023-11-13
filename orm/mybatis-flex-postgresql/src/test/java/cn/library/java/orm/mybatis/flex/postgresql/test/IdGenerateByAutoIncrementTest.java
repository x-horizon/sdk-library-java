// package cn.library.java.orm.mybatis.flex.postgresql.test;
//
// import cn.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
// import cn.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
// import cn.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdAutoIncrementDao;
// import cn.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdAutoIncrementPO;
// import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
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
//         globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.AUTO_INCREMENT),
//         globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE, deletedValue = BooleanConstant.TRUE),
//         globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class)
// )
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
