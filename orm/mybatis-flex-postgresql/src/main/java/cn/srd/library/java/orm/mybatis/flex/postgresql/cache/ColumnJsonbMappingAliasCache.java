// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.postgresql.cache;

import cn.srd.library.java.contract.constant.database.PostgresqlFunction;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.orm.contract.model.base.POJO;
import cn.srd.library.java.orm.mybatis.flex.base.tool.ColumnNameGetter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.tool.id.snowflake.SnowflakeIds;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
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

    private static final Map<String, String> FUNCTION_SQL_MAPPING_THE_LAST_JSON_KEY_MAP = Collections.newConcurrentHashMap(256);

    public static <PJ extends POJO> String get(ColumnNameGetter<PJ> keyGetter, String key) {
        String classNameOfKey = Strings.lowerFirst(MybatisFlexs.getClassName(keyGetter));
        String alias = CACHE.get(getCacheKey(key, classNameOfKey));
        if (Nil.isNull(alias)) {
            return CACHE.get(getCacheKey(Strings.underlineCase(key), classNameOfKey));
        }
        return alias;
    }

    @CanIgnoreReturnValue
    public static <PJ extends POJO> String computeToCache(ColumnNameGetter<PJ> keyGetter) {
        return computeToCache(MybatisFlexs.getFieldName(keyGetter), MybatisFlexs.getClassName(keyGetter));
    }

    @CanIgnoreReturnValue
    public static <PJ extends POJO> String computeToUnderlineCaseCache(ColumnNameGetter<PJ> keyGetter) {
        return computeToCache(Strings.underlineCase(MybatisFlexs.getFieldName(keyGetter)), MybatisFlexs.getClassName(keyGetter));
    }

    @CanIgnoreReturnValue
    public static String computeToCache(String key, String classNameOfKey) {
        Assert.of().setMessage("{}illegal jsonb alias key: [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, key)
                .setThrowable(LibraryJavaInternalException.class)
                .throwsIfTrue(Strings.startWith(key, PostgresqlFunction.JSONB_ARRAY_UNNEST));

        if (Strings.startWith(key, PostgresqlFunction.JSONB_OBJECT_EXTRACT)) {
            String theLastJsonKey = FUNCTION_SQL_MAPPING_THE_LAST_JSON_KEY_MAP.get(key);
            if (Nil.isNull(theLastJsonKey)) {
                theLastJsonKey = Strings.getTheLastDoubleQuoteContent(key);
                String finalTheLastJsonKey = theLastJsonKey;
                FUNCTION_SQL_MAPPING_THE_LAST_JSON_KEY_MAP.computeIfAbsent(key, ignore -> finalTheLastJsonKey);
            }
            key = theLastJsonKey;
        }
        String finalKey = key;
        String finalClassNameOfKey = Strings.lowerFirst(classNameOfKey);
        return CACHE.computeIfAbsent(getCacheKey(key, finalClassNameOfKey), ignore -> Strings.format("\"{}_{}_{}\"", finalClassNameOfKey, finalKey, SnowflakeIds.getFixed()));
    }

    private static String getCacheKey(String key, String className) {
        return Strings.format("{}_{}", className, key);
    }

}