dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_SQUAREUP_WIRE.withoutVersion)
}