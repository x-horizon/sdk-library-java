// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.object.Nil;
import com.mybatisflex.core.FlexGlobalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the root id generate strategy
 *
 * @author wjm
 * @see IdConfig
 * @since 2023-11-12 21:06
 */
public interface IdGenerateStrategy {

    Logger log = LoggerFactory.getLogger(IdGenerateStrategy.class);

    default String getGeneratorName() {
        return "mybatisFlexId-" + this.getClass().getSimpleName();
    }

    default void warningIfNotDefaultIdGenerateType(IdGenerateType idGenerateType) {
        if (Comparators.notEquals(IdGenerateType.UNCONTROLLED, idGenerateType)) {
            log.warn("{}id generator config - current id generate strategy is [{}], the specified id generated type [{}] will not take effect!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), idGenerateType.name());
        }
    }

    default void warningIfNotDefaultIdGenerator(Class<? extends IdGenerator> idGenerator) {
        if (Comparators.notEquals(UnsupportedIdGenerator.class, idGenerator)) {
            log.warn("{}id generator config - current id generate strategy is [{}], the specified id generator [{}] will not take effect!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), idGenerator.getName());
        }
    }

    default void warningIfNotDefaultIdGenerateSQL(String idGenerateSQL) {
        if (Nil.isNotBlank(idGenerateSQL)) {
            log.warn("{}id generator config - current id generate strategy is [{}], the specified generated sql [{}] will not take effect!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), idGenerateSQL);
        }
    }

    default void validateIdConfig(IdConfig idConfig) {
        warningIfNotDefaultIdGenerator(idConfig.generator());
        warningIfNotDefaultIdGenerateSQL(idConfig.generateSQL());
    }

    default void registerIdGenerator(IdConfig idConfig) {
    }

    default String buildMybatisFlexKeyConfigValue(IdConfig idConfig) {
        return getGeneratorName();
    }

    default FlexGlobalConfig.KeyConfig build(IdConfig idConfig) {
        validateIdConfig(idConfig);
        registerIdGenerator(idConfig);
        return doBuild(idConfig);
    }

    private FlexGlobalConfig.KeyConfig doBuild(IdConfig idConfig) {
        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
        keyConfig.setKeyType(idConfig.generateType().getMybatisFlexIdType());
        keyConfig.setValue(buildMybatisFlexKeyConfigValue(idConfig));
        keyConfig.setBefore(true);
        return keyConfig;
    }

}