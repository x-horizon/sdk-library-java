package cn.srd.library.java.orm.mybatis.flex.base.id;

// @NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdSnowflakeGenerator implements IdGenerator {

    private static final class SingleTonHolder {

        private static final IdSnowflakeGenerator INSTANCE = new IdSnowflakeGenerator();

    }

    public static IdSnowflakeGenerator getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public Object generate(Object entity, String keyColumn) {
        return null;
        // return SnowflakeIds.getId();
    }

}