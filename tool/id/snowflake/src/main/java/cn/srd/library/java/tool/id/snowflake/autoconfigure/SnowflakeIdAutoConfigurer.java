package cn.srd.library.java.tool.id.snowflake.autoconfigure;

import cn.hutool.core.lang.Console;
import cn.srd.library.java.tool.id.snowflake.EnableSnowflakeId;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIdSwitcher;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.spring.contract.Annotations;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

@AutoConfigureAfter(RedissonAutoConfiguration.class)
@ConditionalOnBean(SnowflakeIdSwitcher.class)
public class SnowflakeIdAutoConfigurer implements SmartInitializingSingleton {

    private static final byte MIN_WORKER_ID_BIT_LENGTH = 1;

    private static final byte MAX_WORKER_ID_BIT_LENGTH = 15;

    private static final byte MIN_SEQUENCE_BIT_LENGTH = 4;

    private static final byte MAX_SEQUENCE_BIT_LENGTH = 21;

    private static final byte MAX_SUM_OF_WORKER_ID_BIT_LENGTH_AND_SEQUENCE_BIT_LENGTH = 22;

    @Override
    public void afterSingletonsInstantiated() {
        EnableSnowflakeId snowflakeIdConfig = Annotations.getAnnotation(EnableSnowflakeId.class);
        byte workerIdBitLength = snowflakeIdConfig.workerIdBitLength();
        byte sequenceBitLength = snowflakeIdConfig.sequenceBitLength();
        Assert.of().setMessage("").throwsIfTrue(workerIdBitLength < MIN_WORKER_ID_BIT_LENGTH || workerIdBitLength > MAX_WORKER_ID_BIT_LENGTH);
        Assert.of().setMessage("").throwsIfTrue(sequenceBitLength < MIN_SEQUENCE_BIT_LENGTH || sequenceBitLength > MAX_SEQUENCE_BIT_LENGTH);
        Assert.of().setMessage("").throwsIfTrue(workerIdBitLength + sequenceBitLength > MAX_SUM_OF_WORKER_ID_BIT_LENGTH_AND_SEQUENCE_BIT_LENGTH);
        short workedId = snowflakeIdConfig.environment().getStrategy().getWorkerId(snowflakeIdConfig);
        IdGeneratorOptions idGeneratorOptions = new IdGeneratorOptions();
        idGeneratorOptions.WorkerIdBitLength = workerIdBitLength;
        idGeneratorOptions.SeqBitLength = sequenceBitLength;
        idGeneratorOptions.WorkerId = workedId;
        YitIdHelper.setIdGenerator(idGeneratorOptions);
    }

    public static void main(String[] args) {
        Console.log((1 << 6) - 1);
    }

}
