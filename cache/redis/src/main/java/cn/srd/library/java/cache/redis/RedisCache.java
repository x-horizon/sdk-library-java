// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.cache.redis;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Redis Cache Operation
 *
 * @author wjm
 * @since 2023-01-12 10:37
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisCache {

    /**
     * singleton pattern
     */
    private static final RedisCache INSTANCE = new RedisCache();

    /**
     * get singleton instance
     *
     * @return instance
     */
    public static RedisCache getInstance() {
        return INSTANCE;
    }

    /**
     * 获取缓存操作实例（桶）
     *
     * @return 缓存操作实例
     */
    public RedisCacheTemplate withBucket() {
        return RedisCacheBucket.INSTANCE;
    }

    /**
     * 获取缓存操作实例（哈希）
     *
     * @return 缓存操作实例
     */
    public RedisCacheTemplate withHash() {
        return RedisCacheHash.INSTANCE;
    }

    /**
     * 获取缓存操作实例（List）
     *
     * @return 缓存操作实例
     */
    public RedisCacheTemplate withList() {
        return RedisCacheList.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Set）
     *
     * @return 缓存操作实例
     */
    public RedisCacheTemplate withSet() {
        return RedisCacheSet.INSTANCE;
    }

    /**
     * 获取缓存操作实例（Sorted Set）
     *
     * @return 缓存操作实例
     */
    public RedisCacheTemplate withSortedSet() {
        return RedisCacheSortedSet.INSTANCE;
    }

}