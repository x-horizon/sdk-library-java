// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_JTS.withoutVersion)
    api(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_SPATIAL4J.withoutVersion)
}