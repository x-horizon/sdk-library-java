plugins {
    id(GradlePlugin.JAVA_LIBRARY)
    id(GradlePlugin.MAVEN_PUBLISH) version (GradlePlugin.MAVEN_PUBLISH_VERSION)
    id(GradlePlugin.CHECK_STYLE)
}

allprojects {
    apply(plugin = GradlePlugin.MAVEN_PUBLISH)
    apply(plugin = GradlePlugin.CHECK_STYLE)

    group = GradleConfig.GROUP_ID
    version = GradleConfig.PROJECT_VERSION

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

    tasks.withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.withType<JavaCompile> {
        options.release = Integer.valueOf(GradleConfig.JAVA_VERSION)
        options.encoding = GradleConfig.PROJECT_CHARSET
        options.compilerArgs = listOf(
            GradleConfig.WITH_PARAMETERS_ARG,
            GradleConfig.WITH_ENABLE_PREVIEW_ARG,
            // "-Xlint:all",
        )
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        jvmArgs(
            GradleConfig.WITH_ENABLE_PREVIEW_ARG,
            GradleConfig.WITH_ENABLE_DYNAMIC_AGENT_LOADING,
        )
    }

    tasks.withType<JavaExec> {
        jvmArgs(
            GradleConfig.WITH_ENABLE_PREVIEW_ARG,
            GradleConfig.WITH_ENABLE_DYNAMIC_AGENT_LOADING,
        )
    }

    tasks.withType<GenerateModuleMetadata> {
        suppressedValidationErrors.add("enforced-platform")
    }
}

subprojects {
    if (project.path != GradleModule.BOM) {
        apply(plugin = GradlePlugin.JAVA_LIBRARY)

        dependencies {
            api(enforcedPlatform(project(GradleModule.BOM)))
            annotationProcessor(enforcedPlatform(project(GradleModule.BOM)))
            testAnnotationProcessor(enforcedPlatform(project(GradleModule.BOM)))

            if (project.path != GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK) {
                compileOnly(project(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK))
                annotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK))
                testCompileOnly(project(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK))
                testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_LOMBOK))
            }

            testImplementation(project(GradleModule.TEST_JMH))
            testImplementation(project(GradleModule.TEST_JUNIT))
            testImplementation(project(GradleModule.TOOL_LOG))
            testImplementation(project(GradleModule.TEST_SPRING_BOOT))
            testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_JMH))
            testAnnotationProcessor(project(GradleModule.PLUGGABLE_ANNOTATION_API_PROCESSOR_SPRING))
        }

        java {
            toolchain {
                vendor = JvmVendorSpec.AMAZON
                languageVersion = JavaLanguageVersion.of(GradleConfig.JAVA_VERSION)
            }
//            withJavadocJar()
            withSourcesJar()
            modularity.inferModulePath = true
        }

        sourceSets {
            main {
                resources {
                    setSrcDirs(listOf("src/main/java", "src/main/resources"))
                }
            }
        }

        mavenPublishing {
            configure(com.vanniktech.maven.publish.JavaLibrary(
                javadocJar = com.vanniktech.maven.publish.JavadocJar.Empty(),
                sourcesJar = true,
            ))
            publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
            signAllPublications()
            pom {
                name.set(project.name)
                description.set(GradleConfig.PROJECT_DESCRIPTION)
                inceptionYear.set(GradleConfig.PROJECT_INCEPTION_YEAR)
                url.set(GradleConfig.PROJECT_URL)
                licenses {
                    license {
                        name.set(GradleConfig.PROJECT_LICENSE_NAME)
                        url.set(GradleConfig.PROJECT_LICENSE_URL)
                        distribution.set(GradleConfig.PROJECT_LICENSE_URL)
                    }
                }
                developers {
                    developer {
                        id.set(GradleConfig.AUTHOR_NAME)
                        name.set(GradleConfig.AUTHOR_NAME)
                        email.set(GradleConfig.AUTHOR_EMAIL)
                        url.set(GradleConfig.AUTHOR_URL)
                    }
                }
                scm {
                    url.set(GradleConfig.PROJECT_URL)
                    connection.set(GradleConfig.PROJECT_CONNECTION)
                    developerConnection.set(GradleConfig.PROJECT_DEVELOPER_CONNECTION)
                }
            }
        }

        publishing {
            repositories {
                maven {
                    name = GradleRepository.MAVEN_PERSONAL_NAME
                    url = uri(project.findProperty(GradleRepository.mavenPersonalUrlEnvironmentName) as? String ?: GradleRepository.FAKE_VALUE)
                    isAllowInsecureProtocol = true
                    credentials { username = project.findProperty(GradleRepository.MAVEN_PERSONAL_USERNAME_ENVIRONMENT_NAME) as? String ?: GradleRepository.FAKE_VALUE }
                    credentials { password = project.findProperty(GradleRepository.MAVEN_PERSONAL_PASSWORD_ENVIRONMENT_NAME) as? String ?: GradleRepository.FAKE_VALUE }
                }
            }
        }

        tasks.processResources {
            filesMatching("**/*.yaml") {
                expand(
                    GradleConfig.ACTIVE_ENVIRONMENT_FIELD_NAME to GradleConfig.activeEnvironmentName,
                )
            }
        }

        tasks.processTestResources {
            filesMatching("**/*.yaml") {
                expand(
                    GradleConfig.ACTIVE_ENVIRONMENT_FIELD_NAME to GradleConfig.activeEnvironmentName,
                )
            }
        }
    }
}