// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

object GradleConfig {

    // internal val activeEnvironment = Environment.LOCALHOST
    internal val activeEnvironment = Environment.DEVELOPMENT
    // internal val activeEnvironment = Environment.PRODUCTION

    const val JAVA_VERSION = "17"

    const val PROJECT_VERSION = "3.0024"
    const val PROJECT_CHARSET = "UTF-8"

    val projectCompileArgs = setOf(
        "-parameters",
    )

}

enum class Environment(val handler: EnvironmentHandler) {
    LOCALHOST(environmentLocalhostHandler),
    DEVELOPMENT(environmentDevelopmentHandler),
    PRODUCTION(environmentProductionHandler),
}

fun interface EnvironmentHandler {
    fun getNexusUrl(): String
}

val environmentLocalhostHandler = EnvironmentHandler { GradleRepository.NEXUS_LOCALHOST_URL }
val environmentDevelopmentHandler = EnvironmentHandler { GradleRepository.NEXUS_DEVELOPMENT_URL }
val environmentProductionHandler = EnvironmentHandler { GradleRepository.NEXUS_PRODUCTION_URL }