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
import cn.srd.library.java.orm.mybatis.flex.postgresql.dao.StudentTestIdSnowflakeDao;
import cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.StudentTestIdSnowflakePO;
import cn.srd.library.java.tool.id.snowflake.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIdEnvironment;
import cn.srd.library.java.tool.lang.collection.Collections;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static cn.srd.library.java.orm.mybatis.flex.postgresql.model.po.table.StudentTestIdSnowflakeTable.STUDENT_TEST_ID_SNOWFLAKE;

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

    @Autowired private StudentTestIdSnowflakeDao studentTestIdSnowflakeDao;

    @Test
    void testSave() {
        StudentTestIdSnowflakePO studentTestIdSnowflakePO1 = studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().build());
        StudentTestIdSnowflakePO studentTestIdSnowflakePO2 = studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(768L).build());

        Map<Integer, StudentTestIdSnowflakePO> smallStudentTestIdSnowflakePOs = Collections.newHashMap();
        for (int i = 1; i < 60; i++) {
            smallStudentTestIdSnowflakePOs.put(i, StudentTestIdSnowflakePO.builder().build());
        }
        List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs1 = studentTestIdSnowflakeDao.saveBatch(smallStudentTestIdSnowflakePOs.values());

        Map<Integer, StudentTestIdSnowflakePO> bigStudentTestIdSnowflakePOs = Collections.newHashMap();
        for (int i = 1; i < 300; i++) {
            bigStudentTestIdSnowflakePOs.put(i, StudentTestIdSnowflakePO.builder().build());
        }
        List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs2 = studentTestIdSnowflakeDao.saveBatch(bigStudentTestIdSnowflakePOs.values());

        Map<Integer, StudentTestIdSnowflakePO> smallStudentTestIdSnowflakeWithIdPOs = Collections.newHashMap();
        for (int i = 1; i < 60; i++) {
            Long id = (long) (10000 + i);
            smallStudentTestIdSnowflakeWithIdPOs.put(i, StudentTestIdSnowflakePO.builder().id(id).build());
        }
        List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs3 = studentTestIdSnowflakeDao.saveBatch(smallStudentTestIdSnowflakeWithIdPOs.values());

        Map<Integer, StudentTestIdSnowflakePO> bigStudentTestIdSnowflakeWithIdPOs = Collections.newHashMap();
        for (int i = 1; i < 300; i++) {
            Long id = (long) (20000 + i);
            bigStudentTestIdSnowflakeWithIdPOs.put(i, StudentTestIdSnowflakePO.builder().id(id).build());
        }
        List<StudentTestIdSnowflakePO> studentTestIdSnowflakePOs4 = studentTestIdSnowflakeDao.saveBatch(bigStudentTestIdSnowflakeWithIdPOs.values());

        studentTestIdSnowflakeDao.deleteSkipLogicAll();
    }

    @Test
    void testDelete() {
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(10L).build());
        studentTestIdSnowflakeDao.deleteSkipLogicById(10L);

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(11L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(12L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(13L).build());
        studentTestIdSnowflakeDao.deleteSkipLogicByIds(11L, 12L, 13L);

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(14L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(15L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(16L).build());
        studentTestIdSnowflakeDao.deleteSkipLogicByIds(Collections.ofHashSet(14L, 15L, 16L));

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(17L).build());
        studentTestIdSnowflakeDao.deleteSkipLogicByCondition(QueryWrapper.create().where(STUDENT_TEST_ID_SNOWFLAKE.ID.equalsTo(17L)));

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(18L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(19L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(20L).build());
        studentTestIdSnowflakeDao.deleteSkipLogicAll();

        // =============

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(10L).build());
        studentTestIdSnowflakeDao.deleteById(10L);

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(11L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(12L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(13L).build());
        studentTestIdSnowflakeDao.deleteByIds(11L, 12L, 13L);

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(14L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(15L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(16L).build());
        studentTestIdSnowflakeDao.deleteByIds(Collections.ofHashSet(14L, 15L, 16L));

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(17L).build());
        studentTestIdSnowflakeDao.deleteByCondition(QueryWrapper.create().where(STUDENT_TEST_ID_SNOWFLAKE.ID.equalsTo(17L)));

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(18L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(19L).build());
        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(20L).build());
        studentTestIdSnowflakeDao.deleteAll();

        // =============

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(21L).build());
        studentTestIdSnowflakeDao.deleteSkipLogicById(21L);

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(22L).build());
        studentTestIdSnowflakeDao.deleteById(22L);

        studentTestIdSnowflakeDao.deleteSkipLogicAll();

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(22L).build());
        studentTestIdSnowflakeDao.deleteById(22L);

        studentTestIdSnowflakeDao.save(StudentTestIdSnowflakePO.builder().id(21L).build());
        studentTestIdSnowflakeDao.deleteSkipLogicById(21L);

        studentTestIdSnowflakeDao.deleteSkipLogicAll();
    }

}
