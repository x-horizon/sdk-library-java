// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.contract.properties.CacheRedisProperties;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/**
 * @author wjm
 * @since 2023-11-13 10:42
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnowflakeIdOnStandAloneMultipleInstanceStrategy implements SnowflakeIdEnvironmentStrategy {

    private static final byte MIN_WORKER_ID = 1;

    @Override
    public short getWorkerId(EnableSnowflakeId snowflakeIdConfig) {
        RedisProperties redisProperties = CacheRedisProperties.getInstance().getBaseInfo();
        return (short) WorkerIds.INSTANCE.getWorkerId(
                Nil.isBlank(redisProperties.getUrl()) ? Strings.format("{}:{}", redisProperties.getHost(), redisProperties.getPort()) : redisProperties.getUrl(),
                redisProperties.getPassword(),
                redisProperties.getDatabase(),
                Nil.isNull(redisProperties.getSentinel()) ? SymbolConstant.EMPTY : redisProperties.getSentinel().getMaster(),
                MIN_WORKER_ID,
                computeMaxWorkedId(snowflakeIdConfig.workerIdBitLength()),
                snowflakeIdConfig.workerIdCacheTimeout()
        );
    }

    private int computeMaxWorkedId(byte workerIdBitLength) {
        // 2 ^ workerIdBitLength - 1
        // for example:
        //  default worker id bit length: 2 ^ 6 - 1 = 63
        //  min worked id bit length:     2 ^ 1 - 1 = 1
        //  max worked id bit length:     2 ^ 15 - 1 = 32767
        int maxWorkerId = (1 << workerIdBitLength) - 1;
        if (maxWorkerId == 0) {
            maxWorkerId = 63;
        }
        return maxWorkerId;
    }

}
