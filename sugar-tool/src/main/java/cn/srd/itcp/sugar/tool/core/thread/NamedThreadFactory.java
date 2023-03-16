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

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;

/**
 * Nameable thread pool
 *
 * @author wjm
 * @since 2023-03-16 18:57:12
 */
public class NamedThreadFactory {

    /**
     * named the thread pool
     *
     * @param name thread pool name
     * @return self
     */
    public static ThreadFactory of(String name) {
        return new ThreadFactoryBuilder().setNameFormat(name).build();
    }

}
