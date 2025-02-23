package org.horizon.library.java.tool.id.snowflake.strategy;

import org.horizon.library.java.cache.redis.model.property.RedisCacheProperty;
import org.horizon.library.java.contract.constant.text.SymbolConstant;
import org.horizon.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import org.horizon.library.java.tool.id.snowflake.support.SnowflakeIds;
import org.horizon.library.java.tool.lang.object.Nil;
import org.horizon.library.java.tool.lang.text.Strings;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.DefaultIdGenerator;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/**
 * multiple node strategy
 *
 * @author wjm
 * @since 2023-11-13 10:42
 */
public class SnowflakeIdOnMultipleNodeStrategy implements SnowflakeIdEnvironmentStrategy {

    /**
     * the min worker id
     */
    private static final byte MIN_WORKER_ID = 1;

    @Override
    public short getWorkerId(EnableSnowflakeId snowflakeIdConfig) {
        RedisProperties redisProperties = RedisCacheProperty.getInstance().getBaseInfo();
        return (short) SnowflakeIds.WorkerId.INSTANCE.RegisterWorkerId(
                Nil.isBlank(redisProperties.getUrl()) ? Strings.format("{}:{}", redisProperties.getHost(), redisProperties.getPort()) : redisProperties.getUrl(),
                redisProperties.getPassword(),
                redisProperties.getDatabase(),
                Nil.isNull(redisProperties.getSentinel()) ? SymbolConstant.EMPTY : redisProperties.getSentinel().getMaster(),
                MIN_WORKER_ID,
                computeMaxWorkedId(snowflakeIdConfig.workerIdBitLength()),
                snowflakeIdConfig.workerIdCacheTimeout()
        );
    }

    /**
     * <pre>
     * compute the max worker id based on {@link EnableSnowflakeId#workerIdBitLength()}, it is the same as {@link DefaultIdGenerator#DefaultIdGenerator(IdGeneratorOptions) 3.WorkerId}.
     * the max worker id calculation: 2 ^ workerIdBitLength - 1;
     * </pre>
     *
     * @param workerIdBitLength the worker id bir length
     * @return the max worker id
     */
    private int computeMaxWorkedId(byte workerIdBitLength) {
        // for example:
        //  compute max worker id by default worker id bit length: 2 ^ 6 - 1 = 63
        //  compute max worker id by min worked id bit length:     2 ^ 1 - 1 = 1
        //  compute max worker id by max worked id bit length:     2 ^ 15 - 1 = 32767
        int maxWorkerId = (1 << workerIdBitLength) - 1;
        if (maxWorkerId == 0) {
            maxWorkerId = 63;
        }
        return maxWorkerId;
    }

}