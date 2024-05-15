// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.cache.redis;

import cn.srd.library.java.cache.contract.CapableExpirationCacheTemplate;
import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.lang.time.DurationWrapper;
import cn.srd.library.java.tool.lang.time.Times;

import java.util.concurrent.TimeUnit;

/**
 * Redis Cache Template
 *
 * @author wjm
 * @since 2023-06-05 16:41
 */
public interface RedisCacheTemplate extends CapableExpirationCacheTemplate<String> {

    /**
     * 模糊查询某个命名空间的关键字
     */
    String NAMESPACE_KEY_WORD = "*";

    @Override
    default String resolveKey(String key, String extensionKey) {
        if (Strings.endWith(extensionKey, SymbolConstant.COLON)) {
            return extensionKey + key;
        }
        return extensionKey + SymbolConstant.COLON + key;
    }

    /**
     * resolve fuzzy find, example: my-cache:*
     *
     * @param namespace the namespace
     * @return the fuzzy key
     */
    default String resolveFuzzyKey(String namespace) {
        if (Strings.endWith(namespace, SymbolConstant.COLON)) {
            return namespace + NAMESPACE_KEY_WORD;
        }
        return namespace + SymbolConstant.COLON + NAMESPACE_KEY_WORD;
    }

    /**
     * redis expire time or ttl =&gt; {@link DurationWrapper}
     *
     * @param time redis expire time or ttl
     * @return {@link DurationWrapper}
     */
    default DurationWrapper toDurationWrapper(long time) {
        return Times.toDurationWrapper(Times.wrapper(time, TimeUnit.MILLISECONDS).toMillisecond());
    }

}