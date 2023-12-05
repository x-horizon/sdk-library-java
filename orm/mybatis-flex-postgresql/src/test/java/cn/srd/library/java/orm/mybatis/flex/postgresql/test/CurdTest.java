// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.test;

import cn.srd.library.java.contract.constant.booleans.BooleanConstant;
import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.CurdOneIdDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.CurdTwoIdDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.JoinOneDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.JoinTwoDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.CurdOneIdPO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.JoinOnePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.JoinTwoPO;
import cn.srd.library.java.tool.id.snowflake.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIdEnvironment;
import cn.srd.library.java.tool.lang.object.Types;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@MapperScan("cn.srd.library.java.orm.mybatis.flex.postgresql.dao")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(normalValue = BooleanConstant.FALSE_STRING_LOWER_CASE, deletedValue = BooleanConstant.TRUE_STRING_LOWER_CASE),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                nativeMybatisLog = NoLoggingImpl.class,
                xmlMapperClassPaths = {"classpath*:cn/srd/library/java/orm/mybatis/base/customer/dao/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"cn.srd.library.java.orm.mybatis.**.po"}
        )
)
@SpringBootTest
@ExtendWith(SpringExtension.class)
class CurdTest {

    @Autowired private CurdOneIdDao curdOneIdDao;

    @Autowired private CurdTwoIdDao curdTwoIdDao;

    @Autowired private JoinOneDao joinOneDao;

    @Autowired private JoinTwoDao joinTwoDao;

