package cn.srd.library.java.tool.id.snowflake.autoconfigure;

import cn.hutool.core.lang.Console;
import cn.srd.library.java.tool.id.snowflake.WorkerIds;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@AutoConfiguration
public class SnowflakeIdAutoConfigurer implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        IdGeneratorOptions options = new IdGeneratorOptions((short) WorkerIds.INSTANCE.getWorkerId("127.0.0.1:6379", "", 1L, "", 1, (1 << 6) - 1, 60));
        YitIdHelper.setIdGenerator(options);
    }

    public static void main(String[] args) {
        WorkerIds.INSTANCE.getWorkerId("127.0.0.1:6379", "", 1L, "", 1, (1 << 6) - 1, 60);
        Console.log((1 << 6) - 1);
    }

}
