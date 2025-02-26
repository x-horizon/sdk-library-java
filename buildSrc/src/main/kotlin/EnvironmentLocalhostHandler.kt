object EnvironmentLocalhostHandler : EnvironmentHandler {

    override fun getActiveEnvironmentName(): String {
        return Environment.LOCALHOST.code
    }

    override fun getNexusUrl(): String {
        return GradleRepository.NEXUS_LOCALHOST_URL
    }

}