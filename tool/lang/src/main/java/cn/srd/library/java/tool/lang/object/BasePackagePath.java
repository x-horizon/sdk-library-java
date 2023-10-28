// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import cn.srd.library.java.tool.lang.functional.Functional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wjm
 * @since 2023-10-07 20:10
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasePackagePath {

    private static final Set<String> BASE_PACKAGE_PATHS = new HashSet<>();

    public static void register(String basePackagePath) {
        BASE_PACKAGE_PATHS.add(basePackagePath);
    }

    public static void register(String... basePackagePaths) {
        BASE_PACKAGE_PATHS.addAll(Arrays.asList(basePackagePaths));
    }

    public static void register(List<String> basePackagePath) {
        BASE_PACKAGE_PATHS.addAll(basePackagePath);
    }

    public static void register(Set<String> basePackagePath) {
        BASE_PACKAGE_PATHS.addAll(basePackagePath);
    }

    public static Set<String> get() {
        return BASE_PACKAGE_PATHS;
    }

    public static Set<String> get(String specifiedPackagePath) {
        BASE_PACKAGE_PATHS.stream()
                .filter(specifiedPackagePath::startsWith)
                .findAny()
                .ifPresentOrElse(Functional.emptyConsumer(), () -> BASE_PACKAGE_PATHS.add(specifiedPackagePath));
        return BASE_PACKAGE_PATHS;
    }

}
