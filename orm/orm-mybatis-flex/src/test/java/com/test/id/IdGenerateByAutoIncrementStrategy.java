package com.test.id;

public class IdGenerateByAutoIncrementStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByAutoIncrementStrategy INSTANCE = new IdGenerateByAutoIncrementStrategy();

    @Override
    public String getGeneratorName() {
        return "mybatis-flex-id-auto-increment-generator";
    }

}
