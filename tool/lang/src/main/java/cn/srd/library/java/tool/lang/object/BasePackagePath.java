// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.tool.lang.collection.Collections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Set;

/**
 * @author wjm
 * @since 2023-10-07 20:10
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings(SuppressWarningConstant.UNUSED)
public class BasePackagePath {

    // TODO wjm the concurrent safe read and write does not valid because of the write is replace instead of write on the CopyOnWriteArraySet, see #optimize()
    private static Set<String> basePackagePaths = Collections.newCopyOnWriteArraySet();

    public static Set<String> register(String specifiedBasePackagePath) {
        return register(Collections.ofArray(String.class, specifiedBasePackagePath));
    }

    public static Set<String> register(String... specifiedBasePackagePaths) {
        return register(Collections.ofArrayList(specifiedBasePackagePaths));
    }

    public static Set<String> register(Collection<String> specifiedBasePackagePaths) {
        basePackagePaths.addAll(specifiedBasePackagePaths);
        optimize();
        Set<String> currentBasePackagePaths = get();
        log.debug("{}the path {} has already been registered to global base package paths, current global base package paths: {}.", ModuleView.TOOL_CLASS_SYSTEM, specifiedBasePackagePaths, currentBasePackagePaths);
        return currentBasePackagePaths;
    }

    public static Set<String> get() {
        return basePackagePaths;
    }

    private static void optimize() {
        basePackagePaths = Classes.getTheLargestRangePackagePath(basePackagePaths);
    }

}
