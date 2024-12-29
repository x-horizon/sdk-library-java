package cn.srd.library.java.tool.lang.concurrent;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * toolkit for thread
 *
 * @author wjm
 * @since 2024-08-20 17:16
 */
@SuppressWarnings(SuppressWarningConstant.PREVIEW)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Threads {

    /**
     * get current thread id
     *
     * @return current thread id
     */
    public static long getId() {
        return Thread.currentThread().threadId();
    }

    /**
     * format current thread name
     *
     * @param prefix the prefix
     * @return current thread name after formatted
     */
    public static String formatThreadName(String prefix) {
        return STR."\{prefix}-thread-\{getId()}";
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

    /**
     * create {@link ThreadFactory} with name
     *
     * @param name thread pool name
     * @return {@link ThreadFactory}
     */
    public static ThreadFactory newThreadFactory(String name) {
        return new ThreadFactoryBuilder().setNameFormat(formatThreadName(name)).build();
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
            forkJoinWorkerThread.setName(formatThreadName(name));
            return forkJoinWorkerThread;
        };
    }

}