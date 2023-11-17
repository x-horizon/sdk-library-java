// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake;

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

    /**
     * get a snowflake id
     *
     * @return snowflake id
     */
    public static long get() {
        return YitIdHelper.nextId();
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
    protected interface WorkerId extends Library {

        WorkerId INSTANCE = Native.load("worker-id-generate", WorkerId.class);

        int GetWorkerId(String address, String password, long db, String sentinelMasterName, int minWorkerId, int maxWorkerId, int lifeTimeSeconds);

    }

}
