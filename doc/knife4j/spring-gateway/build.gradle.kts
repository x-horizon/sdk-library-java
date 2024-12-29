dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.DOC_KNIFE4J_CONTRACT)))

    api(GradleDependency.DOC_XIAOYMIN_KNIFE4J_SPRING_GATEWAY.withoutVersion)
}