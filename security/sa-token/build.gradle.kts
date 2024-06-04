// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.CACHE_LETTUCE)))
    api(project(GradleModule.toReferenceName(GradleModule.POOL_APACHE)))
    api(project(GradleModule.toReferenceName(GradleModule.SECURITY_CONTRACT)))

    api(GradleDependency.SECURITY_SA_TOKEN_ALONE_REDIS.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_DAO_REDIS_JACKSON.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_OAUTH2.withoutVersion)
    api(GradleDependency.SECURITY_SA_TOKEN_SPRING_BOOT_STARTER.withoutVersion)
}