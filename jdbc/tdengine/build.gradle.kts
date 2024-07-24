import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.JDBC_TDENGINE.withoutVersion) { exclude(group = "commons-logging", module = "commons-logging") }
}