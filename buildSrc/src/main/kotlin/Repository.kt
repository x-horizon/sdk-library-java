// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

object GradleRepository {

    const val GROUP_ID = "cn.srd.library.java"

    const val REPOSITORY_DEFAULT_NAME = "Maven"

    const val COMPONENT_JAVA = "java"
    const val COMPONENT_JAVA_PLATFORM = "javaPlatform"

    val nexusUrl = GradleConfig.activeEnvironment.handler.getNexusUrl()
    const val NEXUS_USERNAME = "srd-library-java"
    const val NEXUS_PASSWORD = "kVPH8d9hktREbQ"
    const val NEXUS_LOCALHOST_URL = "http://localhost:10015/repository/srd-library-java/"
    const val NEXUS_DEVELOPMENT_URL = "http://192.168.10.91:32122/repository/srd-library-java/"
    const val NEXUS_PRODUCTION_URL = "http://192.168.10.86:32208/repository/srd-library-java/"

}