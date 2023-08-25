package cn.srd.library.java.orm.mybatis.flex.config;

import cn.srd.library.java.id.snowflake.core.SnowflakeIdConfig;
import org.springframework.stereotype.Component;

@Component
public class SignalSnowflakeIdConfig implements SnowflakeIdConfig {

    @Override
    public short setWorkerId() {
        return 1;
    }

}
