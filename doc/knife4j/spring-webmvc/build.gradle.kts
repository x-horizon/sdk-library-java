dependencies {
    api(project(GradleModule.DOC_KNIFE4J_CONTRACT))
    api(project(GradleModule.TOOL_SPRING_WEBMVC))

    api(GradleDependency.DOC_XIAOYMIN_KNIFE4J_OPENAPI3_JAKARTA_SPRING_WEBMVC.withoutVersion)
}