dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.ORM_CONTRACT_MYBATIS_BASE)))

    api(GradleDependency.ORM_MYBATIS_PLUS.withoutVersion)
    api(GradleDependency.ORM_MYBATIS_PLUS_DYNAMIC_DATASOURCE.withoutVersion)
}