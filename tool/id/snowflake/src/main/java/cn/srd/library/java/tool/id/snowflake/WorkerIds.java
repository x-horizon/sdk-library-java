// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author wjm
 * @since 2023-11-11 19:06
 */
public interface WorkerIds extends Library {

    WorkerIds INSTANCE = Native.load("yitter-worker-id-generate", WorkerIds.class);

    int RegisterOne(String address, String password, long db, String sentinelMasterName, int minWorkerId, int maxWorkerId, int lifeTimeSeconds);

}
