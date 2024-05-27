// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.component.redis;

import cn.srd.library.java.tool.spring.contract.Springs;
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