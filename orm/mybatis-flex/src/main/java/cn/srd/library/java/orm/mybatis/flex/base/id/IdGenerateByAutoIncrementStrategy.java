package cn.srd.library.java.orm.mybatis.flex.base.id;

import com.mybatisflex.core.keygen.IKeyGenerator;

public class IdGenerateByAutoIncrementStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByAutoIncrementStrategy INSTANCE = new IdGenerateByAutoIncrementStrategy();

    @Override
    public String getGeneratorName() {
        return IdGenerateType.AUTO_INCREMENT_GENERATOR_NAME;
    }

    @Override
    public Class<? extends IKeyGenerator> getGenerator() {
        return IdInvalidGenerator.class;
    }

}
