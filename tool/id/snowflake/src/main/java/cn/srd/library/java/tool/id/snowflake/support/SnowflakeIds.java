// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake.support;

import cn.srd.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import cn.srd.library.java.tool.lang.convert.Converts;
import com.github.yitter.idgen.YitIdHelper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
     * <pre>
     * the jna function to build a worker id.
     *
     *   see the document as following:
     *     <img width="640" height="577" src="https://github.com/yitter/IdGenerator/blob/master/Tools/AutoRegisterWorkerId/regprocess.jpg" alt="">
     *     <a href="https://github.com/yitter/IdGenerator/blob/master/Tools/AutoRegisterWorkerId/regprocess.jpg">the worker id generate flow chart.</a>
     *     <a href="https://github.com/yitter/IdGenerator/blob/master/Tools/AutoRegisterWorkerId/README.md">the worker id generate document.</a>
     *     <a href="https://github.com/yitter/IdGenerator/tree/master/Go/regworkerid">the worker id generate source code.</a>
     * </pre>
     */
    public interface WorkerId extends Library {

        WorkerId INSTANCE = Native.load("worker-id-generate", WorkerId.class);

        int RegisterWorkerId(String address, String password, long db, String sentinelMasterName, int minWorkerId, int maxWorkerId, int lifeTimeSeconds);

    }

}