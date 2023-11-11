package cn.srd.library.java.orm.mybatis.flex.base.id;

import java.util.UUID;

// @NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdUUIDGenerator implements IdGenerator {

    private static final class SingleTonHolder {

        private static final IdUUIDGenerator INSTANCE = new IdUUIDGenerator();

    }

    public static IdUUIDGenerator getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public Object generate(Object entity, String keyColumn) {
        return UUID.randomUUID().toString();
    }

}