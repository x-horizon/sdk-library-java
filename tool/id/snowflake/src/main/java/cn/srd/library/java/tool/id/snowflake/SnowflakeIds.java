package cn.srd.library.java.tool.id.snowflake;

import com.github.yitter.idgen.YitIdHelper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
 * @since 2022-08-29 09:04
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SnowflakeIds {

    /**
     * 获取 id
     *
     * @return id
     */
    public static long getId() {
        return YitIdHelper.nextId();
    }

    public interface Clibrary extends Library {

        Clibrary instance = Native.load("yitter-worker-id-generate", Clibrary.class);
        // Clibrary instance = Native.load("/Users/jimmy/Documents/Workspace/work/srd/itcp-library/library-java-v30/tool/id/snowflake/src/main/resources/linux-x86-64/workeridgo-linux.dll", Clibrary.class);

        // 与动态库中的函数相对应
        int RegisterOne(String address, String password, long db, String sentinelMasterName, int minWorkerId, int maxWorkerId, int lifeTimeSeconds);

    }

    public static void main(String[] args) {
        int a = Clibrary.instance.RegisterOne("192.168.10.91:30570", "", 1L, "", 1, 3000, 60);
    }

}
