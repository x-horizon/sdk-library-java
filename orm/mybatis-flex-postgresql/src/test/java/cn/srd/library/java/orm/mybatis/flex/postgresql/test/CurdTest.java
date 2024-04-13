// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.test;

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
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_1)).build().setName(PEOPLE_NAME_1));
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_2)).build().setName(PEOPLE_NAME_2));
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_3)).build().setName(PEOPLE_NAME_3));
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_4)).build().setName(PEOPLE_NAME_4));
        peopleDao.save(PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_5)).build().setName(PEOPLE_NAME_5));
        peopleDao.saveBatch(Collections.ofArrayList(
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_6)).build().setName(PEOPLE_NAME_6),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_7)).build().setName(PEOPLE_NAME_7),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_8)).build().setName(PEOPLE_NAME_8),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_9)).build().setName(PEOPLE_NAME_9),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_10)).build().setName(PEOPLE_NAME_10)
        ));
        peopleDao.saveBatch(Collections.ofArrayList(
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_11)).build().setName(PEOPLE_NAME_11),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_12)).build().setName(PEOPLE_NAME_12),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_13)).build().setName(PEOPLE_NAME_13),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_14)).build().setName(PEOPLE_NAME_14),
                PeoplePO.builder().homeId(homeNameMappingHomeIdMap.get(HOME_NAME_15)).build().setName(PEOPLE_NAME_15)
        ), 2);
    }

    @Test
    void testUpdate() {
        testDelete();
        testSave();

        List<HomePO> homePOs = homeDao.listAll().stream().map(homePO -> homePO.setName(homePO.getName() + "*")).toList();
        HomePO theFirstHomePO = Collections.getFirst(homePOs).orElseThrow();
        homeDao.updateById(theFirstHomePO);
        homeDao.updateBatchById(theFirstHomePO);
        homeDao.updateBatchById(homePOs);
        homeDao.updateBatchById(homePOs, 2);

        List<PeoplePO> peoplePOs = peopleDao.listAll().stream().map(peoplePO -> peoplePO.setName(peoplePO.getName1() + "*")).toList();
        PeoplePO theFirstPeoplePO = Collections.getFirst(peoplePOs).orElseThrow();
        peopleDao.updateWithVersionById(theFirstPeoplePO);
        peopleDao.updateBatchWithVersionById(peoplePOs, PeoplePO::getId);
        peopleDao.updateBatchWithVersionById(peoplePOs, PeoplePO::getId, 2);

        peopleDao.openUpdate()
                .where(PeoplePO::getCreatorId).equalsTo(1)
                .and(PeoplePO::getUpdaterId).equalsTo(1)
                .update();

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
    void testQuery() {
        testDelete();
        testSave();

        List<PeoplePO> allPeoplePOs = peopleDao.listAll();
        PeoplePO theFirstPeoplePO = Collections.getFirst(allPeoplePOs).orElseThrow();
        PeoplePO theSecondPeoplePO = Collections.getSecond(allPeoplePOs).orElseThrow();
        List<PeoplePO> peoplePOs = peopleDao.listByIds(Collections.ofImmutableList(theFirstPeoplePO.getId(), theSecondPeoplePO.getId()));
        PeoplePO peoplePO1 = peopleDao.getById(theFirstPeoplePO).orElseThrow();
        PeoplePO peoplePO2 = peopleDao.getById(theSecondPeoplePO).orElseThrow();
        Long peopleTotalNumber = peopleDao.countAll();

        peoplePO1 = (PeoplePO) peopleDao.openQuery()
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

                .and(PeoplePO::getName1).notEquals("")
                .and(PeoplePO::getName1).notEquals("", () -> true)
                .and(PeoplePO::getName1).notEquals("", ignore -> true)
                .and(PeoplePO::getName1).notEquals(PeoplePO::getId)
                .and(PeoplePO::getName1).notEquals(PeoplePO::getId, () -> true)
                .and(PeoplePO::getName1).notEqualsIfNotNull(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).notEqualsIfNotEmpty(theFirstPeoplePO.getName1())
                .and(PeoplePO::getName1).notEqualsIfNotBlank(theFirstPeoplePO.getName1())
                .and(PeoplePO::getCreatorId).notEqualsIfNotZeroValue(2L)

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

        //         .set(PeoplePO::getName2, theFirstPeoplePO.getName2() + "-set-boolean-supplier-2", () -> false)
        // .set(PeoplePO::getName3, theFirstPeoplePO.getName3() + "-set-boolean-predicate-2", ignore -> false)
        // .set(PeoplePO::getName4, theFirstPeoplePO.getName4() + "-set-boolean-2", ignore -> false)

        testDelete();
    }

}