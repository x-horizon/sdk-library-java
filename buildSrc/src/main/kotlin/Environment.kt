enum class Environment(val code: String, val handler: EnvironmentHandler) {

    LOCALHOST("localhost", EnvironmentLocalhostHandler),
    DEVELOPMENT("development", EnvironmentDevelopmentHandler),

    ;

}