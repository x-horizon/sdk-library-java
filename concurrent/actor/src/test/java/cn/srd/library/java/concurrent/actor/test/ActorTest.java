// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.concurrent.actor.test;

import cn.srd.library.java.concurrent.actor.foo.TelemetryActorEvent;
import cn.srd.library.java.concurrent.actor.foo.TelemetryActorType;
import cn.srd.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * actor test
 *
 * @author wjm
 * @since 2024-08-20 17:26
 */
@EnableEnumAutowired
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ActorTest {

    @Test
    void testActor() {
        TelemetryActorType.ROOT.getStrategy().getMailbox().tell(TelemetryActorEvent.builder().build());
        TelemetryActorType.ROOT.getStrategy().getMailbox().tell(TelemetryActorEvent.builder().build());
    }

}