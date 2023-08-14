/**
 * Copyright © 2016-2021 The Thingsboard Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.srd.library.java.tool.lang.core.thread;

import cn.hutool.core.thread.ThreadUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * thread tool
 *
 * @author wjm
 * @since 2023-03-16 18:57:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadsUtil extends ThreadUtil {

    /**
     * 获取线程 id
     *
     * @return 线程 id
     */
    public static long getId() {
        return Thread.currentThread().getId();
    }

    /**
     * 生成拼接了线程 id 的线程名称
     *
     * @param name 线程名称
     * @return 线程名称
     */
    public static String generateThreadName(String name) {
        return name + "-thread-" + getId();
    }

    /**
     * create {@link ThreadFactory} with name
     *
     * @param name thread pool name
     * @return {@link ThreadFactory}
     */
    public static ThreadFactory newThreadFactory(String name) {
        return new ThreadFactoryBuilder().setNameFormat(generateThreadName(name)).build();
    }

    /**
     * create {@link ForkJoinPool.ForkJoinWorkerThreadFactory} with name
     *
     * @param name thread pool name
     * @return {@link ForkJoinPool.ForkJoinWorkerThreadFactory}
     */
    public static ForkJoinPool.ForkJoinWorkerThreadFactory newForkJoinWorkerThreadFactory(String name) {
        return pool -> {
            ForkJoinWorkerThread forkJoinWorkerThread = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            forkJoinWorkerThread.setName(generateThreadName(name));
            return forkJoinWorkerThread;
        };
    }

    /**
     * create single thread pool with name
     *
     * @param name thread pool name
     * @return single thread pool
     */
    public static ExecutorService newSingleThreadPool(String name) {
        return Executors.newSingleThreadExecutor(newThreadFactory(name));
    }

    /**
     * create schedule thread pool with name
     *
     * @param corePoolSize see {@link Executors#newScheduledThreadPool(int, ThreadFactory)}
     * @param name         thread pool name
     * @return {@link ScheduledExecutorService}
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, String name) {
        return Executors.newScheduledThreadPool(corePoolSize, newThreadFactory(name));
    }

    /**
     * create {@link ForkJoinPool} with name
     *
     * @param parallelism see {@link ForkJoinPool#ForkJoinPool(int, ForkJoinPool.ForkJoinWorkerThreadFactory, Thread.UncaughtExceptionHandler, boolean)}
     * @param name        thread pool name
     * @return {@link ForkJoinPool}
     */
    public static ExecutorService newWorkStealingThreadPool(int parallelism, String name) {
        return new ForkJoinPool(parallelism, newForkJoinWorkerThreadFactory(name), null, true);
    }

}
