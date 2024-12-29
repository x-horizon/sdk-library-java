dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.DOC_KNIFE4J_CONTRACT)))
    api(project(GradleModule.toReferenceName(GradleModule.POOL_DATABASE_HIKARICP)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_API)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_CONVERT_JACKSON)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_ENUMS)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_SPRING_CONTRACT)))

    api(GradleDependency.TOOL_VALIDATION_HIBERNATE.withoutVersion)

    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
}