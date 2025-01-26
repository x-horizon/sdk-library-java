dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_SERVER_CONTRACT)))
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_JDK)))
    api(GradleDependency.WEB_IO_NETTY_CODEC_MQTT.withoutVersion)

    testImplementation(project(GradleModule.toReferenceName(GradleModule.CONCURRENT_ACTOR)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.MESSAGE_ENGINE_CLIENT_KAFKA)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX_POSTGRESQL)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX_TDENGINE)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT_PLUS)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
}