package cn.srd.library.java.orm.mybatis.flex.base.id;

import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateBySnowflakeStrategy implements IdGenerateStrategy {

    protected static final IdGenerateBySnowflakeStrategy INSTANCE = new IdGenerateBySnowflakeStrategy();

    @Override
    public void registerIdGenerator(IdConfig idConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), IdSnowflakeGenerator.getInstance());
    }

}
