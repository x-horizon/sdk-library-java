package cn.srd.sugar.id.snowflake.core;

/**
 * 雪花 id 相关信息配置
 *
 * @author wjm
 * @since 2022-08-29 09:04:19
 */
public interface SnowflakeIdConfig {

    /**
     * 设置节点 id
     *
     * @return 节点 id
     */
    short setWorkerId();

}
