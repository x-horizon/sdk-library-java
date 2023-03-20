package cn.srd.itcp.sugar.component.actor.thingsboard.core;

import cn.srd.itcp.sugar.tool.core.SystemsUtil;
import cn.srd.itcp.sugar.tool.core.Threads;

import java.util.concurrent.ExecutorService;

/**
 * signal actor system
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorSystem {

    /**
     * single concurrency count
     */
    int SINGLE_CONCURRENCY_COUNT = 1;

    /**
     * init actor system
     */
    void init();

    /**
     * destroy actor system
     */
    void destroy();

    /**
     * create dispatcher executor
     *
     * @param dispatcherName dispatcher name
     * @param parallelism    concurrency count
     * @return dispatcher executor
     */
    default ExecutorService newDispatcherExecutor(String dispatcherName, int parallelism) {
        if (parallelism == 0) {
            parallelism = Math.max(SINGLE_CONCURRENCY_COUNT, SystemsUtil.getAvailableProcessors() / 2);
        }
        if (parallelism == SINGLE_CONCURRENCY_COUNT) {
            return Threads.newSingleThreadPool(dispatcherName);
        } else {
            return Threads.newWorkStealingThreadPool(parallelism, dispatcherName);
        }
    }

}
