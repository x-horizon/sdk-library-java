package com.test.id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByNoneStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByNoneStrategy INSTANCE = new IdGenerateByNoneStrategy();

    @Override
    public String getGeneratorName() {
        return "mybatis-flex-id-none-generator";
    }

}
