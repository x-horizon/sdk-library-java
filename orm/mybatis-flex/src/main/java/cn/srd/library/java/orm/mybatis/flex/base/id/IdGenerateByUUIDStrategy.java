package cn.srd.library.java.orm.mybatis.flex.base.id;

import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.core.keygen.impl.UUIDKeyGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByUUIDStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByUUIDStrategy INSTANCE = new IdGenerateByUUIDStrategy();

    @Override
    public void registerIdGenerator(IdConfig idConfig) {
        KeyGeneratorFactory.register(getGeneratorName(), new UUIDKeyGenerator());
    }

}
