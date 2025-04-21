dependencies {
    api(project(GradleModule.TOOL_LANG))
    implementation(GradleDependency.DOC_SPRING_OPENAPI_COMMON.withoutVersion)
    implementation(GradleDependency.TOOL_SQUAREUP_JAVAPOET.withoutVersion)
    compileOnly(GradleDependency.TOOL_SPI_GOOGLE_AUTO_SERVICE.withoutVersion)
    annotationProcessor(GradleDependency.TOOL_SPI_GOOGLE_AUTO_SERVICE.withoutVersion)
}