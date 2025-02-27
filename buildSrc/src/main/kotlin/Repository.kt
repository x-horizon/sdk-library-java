object GradleRepository {

    const val FAKE_VALUE = "fakeValue"

    val mavenPersonalUrlEnvironmentName = GradleConfig.activeEnvironment.handler.getMavenPersonalUrlEnvironmentName()
    const val MAVEN_PERSONAL_NAME = "MavenPersonal"
    const val MAVEN_PERSONAL_USERNAME_ENVIRONMENT_NAME = "mavenPersonalUsername"
    const val MAVEN_PERSONAL_PASSWORD_ENVIRONMENT_NAME = "mavenPersonalPassword"
    const val MAVEN_PERSONAL_LOCALHOST_URL_ENVIRONMENT_NAME = "mavenPersonalLocalhostUrl"
    const val MAVEN_PERSONAL_DEVELOPMENT_URL_ENVIRONMENT_NAME = "mavenPersonalDevelopmentUrl"

}