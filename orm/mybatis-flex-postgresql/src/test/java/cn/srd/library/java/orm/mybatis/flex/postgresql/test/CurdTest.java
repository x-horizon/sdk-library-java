// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.test;

import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.HomeDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.PeopleDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.HomePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.PeoplePO;
import cn.srd.library.java.tool.id.snowflake.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIdEnvironment;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.object.Nil;
import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@MapperScan("cn.srd.library.java.orm.mybatis.flex.postgresql.**")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
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
@EnableAspectJAutoProxy(exposeProxy = true)
class CurdTest {

    @Autowired private PeopleDao peopleDao;

    @Autowired private HomeDao homeDao;

    private static final String HOME_NAME_1 = "home1";

    private static final String HOME_NAME_2 = "home2";

    private static final String HOME_NAME_3 = "home3";

    private static final String HOME_NAME_4 = "home4";

    private static final String HOME_NAME_5 = "home5";

    private static final String HOME_NAME_6 = "home6";

    private static final String HOME_NAME_7 = "home7";

    private static final String HOME_NAME_8 = "home8";

    private static final String HOME_NAME_9 = "home9";

    private static final String HOME_NAME_10 = "home10";

    private static final String HOME_NAME_11 = "home11";

    private static final String HOME_NAME_12 = "home12";

    private static final String HOME_NAME_13 = "home13";

    private static final String HOME_NAME_14 = "home14";

    private static final String HOME_NAME_15 = "home15";

    private static final String PEOPLE_NAME_1 = "people1";

    private static final String PEOPLE_NAME_2 = "people2";

    private static final String PEOPLE_NAME_3 = "people3";

    private static final String PEOPLE_NAME_4 = "people4";

    private static final String PEOPLE_NAME_5 = "people5";

    private static final String PEOPLE_NAME_6 = "people6";

    private static final String PEOPLE_NAME_7 = "people7";

    private static final String PEOPLE_NAME_8 = "people8";

    private static final String PEOPLE_NAME_9 = "people9";

    private static final String PEOPLE_NAME_10 = "people10";

    private static final String PEOPLE_NAME_11 = "people11";

    private static final String PEOPLE_NAME_12 = "people12";

    private static final String PEOPLE_NAME_13 = "people13";

    private static final String PEOPLE_NAME_14 = "people14";

    private static final String PEOPLE_NAME_15 = "people15";

