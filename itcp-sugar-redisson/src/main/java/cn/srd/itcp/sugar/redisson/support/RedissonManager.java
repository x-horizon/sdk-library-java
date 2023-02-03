package cn.srd.itcp.sugar.redisson.support;

import cn.srd.itcp.sugar.spring.tool.common.core.SpringsUtil;
import org.redisson.api.RedissonClient;

/**
 * Redisson 对象管理
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
public class RedissonManager {

    /**
     * slacker signal ton pattern
     */
    private static final class RedissonClientSingleTonHolder {
        private static final RedissonClient REDISSON_CLIENT_INSTANCE = SpringsUtil.getBean(RedissonClient.class);
    }

    /**
     * 获取 Redisson 客户端
     *
     * @return Redisson 客户端
     */
    public static RedissonClient getClient() {
        return RedissonClientSingleTonHolder.REDISSON_CLIENT_INSTANCE;
    }

}
