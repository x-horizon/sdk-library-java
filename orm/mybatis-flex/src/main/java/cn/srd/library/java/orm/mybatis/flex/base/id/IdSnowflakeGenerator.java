package cn.srd.library.java.orm.mybatis.flex.base.id;

import com.mybatisflex.core.keygen.IKeyGenerator;

// @NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdSnowflakeGenerator implements IKeyGenerator {

    protected static final IdSnowflakeGenerator INSTANCE = new IdSnowflakeGenerator();

    @Override
    public Object generate(Object entity, String keyColumn) {
        return null;
        // return SnowflakeIds.getId();
    }

}