package cn.srd.library.java.orm.mybatis.flex.base.id;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdGenerateByAutoIncrementStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByAutoIncrementStrategy INSTANCE = new IdGenerateByAutoIncrementStrategy();

}
