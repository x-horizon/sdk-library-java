package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.IKeyGenerator;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByCustomerStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByCustomerStrategy INSTANCE = new IdGenerateByCustomerStrategy();

    @Override
    public String getGeneratorName() {
        return IdGenerateType.CUSTOMER_GENERATOR_NAME;
    }

    @Override
    public Class<? extends IKeyGenerator> getGenerator() {
        return IdInvalidGenerator.class;
    }

    @Override
    public FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        Assert.of().setMessage("{}could not build global id generate config because of using the id generate type [{}] in [@{}]-[@{}] but no generator was specified!", ModuleView.ORM_MYBATIS_SYSTEM, IdGenerateType.CUSTOMER.getClass().getSimpleName(), EnableMybatisFlexCustomizer.class.getSimpleName(), IdGenerateConfig.class.getSimpleName())
                .throwsIfEquals(IdInvalidGenerator.class, idGenerateConfig.generator());
        KeyGeneratorFactory.register(getGeneratorName(), Reflects.newInstance(idGenerateConfig.generator()));
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
