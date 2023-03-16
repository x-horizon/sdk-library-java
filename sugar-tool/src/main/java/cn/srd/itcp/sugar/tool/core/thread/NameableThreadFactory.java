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
package cn.srd.itcp.sugar.tool.core.thread;

import javax.annotation.Nonnull;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * copy of {@link Executors.DefaultThreadFactory} to make it nameable
 *
 * @author wjm
 * @since 2023-03-16 18:57:12
 */
public class NameableThreadFactory implements ThreadFactory {

    /**
     * see {@link Executors.DefaultThreadFactory#poolNumber}
     */
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    /**
     * see {@link Executors.DefaultThreadFactory#group}
     */
    private final ThreadGroup group;

    /**
     * see {@link Executors.DefaultThreadFactory#threadNumber}
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * see {@link Executors.DefaultThreadFactory#namePrefix}
     */
    private final String namePrefix;

    /**
     * see {@link Executors.DefaultThreadFactory#DefaultThreadFactory()} and delete {@link SecurityManager} code here because it is deprecated and marked for removal
     */
    private NameableThreadFactory(String name) {
        group = Thread.currentThread().getThreadGroup();
        namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
    }

    /**
     * named the thread pool
     *
     * @param name thread pool name
     * @return self
     */
    public static NameableThreadFactory forName(String name) {
        return new NameableThreadFactory(name);
    }

    @Override
    public Thread newThread(@Nonnull Runnable runnable) {
        Thread thread = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
        if (thread.isDaemon())
            thread.setDaemon(false);
        if (thread.getPriority() != Thread.NORM_PRIORITY)
            thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }

}