    // @Test
    // void testSave() {
    //     CurdOneIdPO curdOneIdPO1 = curdOneIdDao.save(CurdOneIdPO.builder().build());
    //     CurdOneIdPO curdOneIdPO2 = curdOneIdDao.save(CurdOneIdPO.builder().id(768L).build());
    //
    //     Map<Integer, CurdOneIdPO> smallCurdOneIdPOs = Collections.newHashMap();
    //     for (int i = 1; i < 60; i++) {
    //         smallCurdOneIdPOs.put(i, CurdOneIdPO.builder().build());
    //     }
    //     List<CurdOneIdPO> curdOneIdPOs1 = curdOneIdDao.save(smallCurdOneIdPOs.values());
    //
    //     Map<Integer, CurdOneIdPO> bigCurdOneIdPOs = Collections.newHashMap();
    //     for (int i = 1; i < 300; i++) {
    //         bigCurdOneIdPOs.put(i, CurdOneIdPO.builder().build());
    //     }
    //     List<CurdOneIdPO> curdOneIdPOs2 = curdOneIdDao.save(bigCurdOneIdPOs.values());
    //
    //     Map<Integer, CurdOneIdPO> smallStudentTestIdSnowflakeWithIdPOs = Collections.newHashMap();
    //     for (int i = 1; i < 60; i++) {
    //         Long id = (long) (10000 + i);
    //         smallStudentTestIdSnowflakeWithIdPOs.put(i, CurdOneIdPO.builder().id(id).build());
    //     }
    //     List<CurdOneIdPO> curdOneIdPOs3 = curdOneIdDao.save(smallStudentTestIdSnowflakeWithIdPOs.values());
    //
    //     Map<Integer, CurdOneIdPO> bigStudentTestIdSnowflakeWithIdPOs = Collections.newHashMap();
    //     for (int i = 1; i < 300; i++) {
    //         Long id = (long) (20000 + i);
    //         bigStudentTestIdSnowflakeWithIdPOs.put(i, CurdOneIdPO.builder().id(id).build());
    //     }
    //     List<CurdOneIdPO> curdOneIdPOs4 = curdOneIdDao.save(bigStudentTestIdSnowflakeWithIdPOs.values());
    //
    //     curdOneIdDao.deleteSkipLogicAll();
    // }
    //
    // @Test
    // void testUpdate() {
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(1L).name("test1").build());
    //     curdOneIdDao.updateWithVersionById(CurdOneIdPO.builder().id(1L).name("test2").build());
    //     curdOneIdDao.deleteSkipLogicById(1L);
    //
    //     // =============
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(1L).name("test1").build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(2L).name("test2").build());
    //     curdOneIdDao.updateWithVersionById(List.of(CurdOneIdPO.builder().id(1L).name("test3").build(), CurdOneIdPO.builder().id(2L).name("test4").build()), CurdOneIdPO::getId);
    //     curdOneIdDao.deleteSkipLogicByIds(1L, 2L);
    //
    //     // =============
    //
    //     curdTwoIdDao.save(CurdTwoIdPO.builder().id(1L).id2(2L).name("test1").build());
    //     curdTwoIdDao.updateWithVersionById(CurdTwoIdPO.builder().id(1L).id2(2L).name("test2").build());
    //     curdTwoIdDao.deleteSkipLogicById(CurdTwoIdPO.builder().id(1L).id2(2L).build());
    // }
    //
    // @Test
    // void testDelete() {
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(10L).build());
    //     curdOneIdDao.deleteSkipLogicById(10L);
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(11L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(12L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(13L).build());
    //     curdOneIdDao.deleteSkipLogicByIds(11L, 12L, 13L);
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(14L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(15L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(16L).build());
    //     curdOneIdDao.deleteSkipLogicByIds(Collections.ofHashSet(14L, 15L, 16L));
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(17L).build());
    //     curdOneIdDao.deleteSkipLogicByCondition(QueryWrapper.create().where(STUDENT_TEST_ID_SNOWFLAKE.ID.equalsTo(17L)));
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(18L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(19L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(20L).build());
    //     curdOneIdDao.deleteSkipLogicByIds(Collections.ofHashSet(18L, 19L, 20L));
    //
    //     // =============
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(10L).build());
    //     curdOneIdDao.deleteById(10L);
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(11L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(12L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(13L).build());
    //     curdOneIdDao.deleteByIds(11L, 12L, 13L);
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(14L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(15L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(16L).build());
    //     curdOneIdDao.deleteByIds(Collections.ofHashSet(14L, 15L, 16L));
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(17L).build());
    //     curdOneIdDao.deleteByCondition(QueryWrapper.create().where(STUDENT_TEST_ID_SNOWFLAKE.ID.equalsTo(17L)));
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(18L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(19L).build());
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(20L).build());
    //     curdOneIdDao.deleteAll();
    //
    //     // =============
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(21L).build());
    //     curdOneIdDao.deleteSkipLogicById(21L);
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(22L).build());
    //     curdOneIdDao.deleteById(22L);
    //     curdOneIdDao.deleteSkipLogicById(22L);
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(22L).build());
    //     curdOneIdDao.deleteById(22L);
    //
    //     curdOneIdDao.save(CurdOneIdPO.builder().id(21L).build());
    //     curdOneIdDao.deleteSkipLogicById(21L);
    //     curdOneIdDao.deleteSkipLogicAll();
    //
    //     // =============
    //
    //     curdTwoIdDao.save(CurdTwoIdPO.builder().id(1L).id2(2L).build());
    //     curdTwoIdDao.deleteSkipLogicById(CurdTwoIdPO.builder().id(1L).id2(2L).build());
    // }

    @Test
    void testSelect() {
        // curdOneIdDao.getByCondition();
        // CurdOneIdDao
        // TypeUtil.getGenerics(CurdOneIdDao.class);
        Types.getClassGenericType(CurdOneIdDao.class);

        List<JoinOnePO> c = joinOneDao.openQuery()
                .innerJoin(JoinTwoPO.class).on(JoinOnePO::getId, JoinTwoPO::getJoinOneId)
                .innerJoin(JoinTwoPO.class).on(JoinOnePO::getId, JoinTwoPO::getJoinOneId, JoinOnePO::getId, JoinTwoPO::getJoinOneId, JoinOnePO::getId, JoinTwoPO::getJoinOneId, JoinOnePO::getId, JoinTwoPO::getJoinOneId)
                .leftJoin(CurdOneIdPO.class).on(queryChainer -> queryChainer.and(CurdOneIdPO::getId).equalsTo(JoinOnePO::getId))
                .where(JoinOnePO::getId).equalsTo(23L)
                .and(CurdOneIdPO::getName).equalsTo("11")
                .list();

        joinOneDao.save(JoinOnePO.builder().id(1L).joinTwoId(1L).build());
        joinTwoDao.save(JoinTwoPO.builder().id(1L).joinOneId(1L).build());
        //
        // UpdateChain.of(JoinOnePO.class)
        //         .set(JoinOnePO::getId, "1")
        //         .set(JOIN_ONE.ID, "1")
        //         .where(JOIN_ONE.ID.equalsTo(1L))
        //         .and(JOIN_TWO.ID.equalsTo(1L))
        //         .where(CurdOneIdPO::getId).eq(23L)
        //         .and(CurdOneIdPO::getId).eq(23L)
        //         .update();
        //
        // List<JoinOnePO> b = joinOneDao.openQuery()
        //         .leftJoin(JOIN_TWO).on(JOIN_ONE.ID.equalsTo(JOIN_TWO.JOIN_ONE_ID))
        //         .where(JOIN_ONE.ID.equalsTo(1L))
        //         .and(JOIN_TWO.ID.equalsTo(1L))
        //         .list();
        //
        // List<CurdOneIdPO> a = curdOneIdDao.openQuery()
        //         // .where(CURD_ONE_ID.ID.equalsTo(21))
        //         // .leftJoin(CurdTwoIdPO.class).on(CURD_ONE_ID.ID.equalsTo(CURD_TWO_ID.ID))
        //         .where(CurdOneIdPO::getId).equalsTo(23L)
        //         // .where(CurdOneIdPO::getId).eq(CurdTwoIdPO::getId)
        //         .where(CurdOneIdPO::getId).eq(CurdOneIdPO::getId)
        //         // .where(CurdOneIdPO::getId).eq(21)
        //         // .and("")
        //         .list();
        //
        // QueryChain.of(CurdOneIdPO.class)
        //         .leftJoin(CurdTwoIdPO.class).on(CURD_ONE_ID.ID.equalsTo(CURD_TWO_ID.ID))
        //         .where(CurdOneIdPO::getId).eq(CurdTwoIdPO::getId)
        //         .where(CURD_ONE_ID.ID.equalsTo(100))
        //         .list();
        //
        // // Proxy.newProxyInstance(CurdOneIdDao.class.getClassLoader()
        // //         , new Class[]{CurdOneIdDao.class}
        // //         , new Mappers.MapperHandler(mapperClass));
        // //
        // // Object mapperObject = MapUtil.computeIfAbsent(MAPPER_OBJECTS, mapperClass, clazz ->
        // //         Proxy.newProxyInstance(mapperClass.getClassLoader()
        // //                 , new Class[]{mapperClass}
        // //                 , new Mappers.MapperHandler(mapperClass)));
        // // return (M) mapperObject;
        //
        // // 更新数据
        // UpdateChain.of(CurdTwoIdPO.class)
        //         .set(CurdTwoIdPO::getName, "22")
        //         .where(CurdTwoIdPO::getId).ge(100)
        //         .and(CurdTwoIdPO::getName).eq("33")
        //         .update();
        //
        // // // 查询所有数据并打印
        // QueryChain.of(CurdTwoIdPO.class)
        //         .where(CurdTwoIdPO::getId).ge(100)
        //         .and(CurdTwoIdPO::getName).eq(18)
        //         .list()
        //         .forEach(System.out::println);
    }

}
