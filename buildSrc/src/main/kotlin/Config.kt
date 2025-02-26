object GradleConfig {

    // internal val activeEnvironment = Environment.LOCALHOST
    internal val activeEnvironment = Environment.DEVELOPMENT
    // internal val activeEnvironment = Environment.PRODUCTION

    const val GROUP_ID = "io.github.x-horizon"
    const val PROJECT_VERSION = "1.0"
    const val PROJECT_CHARSET = "UTF-8"

    const val AUTHOR_NAME = "jimmy"
    const val AUTHOR_EMAIL = "wjmwss@outlook.com"
    const val AUTHOR_URL = "https://gitee.com/wjmwss"

    const val PROJECT_URL = "https://github.com/x-horizon/library-java"
    const val PROJECT_CONNECTION = "scm:git:$PROJECT_URL.git"
    const val PROJECT_DEVELOPER_CONNECTION = "scm:git:ssh://$PROJECT_URL.git"
    const val PROJECT_LICENSE_NAME = "The Apache License, Version 2.0"
    const val PROJECT_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val PROJECT_DESCRIPTION = "the java library for all."
    const val PROJECT_INCEPTION_YEAR = "2022"

    const val JAVA_VERSION = "21"

    const val WITH_PARAMETERS_ARG = "-parameters"
    const val WITH_ENABLE_PREVIEW_ARG = "--enable-preview"
    const val WITH_ENABLE_DYNAMIC_AGENT_LOADING = "-XX:+EnableDynamicAgentLoading"

    const val ACTIVE_ENVIRONMENT_FIELD_NAME = "activeEnvironmentName"
    var activeEnvironmentName = activeEnvironment.handler.getActiveEnvironmentName()

}