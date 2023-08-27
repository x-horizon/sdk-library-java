package cn.srd.library.java.orm.mybatis.flex.config;

import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdConfig implements cn.srd.library.java.id.snowflake.core.SnowflakeIdConfig {

    @Override
    public short setWorkerId() {
        return 1;
    }

}
