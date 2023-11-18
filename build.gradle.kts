// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

plugins {
    id(GradlePlugin.JAVA_LIBRARY)
    id(GradlePlugin.MAVEN_PUBLISH)
    id(GradlePlugin.CHECK_STYLE)
}

allprojects {
    apply(plugin = GradlePlugin.MAVEN_PUBLISH)
    apply(plugin = GradlePlugin.CHECK_STYLE)

    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/spring/")
        maven("https://repo.spring.io/milestone/")
        maven("https://repo.spring.io/snapshot/")
        mavenCentral()
    }

    checkstyle {
        toolVersion = GradlePlugin.CHECK_STYLE_VERSION
        configFile = file("$rootDir" + GradlePlugin.CHECK_STYLE_CONFIG_PATH)
    }

    tasks.withType<JavaCompile> {
        options.release = Integer.valueOf(GradleConfig.JAVA_VERSION)
        options.encoding = GradleConfig.PROJECT_CHARSET
        options.compilerArgs.plusAssign(GradleConfig.projectCompileArgs)
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<GenerateModuleMetadata> {
        suppressedValidationErrors.add("enforced-platform")
    }
}

subprojects {
    if (GradleModule.toModuleName(project.toString()) != GradleModule.BOM) {
        apply(plugin = GradlePlugin.JAVA_LIBRARY)

        dependencies {
            api(enforcedPlatform(project(GradleModule.toReferenceName(GradleModule.BOM))))
            annotationProcessor(enforcedPlatform(project(GradleModule.toReferenceName(GradleModule.BOM))))
            testAnnotationProcessor(enforcedPlatform(project(GradleModule.toReferenceName(GradleModule.BOM))))

            compileOnly(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK.withoutVersion)
            annotationProcessor(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK.withoutVersion)
            testCompileOnly(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK.withoutVersion)
            testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_JMH)))
            testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_JUNIT)))
            testImplementation(project(GradleModule.toReferenceName(GradleModule.TEST_SPRING)))
            testAnnotationProcessor(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK.withoutVersion)
            testAnnotationProcessor(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH.withoutVersion)
            testAnnotationProcessor(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_AUTOCONFIGURE.withoutVersion)
            testAnnotationProcessor(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_CONFIGURATION.withoutVersion)
        }

        java {
            toolchain {
                vendor = JvmVendorSpec.AMAZON
                languageVersion = JavaLanguageVersion.of(GradleConfig.JAVA_VERSION)
            }
//            withJavadocJar()
            withSourcesJar()
        }

        publishing {
            publications {
                create<MavenPublication>(GradleRepository.REPOSITORY_DEFAULT_NAME) {
                    from(components[GradleRepository.COMPONENT_JAVA])
                    groupId = GradleRepository.GROUP_ID
                    artifactId = GradleModule.toModuleName(project.toString())
                    version = GradleConfig.PROJECT_VERSION
                }
                repositories {
                    maven {
                        isAllowInsecureProtocol = true
                        url = uri(GradleRepository.nexusUrl)
                        credentials { username = GradleRepository.NEXUS_USERNAME }
                        credentials { password = GradleRepository.NEXUS_PASSWORD }
                    }
                }
            }
        }
    }
}


