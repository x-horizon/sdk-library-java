// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

object EnvironmentProductionHandler : EnvironmentHandler {

    override fun getActiveEnvironmentName(): String {
        return Environment.DEVELOPMENT.code
    }

    override fun getNexusUrl(): String {
        return GradleRepository.NEXUS_PRODUCTION_URL
    }

}