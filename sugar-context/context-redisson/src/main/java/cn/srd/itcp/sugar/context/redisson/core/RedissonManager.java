package cn.srd.itcp.sugar.context.redisson.core;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.redisson.api.RedissonClient;

/**
 * Redisson 对象管理
 *
 * @author wjm
 * @since 2020/12/12 18:06
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedissonManager {

    /**
     * singleton pattern
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