    @Test
    void testSave() {
        testDelete();
        homeDao.save(HomePO.builder().name(HOME_NAME_1).build());
        homeDao.save(HomePO.builder().name(HOME_NAME_2).build());
        homeDao.save(HomePO.builder().name(HOME_NAME_3).build());
        homeDao.save(HomePO.builder().name(HOME_NAME_4).build());
        homeDao.save(HomePO.builder().name(HOME_NAME_5).build());
        homeDao.saveBatch(Collections.ofArrayList(
                HomePO.builder().name(HOME_NAME_6).build(),
                HomePO.builder().name(HOME_NAME_7).build(),
                HomePO.builder().name(HOME_NAME_8).build(),
                HomePO.builder().name(HOME_NAME_9).build(),
                HomePO.builder().name(HOME_NAME_10).build()
        ));
        homeDao.saveBatch(Collections.ofArrayList(
                HomePO.builder().name(HOME_NAME_11).build(),
                HomePO.builder().name(HOME_NAME_12).build(),
                HomePO.builder().name(HOME_NAME_13).build(),
                HomePO.builder().name(HOME_NAME_14).build(),
                HomePO.builder().name(HOME_NAME_15).build()
        ), 2);

        List<HomePO> homePOs = homeDao.listAll();
        Map<String, Long> homeNameMappingHomeIdMap = Converts.toMap(homePOs, HomePO::getName, HomePO::getId);
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_1)).name(PEOPLE_NAME_1).build());
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_2)).name(PEOPLE_NAME_2).build());
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_3)).name(PEOPLE_NAME_3).build());
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_4)).name(PEOPLE_NAME_4).build());
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_5)).name(PEOPLE_NAME_5).build());
        peopleDao.saveBatch(Collections.ofArrayList(
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_6)).name(PEOPLE_NAME_6).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_7)).name(PEOPLE_NAME_7).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_8)).name(PEOPLE_NAME_8).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_9)).name(PEOPLE_NAME_9).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_10)).name(PEOPLE_NAME_10).build()
        ));
        peopleDao.saveBatch(Collections.ofArrayList(
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_11)).name(PEOPLE_NAME_11).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_12)).name(PEOPLE_NAME_12).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_13)).name(PEOPLE_NAME_13).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_14)).name(PEOPLE_NAME_14).build(),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_15)).name(PEOPLE_NAME_15).build()
        ), 2);
    }

    @Test
    void testUpdate() {
        testSave();
        List<HomePO> homePOs = homeDao.listAll().stream().map(homePO -> homePO.setName(homePO.getName() + "*")).toList();
        HomePO theFirstHomePO = Collections.getFirst(homePOs).orElseThrow();
        homeDao.updateById(theFirstHomePO);
        homeDao.updateBatchById(theFirstHomePO);
        homeDao.updateBatchById(homePOs);
        homeDao.updateBatchById(homePOs, 2);

        List<PeoplePO> peoplePOs = peopleDao.listAll().stream().map(peoplePO -> peoplePO.setName(peoplePO.getName() + "*")).toList();
        PeoplePO theFirstPeoplePO = Collections.getFirst(peoplePOs).orElseThrow();
        peopleDao.updateWithVersionById(theFirstPeoplePO);
        peopleDao.updateBatchWithVersionById(peoplePOs, PeoplePO::getId);
        peopleDao.updateBatchWithVersionById(peoplePOs, PeoplePO::getId, 2);
    }
 
    @Test
    void testDelete() {
        List<HomePO> homePOs = homeDao.listAll();
        List<Long> homeIds = Converts.toList(homePOs, HomePO::getId);
        if (Nil.isNotEmpty(homeIds)) {
            Long theFirstHomeId = Collections.getFirst(homeIds).orElseThrow();
            homeDao.deleteById(theFirstHomeId);
            HomePO theSecondHomePO = Collections.getSecond(homePOs).orElseThrow();
            homeDao.deleteById(theSecondHomePO);
            homeDao.deleteByIds(homeIds);

            Long theThirdHomeId = Collections.getThird(homeIds).orElseThrow();
            homeDao.deleteSkipLogicById(theThirdHomeId);
            HomePO theForthHomePO = Collections.getForth(homePOs).orElseThrow();
            homeDao.deleteSkipLogicById(theForthHomePO);
            homeDao.deleteSkipLogicByIds(homeIds);
        }

        List<PeoplePO> peoplePOs = peopleDao.listAll();
        List<Long> peopleIds = Converts.toList(peoplePOs, PeoplePO::getId);
        if (Nil.isNotEmpty(peopleIds)) {
            Long theFirstPeopleId = Collections.getFirst(peopleIds).orElseThrow();
            peopleDao.deleteById(theFirstPeopleId);
            PeoplePO theSecondPeoplePO = Collections.getSecond(peoplePOs).orElseThrow();
            peopleDao.deleteById(theSecondPeoplePO);
            peopleDao.deleteByIds(peopleIds);

            Long theThirdPeopleId = Collections.getThird(peopleIds).orElseThrow();
            peopleDao.deleteSkipLogicById(theThirdPeopleId);
            PeoplePO theForthPeoplePO = Collections.getForth(peoplePOs).orElseThrow();
            peopleDao.deleteSkipLogicById(theForthPeoplePO);
            peopleDao.deleteSkipLogicByIds(peopleIds);
        }
    }

    @Test
    void testCurd() {
        // HomeWithVersionPO homeWithVersionPO2 = homeWithVersionDao.getById(1L).orElseThrow();
        // Long homeId = 1L;
        // String homeName = "home";
        // HomeWithVersionPO homeWithVersionPO = HomeWithVersionPO.builder().id(1L).name(homeName).build();
        //
        // Long bedId1 = 1L;
        // Long bedId2 = 2L;
        // Long bedId3 = 3L;
        // Long bedId4 = 4L;
        // String bedName1 = "bed1";
        // String bedName2 = "bed2";
        // String bedName3 = "bed3";
        // String bedName4 = "bed4";
        // String bedNameBatch = "bed";
        // BedWithVersionPO bedWithVersionPO1 = BedWithVersionPO.builder().id(bedId1).homeId(homeId).name(bedName1).build();
        // BedWithVersionPO bedWithVersionPO2 = BedWithVersionPO.builder().id(bedId2).homeId(homeId).name(bedName2).build();
        // BedWithVersionPO bedWithVersionPO3 = BedWithVersionPO.builder().id(bedId3).homeId(homeId).name(bedName3).build();
        // BedWithVersionPO bedWithVersionPO4 = BedWithVersionPO.builder().id(bedId4).homeId(homeId).name(bedName4).build();
        // Collection<BedWithVersionPO> collectionTypeAndUsingDisposableSQLBedWithVersionPOS = Collections.ofImmutableMap(bedId1, bedWithVersionPO1, bedId2, bedWithVersionPO2).values();
        // Collection<BedWithVersionPO> collectionTypeAndUsingJDBCSQLBedWithVersionPOS = IntStream.range(0, 500).mapToObj(ignore -> BedWithVersionPO.builder().homeId(homeId).name(bedNameBatch + Randoms.getNumber()).build()).collect(Collectors.toUnmodifiableList());
        // List<BedWithVersionPO> listTypeAndUsingDisposableSQLBedWithVersionPOS = Collections.ofImmutableList(bedWithVersionPO3, bedWithVersionPO4);
        //
        // Long doorId1 = 1L;
        // Long doorId2 = 2L;
        // Long doorId3 = 3L;
        // String doorName1 = "door1";
        // String doorName2 = "door2";
        // String doorName3 = "door3";
        // MaterialType woodinessMaterialType = MaterialType.WOODINESS;
        // MaterialType steelMaterialType = MaterialType.STEEL;
        //
        // Long keyId1 = 1L;
        // Long keyId2 = 2L;
        // Long keyId3 = 3L;
        // String keyName1 = "key1";
        // String keyName2 = "key2";
        // String keyName3 = "key3";
        //
        // // ==================================== test save ====================================
        // // HomePO afterSaveHomePO = homeDao.save(homePO);
        // //
        // // List<BedPO> afterSaveCollectionTypeByUsingDisposableSQLBedPOs = bedDao.save(collectionTypeAndUsingDisposableSQLBedPOs);
        // // List<BedPO> afterSaveCollectionTypeByUsingJDBCSQLBedPOs = bedDao.save(collectionTypeAndUsingJDBCSQLBedPOs);
        // // List<BedPO> afterSaveListTypeByUsingJDBCSQLBedPOs = bedDao.save(listTypeAndUsingDisposableSQLBedPOs);
        // //
        // // homeDao.openDelete().all().deleteSkipLogic();
        // // bedDao.openDelete().all().deleteSkipLogic();
    }

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
    //
    // @Test
    // void testSelect() {
    //     // curdOneIdDao.getByCondition();
    //     // CurdOneIdDao
    //     // TypeUtil.getGenerics(CurdOneIdDao.class);
    //     Types.getClassGenericType(CurdOneIdDao.class);
    //
    //     // List<JoinOnePO> c = joinOneDao.openQuery()
    //     //         .innerJoin(JoinTwoPO.class).onEquals(JoinOnePO::getId, JoinTwoPO::getJoinOneId)
    //     //         .innerJoin(JoinTwoPO.class).onEquals(JoinOnePO::getId, JoinTwoPO::getJoinOneId, JoinOnePO::getId, JoinTwoPO::getJoinOneId, JoinOnePO::getId, JoinTwoPO::getJoinOneId, JoinOnePO::getId, JoinTwoPO::getJoinOneId)
    //     //         .leftJoin(CurdOneIdPO.class).on(queryChainer -> queryChainer.and(CurdOneIdPO::getId).equalsTo(JoinOnePO::getId))
    //     //         .where(JoinOnePO::getId).equalsTo(23L)
    //     //         .and(CurdOneIdPO::getName).equalsTo("11")
    //     //         .list();
    //
    //     // joinOneDao.save(JoinOnePO.builder().id(1L).joinTwoId(1L).build());
    //     // joinTwoDao.save(JoinTwoPO.builder().id(1L).joinOneId(1L).build());
    //
    //     // joinOneDao.openUpdate()
    //     //         .set(JoinOnePO::getName, "333")
    //     //         .where(JoinOnePO::getId).equalsTo(23L)
    //     //         .and(JoinOnePO::getName).equalsTo("11")
    //     //         .update();
    //
    //     // Mappers.ofEntityClass(JoinTwoPO.class);
    //     // Mappers.ofEntityClass(JoinOnePO.class);
    //     //
    //     // joinOneDao.selectListByQuery(QueryWrapper.create());
    //
    //     joinTwoDao.openDelete()
    //             .all()
    //             // .where(JoinTwoPO::getId).equalsTo(23L)
    //             // .and(JoinTwoPO::getName).equalsTo("11")
    //             .delete();
    //     // .toSQL();
    //     // .deleteSkipLogic();
    //
    //     System.out.println();
    //
    //     //
    //     // UpdateChain.of(JoinOnePO.class)
    //     //         .set(JoinOnePO::getId, "1")
    //     //         .set(JOIN_ONE.ID, "1")
    //     //         .where(JOIN_ONE.ID.equalsTo(1L))
    //     //         .and(JOIN_TWO.ID.equalsTo(1L))
    //     //         .where(CurdOneIdPO::getId).eq(23L)
    //     //         .and(CurdOneIdPO::getId).eq(23L)
    //     //         .update();
    //     //
    //     // List<JoinOnePO> b = joinOneDao.openQuery()
    //     //         .leftJoin(JOIN_TWO).on(JOIN_ONE.ID.equalsTo(JOIN_TWO.JOIN_ONE_ID))
    //     //         .where(JOIN_ONE.ID.equalsTo(1L))
    //     //         .and(JOIN_TWO.ID.equalsTo(1L))
    //     //         .list();
    //     //
    //     // List<CurdOneIdPO> a = curdOneIdDao.openQuery()
    //     //         // .where(CURD_ONE_ID.ID.equalsTo(21))
    //     //         // .leftJoin(CurdTwoIdPO.class).on(CURD_ONE_ID.ID.equalsTo(CURD_TWO_ID.ID))
    //     //         .where(CurdOneIdPO::getId).equalsTo(23L)
    //     //         // .where(CurdOneIdPO::getId).eq(CurdTwoIdPO::getId)
    //     //         .where(CurdOneIdPO::getId).eq(CurdOneIdPO::getId)
    //     //         // .where(CurdOneIdPO::getId).eq(21)
    //     //         // .and("")
    //     //         .list();
    //     //
    //     // QueryChain.of(CurdOneIdPO.class)
    //     //         .leftJoin(CurdTwoIdPO.class).on(CURD_ONE_ID.ID.equalsTo(CURD_TWO_ID.ID))
    //     //         .where(CurdOneIdPO::getId).eq(CurdTwoIdPO::getId)
    //     //         .where(CURD_ONE_ID.ID.equalsTo(100))
    //     //         .list();
    //     //
    //     // // Proxy.newProxyInstance(CurdOneIdDao.class.getClassLoader()
    //     // //         , new Class[]{CurdOneIdDao.class}
    //     // //         , new Mappers.MapperHandler(mapperClass));
    //     // //
    //     // // Object mapperObject = MapUtil.computeIfAbsent(MAPPER_OBJECTS, mapperClass, clazz ->
    //     // //         Proxy.newProxyInstance(mapperClass.getClassLoader()
    //     // //                 , new Class[]{mapperClass}
    //     // //                 , new Mappers.MapperHandler(mapperClass)));
    //     // // return (M) mapperObject;
    //     //
    //     // // 更新数据
    //     // UpdateChain.of(CurdTwoIdPO.class)
    //     //         .set(CurdTwoIdPO::getName, "22")
    //     //         .where(CurdTwoIdPO::getId).ge(100)
    //     //         .and(CurdTwoIdPO::getName).eq("33")
    //     //         .update();
    //     //
    //     // // // 查询所有数据并打印
    //     // QueryChain.of(CurdTwoIdPO.class)
    //     //         .where(CurdTwoIdPO::getId).ge(100)
    //     //         .and(CurdTwoIdPO::getName).eq(18)
    //     //         .list()
    //     //         .forEach(System.out::println);
    // }

}