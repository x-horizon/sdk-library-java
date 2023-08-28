package com.test.id;

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
        Assert.INSTANCE.set("").throwsIfBlank(idGenerateConfig.sql());
        return IdGenerateStrategy.super.buildConfig(idGenerateConfig);
    }

}
