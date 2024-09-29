// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.concurrent.actor.my;

import cn.srd.library.java.concurrent.actor.original.TbActorId;
import cn.srd.library.java.concurrent.actor.original.TbActorMsg;

/**
 * @author wjm
 * @since 2024-09-25 09:12
 */
public interface ActorReference {

    TbActorId getActorId();

    void tell(TbActorMsg actorMsg);

    void tellWithHighPriority(TbActorMsg actorMsg);

}