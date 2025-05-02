package org.horizon.sdk.library.java.tool.lang.object;

import lombok.SneakyThrows;
import org.dromara.hutool.core.date.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author wjm
 * @since 2025-04-28 22:39
 */
public class FakeSharingTest {

    public static class FakeSharing {

        public volatile long a;

        // public long p1, p2, p3, p4, p5, p6, p7;

        public volatile long b;

    }

    @SneakyThrows
    public static void main(String[] args) {
        FakeSharing fakeSharing = new FakeSharing();
        Thread virtualThread1 = Thread.ofVirtual().unstarted(() -> IntStream.range(0, 50000000).forEach(_ -> fakeSharing.a++));
        Thread virtualThread2 = Thread.ofVirtual().unstarted(() -> IntStream.range(0, 50000000).forEach(_ -> fakeSharing.b++));
        StopWatch stopWatch = StopWatch.of();
        stopWatch.start();
        virtualThread1.start();
        virtualThread2.start();
        virtualThread1.join();
        virtualThread2.join();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
    }

}