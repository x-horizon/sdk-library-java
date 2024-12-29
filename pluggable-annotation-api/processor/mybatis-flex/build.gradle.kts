dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.ORM_MYBATIS_FLEX_ANNOTATION.withoutVersion)
}