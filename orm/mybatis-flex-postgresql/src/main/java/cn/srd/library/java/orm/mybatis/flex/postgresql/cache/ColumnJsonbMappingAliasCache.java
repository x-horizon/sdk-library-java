// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.cache;

import cn.srd.library.java.tool.lang.collection.Collections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author wjm
 * @since 2024-04-23 20:17
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnJsonbMappingAliasCache {

    private static final Map<String, String> CACHE = Collections.newConcurrentHashMap(256);

    public static String get(String key) {
        return CACHE.get(key);
    }

    public static String set(String key, String value) {
        return CACHE.putIfAbsent(key, value);
    }

}