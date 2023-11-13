// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CONTRACT_MODEL)))

    api(GradleDependency.TOOL_ALIBABA_TRANSMITTABLE_THREAD_LOCAL.withoutVersion)
    api(GradleDependency.TOOL_BURNING_WAVE_CORE.withoutVersion)
    api(GradleDependency.TOOL_GOOGLE_GUAVA.withoutVersion)
    api(GradleDependency.TOOL_HUTOOL.withoutVersion)
    api(GradleDependency.TOOL_IO_VAVR.withoutVersion)
    api(GradleDependency.TOOL_REFLECT_ASM.withoutVersion)
    api(GradleDependency.TOOL_VJTOOL.withoutVersion)
}