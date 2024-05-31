// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

object GradleConfig {

    // internal val activeEnvironment = Environment.LOCALHOST
    internal val activeEnvironment = Environment.DEVELOPMENT
    // internal val activeEnvironment = Environment.PRODUCTION

    const val JAVA_VERSION = "21"

    const val PROJECT_VERSION = "3.0100"
    const val PROJECT_CHARSET = "UTF-8"

    const val WITH_PARAMETERS_ARG = "-parameters"
    const val WITH_ENABLE_PREVIEW_ARG = "--enable-preview"

    const val ACTIVE_ENVIRONMENT_FIELD_NAME = "activeEnvironmentName"
    var activeEnvironmentName = activeEnvironment.handler.getActiveEnvironmentName()
}