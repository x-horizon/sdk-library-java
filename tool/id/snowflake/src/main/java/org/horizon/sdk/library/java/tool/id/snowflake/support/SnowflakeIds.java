package org.horizon.sdk.library.java.tool.id.snowflake.support;

import com.github.yitter.idgen.YitIdHelper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.horizon.sdk.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import org.horizon.sdk.library.java.tool.lang.convert.Converts;

/**
 * toolkit for snowflake id
 *
 * @author wjm
 * @see EnableSnowflakeId
 * @since 2022-08-29 09:04
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SnowflakeIds {

    private static final long FIXED_ID = get();

    /**
     * get a snowflake id
     *
     * @return snowflake id
     */
    public static long get() {
        return YitIdHelper.nextId();
    }

    /**
     * get a string snowflake id
     *
     * @return snowflake id
     */
    public static String getString() {
        return Converts.toString(get());
    }

    /**
     * get a fixed snowflake id
     *
     * @return fixed snowflake id
     */
    public static long getFixed() {
        return FIXED_ID;
    }

    /**
     * get a fixed string snowflake id
     *
     * @return fixed snowflake id
     */
    public static String getFixedString() {
        return Converts.toString(getFixed());
    }

    /**
     * <p>the JNA function to build a worker ID.</p>
     *
     * <p>related resources:</p>
     * <ul>
     *  <li><p>workflow diagram:</p>
     *  <a href="https://github.com/yitter/IdGenerator/blob/master/Tools/AutoRegisterWorkerId/regprocess.jpg">
     *  worker ID generation process
     *  </a></li>
     *
     *  <li><p>implementation document:</p>
     *  <a href="https://github.com/yitter/IdGenerator/blob/master/Tools/AutoRegisterWorkerId/README.md">
     *  worker ID generation documentation
     *  </a></li>
     *
     *  <li><p>source code reference:</p>
     *  <a href="https://github.com/yitter/IdGenerator/tree/master/Go/regworkerid">
     *  worker ID generation implementation
     *  </a></li>
     * </ul>
     */
    public interface WorkerId extends Library {

        WorkerId INSTANCE = Native.load("worker-id-generate", WorkerId.class);

        int RegisterWorkerId(String address, String password, long db, String sentinelMasterName, int minWorkerId, int maxWorkerId, int lifeTimeSeconds);

    }

}