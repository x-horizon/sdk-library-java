package cn.srd.library.java.orm.mybatis.flex.base.id;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByCustomerStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByCustomerStrategy INSTANCE = new IdGenerateByCustomerStrategy();

    @Override
    public void validateIdConfig(IdConfig idConfig) {
        warningIfNotDefaultIdGenerateSQL(idConfig.generateSQL());
        Assert.of().setMessage("{}id generator config - current id generate strategy is [{}] but no generator was specified!", ModuleView.ORM_MYBATIS_SYSTEM, this.getClass().getName())
                .throwsIfEquals(IdInvalidGenerator.class, idConfig.generator());
    }

    @Override
    public void registerIdGenerator(IdConfig idConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), Reflects.newInstance(idConfig.generator()));
    }

}
