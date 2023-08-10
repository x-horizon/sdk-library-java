package cn.srd.sugar.id.snowflake.core;

import com.github.yitter.idgen.YitIdHelper;

/**
 * 雪花算法 id 工具类
 * <pre>
 *     1、本工具类基于开源框架：<a href="https://github.com/yitter/IdGenerator">IdGenerator</a> 实现；
 *     2、使用该工具类前，需要以下步骤：
 *      ①、启用 {@link EnableSnowflakeId}；
 *      ②、实现 {@link SnowflakeIdConfig} 设置自己的 workerId；
 *     3、支持扫描多包下 {@link SnowflakeIdConfig} 的实现类，使用 {@link SnowflakeIdConfigScan} 配置包路径即可；
 * </pre>
 *
 * @author wjm
 * @since 2022-08-29 09:04:19
 */
public class SnowflakeIds {

    private SnowflakeIds() {
    }

    /**
     * 获取 id
     *
     * @return id
     */
    public static long getId() {
        return YitIdHelper.nextId();
    }

}
