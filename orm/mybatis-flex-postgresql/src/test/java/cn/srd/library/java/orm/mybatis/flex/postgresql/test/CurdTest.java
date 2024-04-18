// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.test;

import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdConfig;
import cn.srd.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import cn.srd.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import cn.srd.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import cn.srd.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import cn.srd.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestInsertListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.config.TestUpdateListener;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.HomeDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.PeopleDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.HomePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.PeoplePO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo.HomeVO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.vo.PeopleVO;
import cn.srd.library.java.orm.mybatis.flex.postgresql.service.HomeService;
import cn.srd.library.java.orm.mybatis.flex.postgresql.service.PeopleService;
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

// @MapperScan("cn.srd.library.java.**.dao.**")
@MapperScan("cn.srd.library.java.orm.mybatis.flex.postgresql.**")
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
        globalListenerConfig = @ListenerConfig(whenInsert = TestInsertListener.class, whenUpdate = TestUpdateListener.class),
        globalOptimisticLockConfig = @OptimisticLockConfig(columnName = "version"),
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

    @Autowired private HomeService homeService;

    @Autowired private HomeDao homeDao;

    @Autowired private PeopleService peopleService;

    @Autowired private PeopleDao peopleDao;

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
        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152157125, 'home1', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152222661, 'home2', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152243141, 'home3', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152255429, 'home4', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152271813, 'home5', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        homeDao.save(HomePO.builder().name(HOME_NAME_1).build());
        homeDao.save(HomePO.builder().name(HOME_NAME_2).build());
        homeDao.save(HomePO.builder().name(HOME_NAME_3).build());
        homeDao.save(HomePO.builder().name(HOME_NAME_4).build());
        HomeVO homeVO = homeService.save(HomeVO.builder().name(HOME_NAME_5).build());

        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152329157, 'home6', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152329158, 'home7', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152329159, 'home8', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152329160, 'home9', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152329161, 'home10', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        homeDao.saveBatch(Collections.ofArrayList(
                HomePO.builder().name(HOME_NAME_6).build(),
                HomePO.builder().name(HOME_NAME_7).build(),
                HomePO.builder().name(HOME_NAME_8).build(),
                HomePO.builder().name(HOME_NAME_9).build(),
                HomePO.builder().name(HOME_NAME_10).build()
        ));

        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152406981, 'home11', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152406982, 'home12', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152419269, 'home13', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152423365, 'home14', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        // INSERT INTO "home"("id", "name", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152439749, 'home15', 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        homeDao.saveBatch(Collections.ofArrayList(
                HomePO.builder().name(HOME_NAME_11).build(),
                HomePO.builder().name(HOME_NAME_12).build(),
                HomePO.builder().name(HOME_NAME_13).build(),
                HomePO.builder().name(HOME_NAME_14).build(),
                HomePO.builder().name(HOME_NAME_15).build()
        ), 2);

        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152509381, 536748152157125, 'people1', 'people1', 'people1', 'people1', 'people1', 'people1', 'people1', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152529861, 536748152222661, 'people2', 'people2', 'people2', 'people2', 'people2', 'people2', 'people2', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152546245, 536748152243141, 'people3', 'people3', 'people3', 'people3', 'people3', 'people3', 'people3', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152562629, 536748152255429, 'people4', 'people4', 'people4', 'people4', 'people4', 'people4', 'people4', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time")
        // VALUES (536748152579013, 536748152271813, 'people5', 'people5', 'people5', 'people5', 'people5', 'people5', 'people5', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51');
        List<HomePO> homePOs = homeDao.listAll();
        Map<String, Long> homeNameMappingHomeIdMap = Converts.toMap(homePOs, HomePO::getName, HomePO::getId);
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_1)).build().setAllName(PEOPLE_NAME_1));
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_2)).build().setAllName(PEOPLE_NAME_2));
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_3)).build().setAllName(PEOPLE_NAME_3));
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_4)).build().setAllName(PEOPLE_NAME_4));
        PeopleVO peopleVO = peopleService.save(PeopleVO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_5)).build().setAllName(PEOPLE_NAME_5));

        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152595397, 536748152329157, 'people6', 'people6', 'people6', 'people6', 'people6', 'people6', 'people6', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152595398, 536748152329158, 'people7', 'people7', 'people7', 'people7', 'people7', 'people7', 'people7', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152595399, 536748152329159, 'people8', 'people8', 'people8', 'people8', 'people8', 'people8', 'people8', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152595400, 536748152329160, 'people9', 'people9', 'people9', 'people9', 'people9', 'people9', 'people9', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152595401, 536748152329161, 'people10', 'people10', 'people10', 'people10', 'people10', 'people10', 'people10', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        peopleDao.saveBatch(Collections.ofArrayList(
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_6)).build().setAllName(PEOPLE_NAME_6),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_7)).build().setAllName(PEOPLE_NAME_7),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_8)).build().setAllName(PEOPLE_NAME_8),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_9)).build().setAllName(PEOPLE_NAME_9),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_10)).build().setAllName(PEOPLE_NAME_10)
        ));

        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152624069, 536748152406981, 'people11', 'people11', 'people11', 'people11', 'people11', 'people11', 'people11', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152624070, 536748152406982, 'people12', 'people12', 'people12', 'people12', 'people12', 'people12', 'people12', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152640453, 536748152419269, 'people13', 'people13', 'people13', 'people13', 'people13', 'people13', 'people13', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL),
        //        (536748152640454, 536748152423365, 'people14', 'people14', 'people14', 'people14', 'people14', 'people14', 'people14', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        // INSERT INTO "people"("id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time", "delete_time")
        // VALUES (536748152660933, 536748152439749, 'people15', 'people15', 'people15', 'people15', 'people15', 'people15', 'people15', 0, 1, 1, '2024-04-15 18:53:51', '2024-04-15 18:53:51', NULL);
        peopleDao.saveBatch(Collections.ofArrayList(
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_11)).build().setAllName(PEOPLE_NAME_11),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_12)).build().setAllName(PEOPLE_NAME_12),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_13)).build().setAllName(PEOPLE_NAME_13),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_14)).build().setAllName(PEOPLE_NAME_14),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_15)).build().setAllName(PEOPLE_NAME_15)
        ), 2);
    }

    @Test
    void testUpdate() {
        testDelete();
        testSave();

        List<HomePO> homePOs = homeDao.listAll().stream().peek(homePO -> homePO.setName(homePO.getName() + "*")).toList();
        HomePO theFirstHomePO = Collections.getFirst(homePOs).orElseThrow();
        // UPDATE "home"
        // SET "name"        = 'home1*',
        //     "creator_id"  = 1,
        //     "updater_id"  = 1,
        //     "create_time" = '2024-04-15 19:02:13',
        //     "update_time" = '2024-04-15 19:02:19'
        // WHERE "id" = 536750208472197
        //   AND "delete_time" IS NULL;
        homeDao.updateById(theFirstHomePO);
        // UPDATE "home"
        // SET "name"        = 'home1*',
        //     "creator_id"  = 1,
        //     "updater_id"  = 1,
        //     "create_time" = '2024-04-15 19:02:13',
        //     "update_time" = '2024-04-15 19:02:45'
        // WHERE "id" = 536750208472197
        //   AND "delete_time" IS NULL;
        homeDao.updateBatchById(theFirstHomePO);
        // UPDATE "home" SET "name" = 'home1*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208472197  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home2*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208525445  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home3*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208558213  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home4*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208582789  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home5*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208607365  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home6*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208668805  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home7*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208668806  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home8*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208668807  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home9*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208668808  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home10*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208668809  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home11*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208742533  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home12*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208742534  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home13*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208771205  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home14*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208771206  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home15*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:14'  WHERE "id" = 536750208787589  AND "delete_time" IS NULL;
        homeDao.updateBatchById(homePOs);
        // UPDATE "home" SET "name" = 'home1*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208472197  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home2*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208525445  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home3*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208558213  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home4*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208582789  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home5*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208607365  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home6*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208668805  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home7*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208668806  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home8*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208668807  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home9*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208668808  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home10*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208668809  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home11*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208742533  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home12*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208742534  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home13*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208771205  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home14*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208771206  AND "delete_time" IS NULL;
        // UPDATE "home" SET "name" = 'home15*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:02:13' , "update_time" = '2024-04-15 19:03:42'  WHERE "id" = 536750208787589  AND "delete_time" IS NULL;
        homeDao.updateBatchById(homePOs, 2);

        List<PeoplePO> peoplePOs = peopleDao.listAll().stream().map(peoplePO -> peoplePO.setAllName(peoplePO.getName1() + "*")).toList();
        PeoplePO theFirstPeoplePO = Collections.getFirst(peoplePOs).orElseThrow();
        // SELECT * FROM "people" WHERE "id" = 536750845867205  AND "delete_time" IS NULL;
        // UPDATE "people" SET "home_id" = 536750845560005 , "name1" = 'people1*' , "name2" = 'people1*' , "name3" = 'people1*' , "name4" = 'people1*' , "name5" = 'people1*' , "name6" = 'people1*' , "name7" = 'people1*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:05:01' , "version" = "version" + 1  WHERE "id" = 536750845867205  AND "delete_time" IS NULL AND "version" = 0;
        peopleDao.updateWithVersionById(theFirstPeoplePO);
        // SELECT "id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time", "delete_time" FROM "people" WHERE ("id" = 536750845867205  OR "id" = 536750845904069  OR "id" = 536750845920453  OR "id" = 536750845940933  OR "id" = 536750845961413  OR "id" = 536750845990085  OR "id" = 536750845990086  OR "id" = 536750845994181  OR "id" = 536750845994182  OR "id" = 536750845998277  OR "id" = 536750846047429  OR "id" = 536750846047430  OR "id" = 536750846067909  OR "id" = 536750846067910  OR "id" = 536750846096581 ) AND "delete_time" IS NULL;
        // UPDATE "people" SET "home_id" = 536750845560005 , "name1" = 'people1*' , "name2" = 'people1*' , "name3" = 'people1*' , "name4" = 'people1*' , "name5" = 'people1*' , "name6" = 'people1*' , "name7" = 'people1*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845867205  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845625541 , "name1" = 'people2*' , "name2" = 'people2*' , "name3" = 'people2*' , "name4" = 'people2*' , "name5" = 'people2*' , "name6" = 'people2*' , "name7" = 'people2*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845904069  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845641925 , "name1" = 'people3*' , "name2" = 'people3*' , "name3" = 'people3*' , "name4" = 'people3*' , "name5" = 'people3*' , "name6" = 'people3*' , "name7" = 'people3*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845920453  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845662405 , "name1" = 'people4*' , "name2" = 'people4*' , "name3" = 'people4*' , "name4" = 'people4*' , "name5" = 'people4*' , "name6" = 'people4*' , "name7" = 'people4*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845940933  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845674693 , "name1" = 'people5*' , "name2" = 'people5*' , "name3" = 'people5*' , "name4" = 'people5*' , "name5" = 'people5*' , "name6" = 'people5*' , "name7" = 'people5*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845961413  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845732037 , "name1" = 'people6*' , "name2" = 'people6*' , "name3" = 'people6*' , "name4" = 'people6*' , "name5" = 'people6*' , "name6" = 'people6*' , "name7" = 'people6*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845990085  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845732038 , "name1" = 'people7*' , "name2" = 'people7*' , "name3" = 'people7*' , "name4" = 'people7*' , "name5" = 'people7*' , "name6" = 'people7*' , "name7" = 'people7*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845990086  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845732039 , "name1" = 'people8*' , "name2" = 'people8*' , "name3" = 'people8*' , "name4" = 'people8*' , "name5" = 'people8*' , "name6" = 'people8*' , "name7" = 'people8*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845994181  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845732040 , "name1" = 'people9*' , "name2" = 'people9*' , "name3" = 'people9*' , "name4" = 'people9*' , "name5" = 'people9*' , "name6" = 'people9*' , "name7" = 'people9*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845994182  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845732041 , "name1" = 'people10*' , "name2" = 'people10*' , "name3" = 'people10*' , "name4" = 'people10*' , "name5" = 'people10*' , "name6" = 'people10*' , "name7" = 'people10*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750845998277  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845785285 , "name1" = 'people11*' , "name2" = 'people11*' , "name3" = 'people11*' , "name4" = 'people11*' , "name5" = 'people11*' , "name6" = 'people11*' , "name7" = 'people11*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750846047429  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845785286 , "name1" = 'people12*' , "name2" = 'people12*' , "name3" = 'people12*' , "name4" = 'people12*' , "name5" = 'people12*' , "name6" = 'people12*' , "name7" = 'people12*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750846047430  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845801669 , "name1" = 'people13*' , "name2" = 'people13*' , "name3" = 'people13*' , "name4" = 'people13*' , "name5" = 'people13*' , "name6" = 'people13*' , "name7" = 'people13*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750846067909  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845801670 , "name1" = 'people14*' , "name2" = 'people14*' , "name3" = 'people14*' , "name4" = 'people14*' , "name5" = 'people14*' , "name6" = 'people14*' , "name7" = 'people14*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750846067910  AND "delete_time" IS NULL AND "version" = 0;
        // UPDATE "people" SET "home_id" = 536750845813957 , "name1" = 'people15*' , "name2" = 'people15*' , "name3" = 'people15*' , "name4" = 'people15*' , "name5" = 'people15*' , "name6" = 'people15*' , "name7" = 'people15*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:05:23' , "version" = "version" + 1  WHERE "id" = 536750846096581  AND "delete_time" IS NULL AND "version" = 0;
        peopleDao.updateBatchWithVersionById(peoplePOs, PeoplePO::getId);
        // SELECT "id", "home_id", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "version", "creator_id", "updater_id", "create_time", "update_time", "delete_time" FROM "people" WHERE ("id" = 536750845867205  OR "id" = 536750845904069  OR "id" = 536750845920453  OR "id" = 536750845940933  OR "id" = 536750845961413  OR "id" = 536750845990085  OR "id" = 536750845990086  OR "id" = 536750845994181  OR "id" = 536750845994182  OR "id" = 536750845998277  OR "id" = 536750846047429  OR "id" = 536750846047430  OR "id" = 536750846067909  OR "id" = 536750846067910  OR "id" = 536750846096581 ) AND "delete_time" IS NULL;
        // UPDATE "people" SET "home_id" = 536750845560005 , "name1" = 'people1*' , "name2" = 'people1*' , "name3" = 'people1*' , "name4" = 'people1*' , "name5" = 'people1*' , "name6" = 'people1*' , "name7" = 'people1*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845867205  AND "delete_time" IS NULL AND "version" = 2;
        // UPDATE "people" SET "home_id" = 536750845625541 , "name1" = 'people2*' , "name2" = 'people2*' , "name3" = 'people2*' , "name4" = 'people2*' , "name5" = 'people2*' , "name6" = 'people2*' , "name7" = 'people2*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845904069  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845641925 , "name1" = 'people3*' , "name2" = 'people3*' , "name3" = 'people3*' , "name4" = 'people3*' , "name5" = 'people3*' , "name6" = 'people3*' , "name7" = 'people3*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845920453  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845662405 , "name1" = 'people4*' , "name2" = 'people4*' , "name3" = 'people4*' , "name4" = 'people4*' , "name5" = 'people4*' , "name6" = 'people4*' , "name7" = 'people4*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:48' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845940933  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845674693 , "name1" = 'people5*' , "name2" = 'people5*' , "name3" = 'people5*' , "name4" = 'people5*' , "name5" = 'people5*' , "name6" = 'people5*' , "name7" = 'people5*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845961413  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845732037 , "name1" = 'people6*' , "name2" = 'people6*' , "name3" = 'people6*' , "name4" = 'people6*' , "name5" = 'people6*' , "name6" = 'people6*' , "name7" = 'people6*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845990085  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845732038 , "name1" = 'people7*' , "name2" = 'people7*' , "name3" = 'people7*' , "name4" = 'people7*' , "name5" = 'people7*' , "name6" = 'people7*' , "name7" = 'people7*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845990086  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845732039 , "name1" = 'people8*' , "name2" = 'people8*' , "name3" = 'people8*' , "name4" = 'people8*' , "name5" = 'people8*' , "name6" = 'people8*' , "name7" = 'people8*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845994181  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845732040 , "name1" = 'people9*' , "name2" = 'people9*' , "name3" = 'people9*' , "name4" = 'people9*' , "name5" = 'people9*' , "name6" = 'people9*' , "name7" = 'people9*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845994182  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845732041 , "name1" = 'people10*' , "name2" = 'people10*' , "name3" = 'people10*' , "name4" = 'people10*' , "name5" = 'people10*' , "name6" = 'people10*' , "name7" = 'people10*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750845998277  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845785285 , "name1" = 'people11*' , "name2" = 'people11*' , "name3" = 'people11*' , "name4" = 'people11*' , "name5" = 'people11*' , "name6" = 'people11*' , "name7" = 'people11*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750846047429  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845785286 , "name1" = 'people12*' , "name2" = 'people12*' , "name3" = 'people12*' , "name4" = 'people12*' , "name5" = 'people12*' , "name6" = 'people12*' , "name7" = 'people12*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750846047430  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845801669 , "name1" = 'people13*' , "name2" = 'people13*' , "name3" = 'people13*' , "name4" = 'people13*' , "name5" = 'people13*' , "name6" = 'people13*' , "name7" = 'people13*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750846067909  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845801670 , "name1" = 'people14*' , "name2" = 'people14*' , "name3" = 'people14*' , "name4" = 'people14*' , "name5" = 'people14*' , "name6" = 'people14*' , "name7" = 'people14*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750846067910  AND "delete_time" IS NULL AND "version" = 1;
        // UPDATE "people" SET "home_id" = 536750845813957 , "name1" = 'people15*' , "name2" = 'people15*' , "name3" = 'people15*' , "name4" = 'people15*' , "name5" = 'people15*' , "name6" = 'people15*' , "name7" = 'people15*' , "creator_id" = 1 , "updater_id" = 1 , "create_time" = '2024-04-15 19:04:49' , "update_time" = '2024-04-15 19:06:19' , "version" = "version" + 1  WHERE "id" = 536750846096581  AND "delete_time" IS NULL AND "version" = 1;
        peopleDao.updateBatchWithVersionById(peoplePOs, PeoplePO::getId, 2);

        // UPDATE "people"
        // SET "updater_id"  = 1,
        //     "update_time" = '2024-04-15 19:07:37',
        //     "version"     = "version" + 1
        // WHERE ("creator_id" = 1 AND "updater_id" = 1)
        //   AND "delete_time" IS NULL;
        peopleDao.openUpdate()
                .where(PeoplePO::getCreatorId).equalsTo(1)
                .and(PeoplePO::getUpdaterId).equalsTo(1)
                .update();
        // UPDATE "people"
        // SET "name1"       = 'people1*-set',
        //     "name2"       = 'people1*-set-boolean-supplier',
        //     "name4"       = 'people1*-set-boolean',
        //     "name5"       = 'people1*-set-if-not-null',
        //     "name6"       = 'people1*-set-if-not-empty',
        //     "name7"       = 'people1*-set-if-not-blank',
        //     "home_id"     = 2,
        //     "updater_id"  = 1,
        //     "update_time" = '2024-04-15 19:08:18',
        //     "version"     = "version" + 1
        // WHERE ("creator_id" = 1 AND "updater_id" = 1 OR "updater_id" = 1)
        //   AND "delete_time" IS NULL;
        peopleDao.openUpdate()
                .set(PeoplePO::getName1, theFirstPeoplePO.getName1() + "-set")
                .set(PeoplePO::getName2, theFirstPeoplePO.getName2() + "-set-boolean-supplier", () -> true)
                .set(PeoplePO::getName4, theFirstPeoplePO.getName4() + "-set-boolean", ignore -> true)
                .setIfNotNull(PeoplePO::getName5, theFirstPeoplePO.getName5() + "-set-if-not-null")
                .setIfNotEmpty(PeoplePO::getName6, theFirstPeoplePO.getName6() + "-set-if-not-empty")
                .setIfNotBlank(PeoplePO::getName7, theFirstPeoplePO.getName7() + "-set-if-not-blank")
                .setIfNotZeroValue(PeoplePO::getHomeId, 2)
                .where(PeoplePO::getCreatorId).equalsTo(1)
                .and(PeoplePO::getUpdaterId).equalsTo(1)
                .or(PeoplePO::getUpdaterId).equalsTo(1)
                .update();
        // UPDATE "people"
        // SET "name1"       = 'people1*-set-2',
        //     "updater_id"  = 1,
        //     "update_time" = '2024-04-15 19:08:56',
        //     "version"     = "version" + 1
        // WHERE ("creator_id" = 1 AND "updater_id" = 1 OR "updater_id" = 1)
        //   AND "delete_time" IS NULL;
        peopleDao.openUpdate()
                .set(PeoplePO::getName1, theFirstPeoplePO.getName1() + "-set-2")
                .set(PeoplePO::getName2, theFirstPeoplePO.getName2() + "-set-boolean-supplier-2", () -> false)
                .set(PeoplePO::getName4, theFirstPeoplePO.getName4() + "-set-boolean-2", ignore -> false)
                .setIfNotNull(PeoplePO::getName5, null)
                .setIfNotEmpty(PeoplePO::getName6, "")
                .setIfNotBlank(PeoplePO::getName7, "  ")
                .setIfNotZeroValue(PeoplePO::getHomeId, 0)
                .where(PeoplePO::getCreatorId).equalsTo(1)
                .and(PeoplePO::getUpdaterId).equalsTo(1)
                .or(PeoplePO::getUpdaterId).equalsTo(1)
                .update();

        testDelete();
    }

    @Test
    void testDelete() {
        List<HomePO> homePOs = homeDao.listAll();
        List<Long> homeIds = Converts.toList(homePOs, HomePO::getId);
        if (Nil.isNotEmpty(homeIds)) {
            Long theFirstHomeId = Collections.getFirst(homeIds).orElseThrow();
            // UPDATE "home"
            // SET "delete_time" = NOW()
            // WHERE "id" = 536751840355717
            //   AND "delete_time" IS NULL;
            homeDao.deleteById(theFirstHomeId);
            HomePO theSecondHomePO = Collections.getSecond(homePOs).orElseThrow();
            // UPDATE "home"
            // SET "delete_time" = NOW()
            // WHERE "id" = 536751840429445
            //   AND "delete_time" IS NULL;
            homeDao.deleteById(theSecondHomePO);
            // UPDATE "home"
            // SET "delete_time" = NOW()
            // WHERE ("id" IS NOT NULL)
            //   AND "delete_time" IS NULL;
            homeDao.openDelete().where(HomePO::getId).isNotNull().delete();
            // UPDATE "home"
            // SET "delete_time" = NOW()
            // WHERE "id" = 536751840355717
            //    OR "id" = 536751840429445
            //    OR "id" = 536751840445829
            //    OR "id" = 536751840466309
            //    OR "id" = 536751840482693
            //    OR "id" = 536751840548229
            //    OR "id" = 536751840548230
            //    OR "id" = 536751840548231
            //    OR "id" = 536751840548232
            //    OR "id" = 536751840548233
            //    OR "id" = 536751840630149
            //    OR "id" = 536751840630150
            //    OR "id" = 536751840654725
            //    OR "id" = 536751840654726
            //    OR "id" = 536751840667013
            //     AND "delete_time" IS NULL;
            homeDao.deleteByIds(homeIds);
            Long theThirdHomeId = Collections.getThird(homeIds).orElseThrow();
            // DELETE FROM "home" WHERE "id" = 536757777872453;
            homeDao.deleteSkipLogicById(theThirdHomeId);
            HomePO theForthHomePO = Collections.getForth(homePOs).orElseThrow();
            // DELETE FROM "home" WHERE "id" = 536757777901125;
            homeDao.deleteSkipLogicById(theForthHomePO);
            // DELETE FROM "home" WHERE "id" IS NOT NULL;
            homeDao.openDelete().where(HomePO::getId).isNotNull().deleteSkipLogic();
            // DELETE
            // FROM "home"
            // WHERE "id" = 536757777798725
            //    OR "id" = 536757777860165
            //    OR "id" = 536757777872453
            //    OR "id" = 536757777901125
            //    OR "id" = 536757777921605
            //    OR "id" = 536757777966661
            //    OR "id" = 536757777966662
            //    OR "id" = 536757777966663
            //    OR "id" = 536757777966664
            //    OR "id" = 536757777966665
            //    OR "id" = 536757778015813
            //    OR "id" = 536757778015814
            //    OR "id" = 536757778032197
            //    OR "id" = 536757778032198
            //    OR "id" = 536757778052677;
            homeDao.deleteSkipLogicByIds(homeIds);
        }

        List<PeoplePO> peoplePOs = peopleDao.listAll();
        List<Long> peopleIds = Converts.toList(peoplePOs, PeoplePO::getId);
        if (Nil.isNotEmpty(peopleIds)) {
            Long theFirstPeopleId = Collections.getFirst(peopleIds).orElseThrow();
            // UPDATE "people"
            // SET "delete_time" = NOW()
            // WHERE "id" = 536757778110021
            //   AND "delete_time" IS NULL;
            peopleDao.deleteById(theFirstPeopleId);
            PeoplePO theSecondPeoplePO = Collections.getSecond(peoplePOs).orElseThrow();
            // UPDATE "people"
            // SET "delete_time" = NOW()
            // WHERE "id" = 536757778110021
            //   AND "delete_time" IS NULL;
            peopleDao.deleteById(theSecondPeoplePO);
            // UPDATE "people"
            // SET "delete_time" = NOW()
            // WHERE ("id" IS NOT NULL)
            //   AND "delete_time" IS NULL;
            peopleDao.openDelete().where(PeoplePO::getId).isNotNull().delete();
            // UPDATE "people"
            // SET "delete_time" = NOW()
            // WHERE "id" = 536757778110021
            //    OR "id" = 536757778150981
            //    OR "id" = 536757778175557
            //    OR "id" = 536757778196037
            //    OR "id" = 536757778212421
            //    OR "id" = 536757778253381
            //    OR "id" = 536757778253382
            //    OR "id" = 536757778253383
            //    OR "id" = 536757778253384
            //    OR "id" = 536757778253385
            //    OR "id" = 536757778286149
            //    OR "id" = 536757778286150
            //    OR "id" = 536757778302533
            //    OR "id" = 536757778302534
            //    OR "id" = 536757778323013
            //     AND "delete_time" IS NULL;
            peopleDao.deleteByIds(peopleIds);
            Long theThirdPeopleId = Collections.getThird(peopleIds).orElseThrow();
            // DELETE FROM "people" WHERE "id" = 536757778175557;
            peopleDao.deleteSkipLogicById(theThirdPeopleId);
            PeoplePO theForthPeoplePO = Collections.getForth(peoplePOs).orElseThrow();
            // DELETE FROM "people" WHERE "id" = 536757778196037;
            peopleDao.deleteSkipLogicById(theForthPeoplePO);
            // DELETE FROM "people" WHERE "id" IS NOT NULL;
            peopleDao.openDelete().where(PeoplePO::getId).isNotNull().deleteSkipLogic();
            // DELETE
            // FROM "people"
            // WHERE "id" = 536757778110021
            //    OR "id" = 536757778150981
            //    OR "id" = 536757778175557
            //    OR "id" = 536757778196037
            //    OR "id" = 536757778212421
            //    OR "id" = 536757778253381
            //    OR "id" = 536757778253382
            //    OR "id" = 536757778253383
            //    OR "id" = 536757778253384
            //    OR "id" = 536757778253385
            //    OR "id" = 536757778286149
            //    OR "id" = 536757778286150
            //    OR "id" = 536757778302533
            //    OR "id" = 536757778302534
            //    OR "id" = 536757778323013;
            peopleDao.deleteSkipLogicByIds(peopleIds);
        }
    }

    @Test
    void testQuery() {
        testDelete();
        testSave();

        // SELECT * FROM "home" WHERE ("home"."name" = 'home1') AND "delete_time" IS NULL LIMIT 1
        HomeVO homeVO1 = homeService.getByField(HomePO::getName, HOME_NAME_1).orElseThrow();
        // SELECT * FROM "home" WHERE (name LIKE '%home%') AND "delete_time" IS NULL
        List<HomePO> homePOs1 = homeDao.listLikeByField(HomePO::getName, "home");
        List<HomePO> homePOs2 = homeDao.listJsonbListStringLikeByField(HomePO::getName, "home");

        // SELECT * FROM "people" WHERE "delete_time" IS NULL;
        List<PeoplePO> allPeoplePOs = peopleDao.listAll();
        PeoplePO theFirstPeoplePO = Collections.getFirst(allPeoplePOs).orElseThrow();
        PeoplePO theSecondPeoplePO = Collections.getSecond(allPeoplePOs).orElseThrow();
        // SELECT * FROM "people" WHERE ("id" = 536758914377477  OR "id" = 536758914397957 ) AND "delete_time" IS NULL;
        List<PeoplePO> peoplePOs1 = peopleDao.listByIds(Collections.ofImmutableList(theFirstPeoplePO.getId(), theSecondPeoplePO.getId()));
        // SELECT * FROM "people" WHERE "id" = 536758914377477  AND "delete_time" IS NULL;
        PeoplePO peoplePO1 = peopleDao.getById(theFirstPeoplePO).orElseThrow();
        // SELECT COUNT(*) FROM "people" WHERE "delete_time" IS NULL;
        Long peopleTotalNumber1 = peopleDao.countAll();

        // SELECT *
        // FROM "people"
        // WHERE "id" = 536759343880005
        //    AND "name1" = 'people1'
        //    AND "name1" = 'people1'
        //    AND "name1" = 'people1'
        //    AND "name1" = "name1"
        //    AND "name1" = "name1"
        //    AND "name1" = 'people1'
        //    AND "name1" = 'people1'
        //    AND "name1" = 'people1'
        //    AND "creator_id" = 1
        //    OR "name1" != ''
        //    OR "name1" != ''
        //    OR "name1" != ''
        //    OR "name1" != "name1"
        //    OR "name1" != "name1"
        //    OR "name1" != 'people1'
        //    OR "name1" != 'people1'
        //    OR "name1" != 'people1'
        //    OR "creator_id" != 2
        //    AND "id" > 1
        //    AND "id" > 1
        //    AND "id" > 1
        //    AND "id" > "creator_id"
        //    AND "id" > "creator_id"
        //    AND "id" > 1
        //    AND "id" > 1
        //    AND "id" >= 1
        //    AND "id" >= 1
        //    AND "id" >= 1
        //    AND "id" >= "creator_id"
        //    AND "id" >= "creator_id"
        //    AND "id" >= 1
        //    AND "id" >= 1
        //    AND "creator_id" < 536759343880005
        //    AND "creator_id" < 536759343880005
        //    AND "creator_id" < 536759343880005
        //    AND "creator_id" < "id"
        //    AND "creator_id" < "id"
        //    AND "creator_id" < 536759343880005
        //    AND "creator_id" < 536759343880005
        //    AND "creator_id" <= 536759343880005
        //    AND "creator_id" <= 536759343880005
        //    AND "creator_id" <= "id"
        //    AND "creator_id" <= "id"
        //    AND "creator_id" <= 536759343880005
        //    AND "creator_id" <= 536759343880005
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" IN (536759343880005)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" NOT IN (1)
        //    AND "id" BETWEEN 1 AND 536759343880005
        //    AND "id" BETWEEN 1 AND 536759343880005
        //    AND "id" BETWEEN 1 AND 536759343880005
        //    AND "id" BETWEEN 1 AND 536759343880005
        //    AND "id" NOT BETWEEN 1 AND 2
        //    AND "id" NOT BETWEEN 1 AND 2
        //    AND "id" NOT BETWEEN 1 AND 2
        //    AND "id" NOT BETWEEN 1 AND 2
        //    AND "name1" LIKE '%people1%'
        //    AND "name1" LIKE '%people1%'
        //    AND "name1" LIKE '%people1%'
        //    AND "name1" LIKE '%people1%'
        //    AND "name1" LIKE '%people1%'
        //    AND "name1" LIKE 'people1%'
        //    AND "name1" LIKE 'people1%'
        //    AND "name1" LIKE 'people1%'
        //    AND "name1" LIKE 'people1%'
        //    AND "name1" LIKE 'people1%'
        //    AND "name1" LIKE '%people1'
        //    AND "name1" LIKE '%people1'
        //    AND "name1" LIKE '%people1'
        //    AND "name1" LIKE '%people1'
        //    AND "name1" LIKE '%people1'
        //    AND "name1" LIKE 'people1'
        //    AND "name1" LIKE 'people1'
        //    AND "name1" LIKE 'people1'
        //    AND "name1" LIKE 'people1'
        //    AND "name1" LIKE 'people1'
        //    AND "name1" NOT LIKE '%111%'
        //    AND "name1" NOT LIKE '%111%'
        //    AND "name1" NOT LIKE '%111%'
        //    AND "name1" NOT LIKE '%111%'
        //    AND "name1" NOT LIKE '%111%'
        //    AND "name1" NOT LIKE '111%'
        //    AND "name1" NOT LIKE '111%'
        //    AND "name1" NOT LIKE '111%'
        //    AND "name1" NOT LIKE '111%'
        //    AND "name1" NOT LIKE '111%'
        //    AND "name1" NOT LIKE '%111'
        //    AND "name1" NOT LIKE '%111'
        //    AND "name1" NOT LIKE '%111'
        //    AND "name1" NOT LIKE '%111'
        //    AND "name1" NOT LIKE '%111'
        //    AND "name1" NOT LIKE '111'
        //    AND "name1" NOT LIKE '111'
        //    AND "name1" NOT LIKE '111'
        //    AND "name1" NOT LIKE '111'
        //    AND "name1" NOT LIKE '111'
        //    AND "delete_time" IS NULL
        //    AND "delete_time" IS NULL
        //    AND "id" IS NOT NULL
        //    AND "id" IS NOT NULL
        //    AND "delete_time" IS NULL
        // ORDER BY "create_time" DESC
        // LIMIT 1;
        PeoplePO peoplePO3 = peopleDao.openQuery()
                .where(PeoplePO::getId).equalsTo(theFirstPeoplePO.getId())
                .and(PeoplePO::getName1).equalsTo(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).equalsTo(theFirstPeoplePO.getName1(), () -> true)
                .and(PeoplePO::getName1).equalsTo(theFirstPeoplePO.getName1(), ignore -> true)
                .and(PeoplePO::getName1).equalsTo(PeoplePO::getName1)
                .and(PeoplePO::getName1).equalsTo(PeoplePO::getName1, () -> true)
                .and(PeoplePO::getName1).equalsIfNotNull(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).equalsIfNotEmpty(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).equalsIfNotBlank(theFirstPeoplePO.getName1())
                .and(PeoplePO::getCreatorId).equalsIfNotZeroValue(1L)

                .or(PeoplePO::getName1).notEquals("")
                .or(PeoplePO::getName1).notEquals("", () -> true)
                .or(PeoplePO::getName1).notEquals("", ignore -> true)
                .or(PeoplePO::getName1).notEquals(PeoplePO::getName1)
                .or(PeoplePO::getName1).notEquals(PeoplePO::getName1, () -> true)
                .or(PeoplePO::getName1).notEqualsIfNotNull(theFirstPeoplePO.getName1())
                .or(PeoplePO::getName1).notEqualsIfNotEmpty(theFirstPeoplePO.getName1())
                .or(PeoplePO::getName1).notEqualsIfNotBlank(theFirstPeoplePO.getName1())
                .or(PeoplePO::getCreatorId).notEqualsIfNotZeroValue(2L)

                .and(PeoplePO::getId).greaterThan(theFirstPeoplePO.getCreatorId())
                .and(PeoplePO::getId).greaterThan(theFirstPeoplePO.getCreatorId(), () -> true)
                .and(PeoplePO::getId).greaterThan(theFirstPeoplePO.getCreatorId(), ignore -> true)
                .and(PeoplePO::getId).greaterThan(PeoplePO::getCreatorId)
                .and(PeoplePO::getId).greaterThan(PeoplePO::getCreatorId, () -> true)
                .and(PeoplePO::getId).greaterThanIfNotNull(theFirstPeoplePO.getCreatorId())
                .and(PeoplePO::getId).greaterThanIfNotZeroValue(theFirstPeoplePO.getCreatorId())

                .and(PeoplePO::getId).greaterThanOrEquals(theFirstPeoplePO.getCreatorId())
                .and(PeoplePO::getId).greaterThanOrEquals(theFirstPeoplePO.getCreatorId(), () -> true)
                .and(PeoplePO::getId).greaterThanOrEquals(theFirstPeoplePO.getCreatorId(), ignore -> true)
                .and(PeoplePO::getId).greaterThanOrEquals(PeoplePO::getCreatorId)
                .and(PeoplePO::getId).greaterThanOrEquals(PeoplePO::getCreatorId, () -> true)
                .and(PeoplePO::getId).greaterThanOrEqualsIfNotNull(theFirstPeoplePO.getCreatorId())
                .and(PeoplePO::getId).greaterThanOrEqualsIfNotZeroValue(theFirstPeoplePO.getCreatorId())

                .and(PeoplePO::getCreatorId).lessThan(theFirstPeoplePO.getId())
                .and(PeoplePO::getCreatorId).lessThan(theFirstPeoplePO.getId(), () -> true)
                .and(PeoplePO::getCreatorId).lessThan(theFirstPeoplePO.getId(), ignore -> true)
                .and(PeoplePO::getCreatorId).lessThan(PeoplePO::getId)
                .and(PeoplePO::getCreatorId).lessThan(PeoplePO::getId, () -> true)
                .and(PeoplePO::getCreatorId).lessThanIfNotNull(theFirstPeoplePO.getId())
                .and(PeoplePO::getCreatorId).lessThanIfNotZeroValue(theFirstPeoplePO.getId())

                .and(PeoplePO::getCreatorId).lessThanOrEquals(theFirstPeoplePO.getId(), () -> true)
                .and(PeoplePO::getCreatorId).lessThanOrEquals(theFirstPeoplePO.getId(), ignore -> true)
                .and(PeoplePO::getCreatorId).lessThanOrEquals(PeoplePO::getId)
                .and(PeoplePO::getCreatorId).lessThanOrEquals(PeoplePO::getId, () -> true)
                .and(PeoplePO::getCreatorId).lessThanOrEqualsIfNotNull(theFirstPeoplePO.getId())
                .and(PeoplePO::getCreatorId).lessThanOrEqualsIfNotZeroValue(theFirstPeoplePO.getId())

                .and(PeoplePO::getId).in(theFirstPeoplePO.getId())
                .and(PeoplePO::getId).in(Collections.ofArray(Object.class, theFirstPeoplePO.getId()))
                .and(PeoplePO::getId).in(Collections.ofArray(Object.class, theFirstPeoplePO.getId()), () -> true)
                .and(PeoplePO::getId).in(Collections.ofArray(Object.class, theFirstPeoplePO.getId()), ignore -> true)
                .and(PeoplePO::getId).in(Collections.ofImmutableList(theFirstPeoplePO.getId()))
                .and(PeoplePO::getId).in(Collections.ofImmutableList(theFirstPeoplePO.getId()), () -> true)
                .and(PeoplePO::getId).in(Collections.ofImmutableList(theFirstPeoplePO.getId()), ignore -> true)
                .and(PeoplePO::getId).inIfNotEmpty(Collections.ofArray(Object.class, theFirstPeoplePO.getId()))
                .and(PeoplePO::getId).inIfNotEmpty(Collections.ofImmutableList(theFirstPeoplePO.getId()))

                .and(PeoplePO::getId).notIn(theFirstPeoplePO.getCreatorId())
                .and(PeoplePO::getId).notIn(Collections.ofArray(Object.class, theFirstPeoplePO.getCreatorId()))
                .and(PeoplePO::getId).notIn(Collections.ofArray(Object.class, theFirstPeoplePO.getCreatorId()), () -> true)
                .and(PeoplePO::getId).notIn(Collections.ofArray(Object.class, theFirstPeoplePO.getCreatorId()), ignore -> true)
                .and(PeoplePO::getId).notIn(Collections.ofImmutableList(theFirstPeoplePO.getCreatorId()))
                .and(PeoplePO::getId).notIn(Collections.ofImmutableList(theFirstPeoplePO.getCreatorId()), () -> true)
                .and(PeoplePO::getId).notIn(Collections.ofImmutableList(theFirstPeoplePO.getCreatorId()), ignore -> true)
                .and(PeoplePO::getId).notInIfNotEmpty(Collections.ofArray(Object.class, theFirstPeoplePO.getCreatorId()))
                .and(PeoplePO::getId).notInIfNotEmpty(Collections.ofImmutableList(theFirstPeoplePO.getCreatorId()))

                .and(PeoplePO::getId).between(theFirstPeoplePO.getCreatorId(), theFirstPeoplePO.getId())
                .and(PeoplePO::getId).between(theFirstPeoplePO.getCreatorId(), theFirstPeoplePO.getId(), () -> true)
                .and(PeoplePO::getId).between(theFirstPeoplePO.getCreatorId(), theFirstPeoplePO.getId(), (ignore1, ignore2) -> true)
                .and(PeoplePO::getId).betweenIfAllNotNull(theFirstPeoplePO.getCreatorId(), theFirstPeoplePO.getId())

                .and(PeoplePO::getId).notBetween(theFirstPeoplePO.getCreatorId(), 2L)
                .and(PeoplePO::getId).notBetween(theFirstPeoplePO.getCreatorId(), 2L, () -> true)
                .and(PeoplePO::getId).notBetween(theFirstPeoplePO.getCreatorId(), 2L, (ignore1, ignore2) -> true)
                .and(PeoplePO::getId).notBetweenIfAllNotNull(theFirstPeoplePO.getCreatorId(), 2L)

                .and(PeoplePO::getName1).like(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).like(theFirstPeoplePO.getName1(), () -> true)
                .and(PeoplePO::getName1).like(theFirstPeoplePO.getName1(), ignore -> true)
                .and(PeoplePO::getName1).likeIfNotNull(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).likeIfNotBlank(theFirstPeoplePO.getName1())

                .and(PeoplePO::getName1).likeLeft(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).likeLeft(theFirstPeoplePO.getName1(), () -> true)
                .and(PeoplePO::getName1).likeLeft(theFirstPeoplePO.getName1(), ignore -> true)
                .and(PeoplePO::getName1).likeLeftIfNotNull(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).likeLeftIfNotBlank(theFirstPeoplePO.getName1())

                .and(PeoplePO::getName1).likeRight(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).likeRight(theFirstPeoplePO.getName1(), () -> true)
                .and(PeoplePO::getName1).likeRight(theFirstPeoplePO.getName1(), ignore -> true)
                .and(PeoplePO::getName1).likeRightIfNotNull(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).likeRightIfNotBlank(theFirstPeoplePO.getName1())

                .and(PeoplePO::getName1).likeRaw(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).likeRaw(theFirstPeoplePO.getName1(), () -> true)
                .and(PeoplePO::getName1).likeRaw(theFirstPeoplePO.getName1(), ignore -> true)
                .and(PeoplePO::getName1).likeRawIfNotNull(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).likeRawIfNotBlank(theFirstPeoplePO.getName1())

                .and(PeoplePO::getName1).notLike("111")
                .and(PeoplePO::getName1).notLike("111", () -> true)
                .and(PeoplePO::getName1).notLike("111", ignore -> true)
                .and(PeoplePO::getName1).notLikeIfNotNull("111")
                .and(PeoplePO::getName1).notLikeIfNotBlank("111")

                .and(PeoplePO::getName1).notLikeLeft("111")
                .and(PeoplePO::getName1).notLikeLeft("111", () -> true)
                .and(PeoplePO::getName1).notLikeLeft("111", ignore -> true)
                .and(PeoplePO::getName1).notLikeLeftIfNotNull("111")
                .and(PeoplePO::getName1).notLikeLeftIfNotBlank("111")

                .and(PeoplePO::getName1).notLikeRight("111")
                .and(PeoplePO::getName1).notLikeRight("111", () -> true)
                .and(PeoplePO::getName1).notLikeRight("111", ignore -> true)
                .and(PeoplePO::getName1).notLikeRightIfNotNull("111")
                .and(PeoplePO::getName1).notLikeRightIfNotBlank("111")

                .and(PeoplePO::getName1).notLikeRaw("111")
                .and(PeoplePO::getName1).notLikeRaw("111", () -> true)
                .and(PeoplePO::getName1).notLikeRaw("111", ignore -> true)
                .and(PeoplePO::getName1).notLikeRawIfNotNull("111")
                .and(PeoplePO::getName1).notLikeRawIfNotBlank("111")

                .and(PeoplePO::getDeleteTime).isNull()
                .and(PeoplePO::getDeleteTime).isNull(() -> true)

                .and(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull(() -> true)

                .orderByDesc(PeoplePO::getCreateTime)
                .get()
                .orElseThrow();

        // SELECT * FROM "people" WHERE "delete_time" IS NULL;
        List<PeoplePO> peoplePOs2 = peopleDao.openQuery().list();

        // SELECT *
        // FROM "people"
        //          INNER JOIN "home" AS "home2" ON ("people"."home_id" = "home2"."id") AND "home2"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL
        // ORDER BY "people"."create_time" DESC;
        List<PeoplePO> peoplePOs3 = peopleDao.openQuery()
                .innerJoin(HomePO.class).as("home2").onEquals(PeoplePO::getHomeId, HomePO::getId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .orderByDesc(PeoplePO::getCreateTime)
                .list();

        // SELECT *
        // FROM "people"
        //          LEFT JOIN "home" ON ("people"."home_id" = "home"."id" AND "people"."home_id" = "home"."id" AND "people"."home_id" = "home"."id" AND "people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL
        // ORDER BY "people"."create_time" DESC;
        List<PeoplePO> peoplePOs4 = peopleDao.openQuery()
                .leftJoin(HomePO.class).onEquals(PeoplePO::getHomeId, HomePO::getId, PeoplePO::getHomeId, HomePO::getId, PeoplePO::getHomeId, HomePO::getId, PeoplePO::getHomeId, HomePO::getId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .orderByDesc(PeoplePO::getCreateTime)
                .list();

        // SELECT *
        // FROM "people"
        //          RIGHT JOIN "home" ON ("people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL
        // ORDER BY "people"."create_time" DESC;
        List<PeoplePO> peoplePOs5 = peopleDao.openQuery()
                .rightJoin(HomePO.class).onEquals(PeoplePO::getHomeId, HomePO::getId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .orderByDesc(PeoplePO::getCreateTime)
                .list();

        // SELECT *
        // FROM "people"
        //          FULL JOIN "home" ON ("people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL
        // ORDER BY "people"."create_time" DESC;
        List<PeoplePO> peoplePOs7 = peopleDao.openQuery()
                .fullJoin(HomePO.class).onEquals(PeoplePO::getHomeId, HomePO::getId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .orderByDesc(PeoplePO::getCreateTime)
                .list();

        // SELECT "creator_id", "creator_id", "updater_id"
        // FROM "people"
        // WHERE ("id" IS NOT NULL AND "id" IS NOT NULL OR "id" IS NOT NULL)
        //   AND "delete_time" IS NULL
        // GROUP BY "creator_id", "creator_id", "updater_id"
        // ORDER BY "creator_id" DESC;
        List<PeoplePO> peoplePOs8 = peopleDao.openQuery()
                .select(PeoplePO::getCreatorId, PeoplePO::getCreatorId)
                .select(PeoplePO::getUpdaterId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .groupBy(PeoplePO::getCreatorId, PeoplePO::getCreatorId)
                .groupBy(PeoplePO::getUpdaterId)
                .orderByDesc(PeoplePO::getCreatorId)
                .list();

        // SELECT COUNT(*) AS "total"
        // FROM "people"
        //          RIGHT JOIN "home" ON ("people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL;
        //
        // SELECT *
        // FROM "people"
        //          RIGHT JOIN "home" ON ("people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL
        // ORDER BY "people"."create_time" DESC
        // LIMIT 10 OFFSET 0;
        PageResult<PeoplePO> peoplePagePOs1 = peopleDao.openQuery()
                .rightJoin(HomePO.class).onEquals(PeoplePO::getHomeId, HomePO::getId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .orderByDesc(PeoplePO::getCreateTime)
                .page();

        // SELECT COUNT(*) AS "total"
        // FROM "people"
        //          RIGHT JOIN "home" ON ("people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL;
        //
        // SELECT *
        // FROM "people"
        //          RIGHT JOIN "home" ON ("people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL
        // ORDER BY "people"."create_time" DESC
        // LIMIT 5 OFFSET 0;
        PageResult<PeopleVO> peoplePageVOs2 = peopleDao.openQuery()
                .rightJoin(HomePO.class).onEquals(PeoplePO::getHomeId, HomePO::getId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .orderByDesc(PeoplePO::getCreateTime)
                .pageToVO(1, 5);

        // SELECT *
        // FROM "people"
        //          RIGHT JOIN "home" ON ("people"."home_id" = "home"."id") AND "home"."delete_time" IS NULL
        // WHERE ("people"."id" IS NOT NULL AND "people"."id" IS NOT NULL OR "people"."id" IS NOT NULL)
        //   AND "people"."delete_time" IS NULL
        // ORDER BY "people"."create_time" DESC
        // LIMIT 5 OFFSET 0;
        PageResult<PeopleVO> peoplePageVOs3 = peopleDao.openQuery()
                .rightJoin(HomePO.class).onEquals(PeoplePO::getHomeId, HomePO::getId)
                .where(PeoplePO::getId).isNotNull()
                .and(PeoplePO::getId).isNotNull()
                .or(PeoplePO::getId).isNotNull()
                .orderByDesc(PeoplePO::getCreateTime)
                .pageToVO(1, 5, 9);

        // SELECT COUNT(*) FROM "people" WHERE ("id" IS NULL ) AND "delete_time" IS NULL;
        Long peopleTotalNumber2 = peopleDao.openQuery().where(PeoplePO::getId).isNull().count();
        // SELECT COUNT(*) FROM "people" WHERE ("id" IS NOT NULL ) AND "delete_time" IS NULL;
        Long peopleTotalNumber3 = peopleDao.openQuery().where(PeoplePO::getId).isNotNull().count();
        // SELECT COUNT(*) FROM "people" WHERE ("id" IS NULL ) AND "delete_time" IS NULL;
        Boolean isPeopleExist1 = peopleDao.openQuery().where(PeoplePO::getId).isNull().exists();
        // SELECT COUNT(*) FROM "people" WHERE ("id" IS NOT NULL ) AND "delete_time" IS NULL;
        Boolean isPeopleExist2 = peopleDao.openQuery().where(PeoplePO::getId).isNotNull().exists();

        testDelete();
    }

}