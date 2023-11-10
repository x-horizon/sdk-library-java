package cn.srd.library.java.orm.mybatis.flex.base.id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdGenerateByUncontrolledStrategy implements IdGenerateStrategy {

    protected static final IdGenerateByUncontrolledStrategy INSTANCE = new IdGenerateByUncontrolledStrategy();

}
