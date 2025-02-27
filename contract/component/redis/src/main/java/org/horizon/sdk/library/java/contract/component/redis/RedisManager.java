package org.horizon.sdk.library.java.contract.component.redis;

import org.horizon.sdk.library.java.tool.spring.contract.support.Springs;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.redisson.api.RedissonClient;

/**
 * redisson component manager
 *
 * @author wjm
 * @since 2020-12-12 18:06
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisManager {

    /**
     * singleton pattern
     */
    private static final class RedissonClientSingleTonHolder {

        private static final RedissonClient REDISSON_CLIENT_INSTANCE = Springs.getBean(RedissonClient.class);

    }

    /**
     * get singleton redisson client
     *
     * @return singleton redisson client
     */
    public static RedissonClient getClient() {
        return RedissonClientSingleTonHolder.REDISSON_CLIENT_INSTANCE;
    }

}