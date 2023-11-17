// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_CONTRACT_POSTGRESQL)))
    api(project(GradleModule.toReferenceName(GradleModule.ORM_MYBATIS_FLEX)))

    annotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
    testAnnotationProcessor(project(GradleModule.toReferenceName(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX)))
    testImplementation(project(GradleModule.toReferenceName(GradleModule.TOOL_JDK)))
}