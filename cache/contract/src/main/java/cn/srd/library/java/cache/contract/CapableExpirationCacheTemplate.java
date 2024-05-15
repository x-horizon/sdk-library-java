// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.cache.contract;

/**
 * Cache Template - Support expiration、More operation
 *
 * @param <K> cache key type
 * @author wjm
 * @since 2023-06-05 16:41
 */
public interface CapableExpirationCacheTemplate<K> extends CapableCacheTemplate<K>, ExpirationCacheTemplate<K> {

}