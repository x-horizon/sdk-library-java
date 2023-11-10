package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.compare.Comparators;
import cn.srd.library.java.tool.lang.object.Nil;
import com.mybatisflex.core.FlexGlobalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IdGenerateStrategy {

    Logger log = LoggerFactory.getLogger(IdGenerateStrategy.class);

    default String getGeneratorName() {
        return "mybatisFlexId-" + this.getClass().getSimpleName();
    }

    default void warningIfNotDefaultIdGenerateType(IdGenerateType idGenerateType) {
        if (Comparators.notEquals(IdGenerateType.UNCONTROLLED, idGenerateType)) {
            log.warn("{}id generator config - current id generate strategy is [{}], the specified id generate type [{}] will not take effect!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), idGenerateType.name());
        }
    }

    default void warningIfNotDefaultIdGenerator(Class<? extends IdGenerator> idGenerator) {
        if (Comparators.notEquals(IdInvalidGenerator.class, idGenerator)) {
            log.warn("{}id generator config - current id generate strategy is [{}], the specified id generator [{}] will not take effect!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), idGenerator.getName());
        }
    }

    default void warningIfNotDefaultIdGenerateSQL(String idGenerateSQL) {
        if (Nil.isNotBlank(idGenerateSQL)) {
            log.warn("{}id generator config - current id generate strategy is [{}], the specified generate sql [{}] will not take effect!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), idGenerateSQL);
        }
    }

    default void validateIdConfig(IdConfig idConfig) {
        warningIfNotDefaultIdGenerator(idConfig.generator());
        warningIfNotDefaultIdGenerateSQL(idConfig.generateSQL());
    }

    default void registerIdGenerator(IdConfig idConfig) {
    }

    default FlexGlobalConfig.KeyConfig build(IdConfig idConfig) {
        validateIdConfig(idConfig);
        registerIdGenerator(idConfig);
        return doBuild(idConfig);
    }

    private FlexGlobalConfig.KeyConfig doBuild(IdConfig idConfig) {
        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
        keyConfig.setKeyType(idConfig.type().getMybatisFlexIdType());
        keyConfig.setValue(getGeneratorName());
        keyConfig.setBefore(true);
        return keyConfig;
    }

}
