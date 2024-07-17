// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    implementation(project(GradleModule.toReferenceName(GradleModule.OSS_LOCAL)))
    implementation(project(GradleModule.toReferenceName(GradleModule.OSS_MINIO)))
    implementation(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_WEBMVC)))
}