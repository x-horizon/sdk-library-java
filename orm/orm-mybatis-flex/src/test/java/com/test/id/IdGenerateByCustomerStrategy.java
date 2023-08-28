package com.test.id;

import cn.srd.library.java.contract.constant.core.ModuleView;
import cn.srd.library.java.tool.lang.core.ReflectsUtil;
import cn.srd.library.java.tool.lang.core.StringsUtil;
import cn.srd.library.java.tool.lang.core.asserts.Assert;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByCustomerStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByCustomerStrategy INSTANCE = new IdGenerateByCustomerStrategy();

    @Override
    public String getGeneratorName() {
        return "mybatis-flex-id-customer-generator";
    }

    @Override
    public FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        Assert.INSTANCE.set(StringsUtil.format("{}could not build id generate config because of using the id generate type [{}] in [@{}] but no generator was specified!", ModuleView.ORM_MYBATIS_FLEX_SYSTEM, IdGenerateType.CUSTOMER.getClass().getSimpleName(), IdGenerateConfig.class.getSimpleName())).throwsIfEquals(IdInvalidGenerator.class, idGenerateConfig.generator());
        KeyGeneratorFactory.register(getGeneratorName(), ReflectsUtil.newInstance(idGenerateConfig.generator()));
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
