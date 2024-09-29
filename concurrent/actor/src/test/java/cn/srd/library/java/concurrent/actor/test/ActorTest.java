// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.concurrent.actor.test;

import cn.srd.library.java.concurrent.actor.foo.TelemetryActorEvent;
import cn.srd.library.java.concurrent.actor.foo.TelemetryActorType;
import cn.srd.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * actor test
 *
 * @author wjm
 * @since 2024-08-20 17:26
 */
@Slf4j
@EnableEnumAutowired
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ActorTest {

    @Test
    void testActor() {
        TelemetryActorType.ROOT.getStrategy().getMailbox().tell(TelemetryActorEvent.builder().build());
        TelemetryActorType.ROOT.getStrategy().getMailbox().tell(TelemetryActorEvent.builder().build());
    }

    private static int countDownLatchTimeout = 5;

    @SneakyThrows
    @Test
    void testActor2() {
        // ExecutorService cachedThreadPool = Executors.newVirtualThreadPerTaskExecutor();
        // Thread.startVirtualThread(runnable);
        // Thread.ofVirtual().name(name).start(runnable);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        @Cleanup ExecutorService cachedThreadPool = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 5; i++) {
            cachedThreadPool.execute(() -> {
                try {
                    log.debug(countDownLatch.toString());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }

            });
        }

        countDownLatch.await(countDownLatchTimeout, TimeUnit.MINUTES);
        cachedThreadPool.shutdown();
    }

}