package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.tool.lang.functional.Assert;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateBySQLStrategy implements IdGenerateStrategy {

    protected static final IdGenerateBySQLStrategy INSTANCE = new IdGenerateBySQLStrategy();

    @Override
    public String getGeneratorName() {
        return IdGenerateType.SQL_GENERATOR_NAME;
    }

    @Override
    public Class<? extends IKeyGenerator> getGenerator() {
        return IdInvalidGenerator.class;
    }

    @Override
    public FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        Assert.of().setMessage("{}could not build global id generate config because of using the id generate type [{}] in [@{}]-[@{}] but no sql was specified!", ModuleView.ORM_MYBATIS_SYSTEM, IdGenerateType.CUSTOMER.getClass().getSimpleName(), EnableMybatisFlexCustomizer.class.getSimpleName(), IdGenerateConfig.class.getSimpleName())
                .throwsIfEquals(IdInvalidGenerator.class, idGenerateConfig.generator());
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
