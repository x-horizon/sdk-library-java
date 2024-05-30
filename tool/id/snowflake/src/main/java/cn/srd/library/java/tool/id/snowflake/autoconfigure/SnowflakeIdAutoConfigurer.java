// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake.autoconfigure;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.spring.contract.Annotations;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Java Tool Snowflake ID
 *
 * @author wjm
 * @see EnableSnowflakeId
 * @since 2023-11-13 10:26
 */
@Slf4j
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@ConditionalOnBean(SnowflakeIdSwitcher.class)
public class SnowflakeIdAutoConfigurer implements SmartInitializingSingleton {

    /**
     * the min value of worker id bit length
     */
    private static final byte MIN_WORKER_ID_BIT_LENGTH = 1;

    /**
     * the max value of worker id bit length
     */
    private static final byte MAX_WORKER_ID_BIT_LENGTH = 15;

    /**
     * the min value of the sequence bit length
     */
    private static final byte MIN_SEQUENCE_BIT_LENGTH = 4;

    /**
     * the max value of the sequence bit length
     */
    private static final byte MAX_SEQUENCE_BIT_LENGTH = 21;

    /**
     * the max value of the sum of {@link EnableSnowflakeId#workerIdBitLength()} and {@link EnableSnowflakeId#sequenceBitLength()}
     */
    private static final byte MAX_SUM_OF_WORKER_ID_BIT_LENGTH_AND_SEQUENCE_BIT_LENGTH = 22;

    @Override
    public void afterSingletonsInstantiated() {
        log.debug("{}snowflake id system is enabled, starting initializing...", ModuleView.TOOL_SNOWFLAKE_ID_SYSTEM);

        EnableSnowflakeId snowflakeIdConfig = Annotations.getAnnotation(EnableSnowflakeId.class);
        byte workerIdBitLength = snowflakeIdConfig.workerIdBitLength();
        byte sequenceBitLength = snowflakeIdConfig.sequenceBitLength();
        Assert.of().setMessage("{}found illegal work id bit length in [{}], the range of work id bit length is [{},{}], current work id bit length is [{}], please check!", ModuleView.TOOL_SNOWFLAKE_ID_SYSTEM, EnableSnowflakeId.class.getName(), MIN_WORKER_ID_BIT_LENGTH, MAX_WORKER_ID_BIT_LENGTH, workerIdBitLength)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(workerIdBitLength < MIN_WORKER_ID_BIT_LENGTH || workerIdBitLength > MAX_WORKER_ID_BIT_LENGTH);
        Assert.of().setMessage("{}found illegal sequence bit length in [{}], the range of sequence bit length is [{},{}], current sequence bit length is [{}], please check!", ModuleView.TOOL_SNOWFLAKE_ID_SYSTEM, EnableSnowflakeId.class.getName(), MIN_SEQUENCE_BIT_LENGTH, MAX_SEQUENCE_BIT_LENGTH, sequenceBitLength)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(sequenceBitLength < MIN_SEQUENCE_BIT_LENGTH || sequenceBitLength > MAX_SEQUENCE_BIT_LENGTH);
        Assert.of().setMessage("{}found illegal sum of work id bit length and sequence bit length in [{}], the max sum of work id bit length and sequence bit length is [{}], current work id bit length is [{}], current sequence bit length is [{}], please check!", ModuleView.TOOL_SNOWFLAKE_ID_SYSTEM, EnableSnowflakeId.class.getName(), MAX_SUM_OF_WORKER_ID_BIT_LENGTH_AND_SEQUENCE_BIT_LENGTH, workerIdBitLength, sequenceBitLength)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(workerIdBitLength + sequenceBitLength > MAX_SUM_OF_WORKER_ID_BIT_LENGTH_AND_SEQUENCE_BIT_LENGTH);

        IdGeneratorOptions idGeneratorOptions = new IdGeneratorOptions();
        idGeneratorOptions.WorkerIdBitLength = workerIdBitLength;
        idGeneratorOptions.SeqBitLength = sequenceBitLength;
        idGeneratorOptions.WorkerId = snowflakeIdConfig.environment().getStrategy().getWorkerId(snowflakeIdConfig);
        YitIdHelper.setIdGenerator(idGeneratorOptions);

        log.debug(""" 
                        {}loading configurations as following:
                        --------------------------------------------------------------------------------------------------------------------------------
                        workerIdBitLength = [{}]
                        seqBitLength      = [{}]
                        workerId          = [{}]
                        --------------------------------------------------------------------------------------------------------------------------------""",
                ModuleView.TOOL_SNOWFLAKE_ID_SYSTEM,
                idGeneratorOptions.WorkerIdBitLength,
                idGeneratorOptions.SeqBitLength,
                idGeneratorOptions.WorkerId
        );
        log.debug("{}snowflake id system initialized.", ModuleView.TOOL_SNOWFLAKE_ID_SYSTEM);
    }

}