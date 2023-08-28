package com.test.id;

import cn.srd.library.java.contract.constant.core.ModuleView;
import cn.srd.library.java.tool.lang.core.StringsUtil;
import cn.srd.library.java.tool.lang.core.asserts.Assert;
import com.mybatisflex.core.FlexGlobalConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateBySQLStrategy implements IdGenerateStrategy {

    protected static final IdGenerateBySQLStrategy INSTANCE = new IdGenerateBySQLStrategy();

    @Override
    public String getGeneratorName() {
        return "mybatis-flex-id-sql-generator";
    }

    @Override
    public FlexGlobalConfig.KeyConfig buildConfig(IdGenerateConfig idGenerateConfig) {
        Assert.INSTANCE.set(StringsUtil.format("{}could not build id generate config because of using the id generate type [{}] in [@{}] but no sql was specified!", ModuleView.ORM_MYBATIS_FLEX_SYSTEM, IdGenerateType.SQL.getClass().getSimpleName(), IdGenerateConfig.class.getSimpleName())).throwsIfBlank(idGenerateConfig.sql());
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
