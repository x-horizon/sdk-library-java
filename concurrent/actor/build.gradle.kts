// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))
    
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_API)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_JACKSON)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_ENUMS)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_LOG)))
}