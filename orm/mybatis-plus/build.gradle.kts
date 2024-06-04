// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_BASE)))

    api(GradleDependency.ORM_MYBATIS_PLUS.withoutVersion)
    api(GradleDependency.ORM_MYBATIS_PLUS_DYNAMIC_DATASOURCE.withoutVersion)
}