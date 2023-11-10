package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.functional.Assert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateBySQLStrategy implements IdGenerateStrategy {

    protected static final IdGenerateBySQLStrategy INSTANCE = new IdGenerateBySQLStrategy();

    @Override
    public void validateIdConfig(IdConfig idConfig) {
        warningIfNotDefaultIdGenerateType(idConfig.type());
        Assert.of().setMessage("{}id generator config - current id generate strategy is [{}] but no generated sql specified in [{}]!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName(), IdConfig.class.getName())
                .throwsIfBlank(idConfig.generateSQL());
    }

}
