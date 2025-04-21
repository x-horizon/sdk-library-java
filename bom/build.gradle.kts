plugins {
    id(GradlePlugin.JAVA_PLATFORM)
}

dependencies {
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_CACHE_REDISSON)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_CLOUD_NATIVE_DOCKER)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_DOC_SPRING_OPENAPI)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_DOC_XIAOYMIN_KNIFE4J)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_ECLIPSE_PAHO)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_ALIBABA_SPRING_CLOUD)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING_BOOT)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING_CLOUD)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_FRAMEWORK_SPRING_INTEGRATION)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_JDBC_POSTGIS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_METRIC_DROPWIZARD)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_METRIC_MICROMETER)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_ORM_MYBATIS_FLEX)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_ORM_MYBATIS_PLUS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_OSS_ALL_FACED)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_SECURITY_DEV33_SA_TOKEN)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TEST_MOCKITO)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TEST_TESTCONTAINERS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_CONTENT_ANALYSIS_APACHE_TIKA)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_DYNAMIC_TP)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_GEOMETRY_JTS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_MAPSTRUCT_PLUS)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_MAPSTRUCT_PROTOBUF)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_SERIALIZATION_FASTERXML_JACKSON)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_SERIALIZATION_GOOGLE_PROTOBUF)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_TOOL_SERIALIZATION_SQUAREUP_WIRE)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_FEIGN)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_GRPC)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_NETTY)))
    api(platform(GradleDependency.withVersion(GradleDependency.BOM_WEB_REACTOR)))

    constraints {
        rootProject.allprojects
            .filterNot { it.path == ":" }
            .forEach { api(project(it.path)) }

        api(GradleDependency.withVersion(GradleDependency.CACHE_CAFFEINE))
        api(GradleDependency.withVersion(GradleDependency.CACHE_LETTUCE))
        api(GradleDependency.withVersion(GradleDependency.CACHE_REDISSON))

        api(GradleDependency.withVersion(GradleDependency.CLOUD_COMMUNICATION_DROMARA_SMS))
        api(GradleDependency.withVersion(GradleDependency.CLOUD_COMMUNICATION_JAKARTA_MAIL))

        api(GradleDependency.withVersion(GradleDependency.JDBC_DA_MENG))
        api(GradleDependency.withVersion(GradleDependency.JDBC_HSQLDB))
        api(GradleDependency.withVersion(GradleDependency.JDBC_MARIADB))
        api(GradleDependency.withVersion(GradleDependency.JDBC_MYSQL))
        api(GradleDependency.withVersion(GradleDependency.JDBC_POSTGIS))
        api(GradleDependency.withVersion(GradleDependency.JDBC_POSTGRESQL))
        api(GradleDependency.withVersion(GradleDependency.JDBC_TDENGINE))
        api(GradleDependency.withVersion(GradleDependency.JDBC_SQL_SERVER))

        api(GradleDependency.withVersion(GradleDependency.ORM_MYBATIS))
        api(GradleDependency.withVersion(GradleDependency.ORM_MYBATIS_PLUS))
        api(GradleDependency.withVersion(GradleDependency.ORM_MYBATIS_PLUS_DYNAMIC_DATASOURCE))
        api(GradleDependency.withVersion(GradleDependency.ORM_SPRING_INTEGRATION_CASSANDRA))

        api(GradleDependency.withVersion(GradleDependency.OSS_ALL_FACED))
        api(GradleDependency.withVersion(GradleDependency.OSS_MINIO))

        api(GradleDependency.withVersion(GradleDependency.MESSAGE_MQTT_V5_ECLIPSE_PAHO))
        api(GradleDependency.withVersion(GradleDependency.MESSAGE_ROCKETMQ_TODO)) // TODO wjm need to remove

        api(GradleDependency.withVersion(GradleDependency.METRIC_BIT_WALKER_USER_AGENT))
        api(GradleDependency.withVersion(GradleDependency.METRIC_OSHI_CORE))

        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_LOMBOK))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT_PLUS))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MAPSTRUCT_PROTOBUF))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_MICA_AUTO))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_OPENJDK_JMH))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_AUTOCONFIGURE))
        api(GradleDependency.withVersion(GradleDependency.PLUGGABLE_ANNOTATION_PROCESSING_API_PROCESSOR_SPRING_CONFIGURATION))

        api(GradleDependency.withVersion(GradleDependency.POOL_APACHE))
        api(GradleDependency.withVersion(GradleDependency.POOL_DATABASE_HIKARICP))

        api(GradleDependency.withVersion(GradleDependency.PROTOCOL_JAVAX_SIP))

        api(GradleDependency.withVersion(GradleDependency.TOOL_ALIBABA_EASY_EXCEL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_ALIBABA_TRANSMITTABLE_THREAD_LOCAL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_ANNOTATION_API_JAVAX))
        api(GradleDependency.withVersion(GradleDependency.TOOL_AUTOWIRED_SMART_SPRING))
        api(GradleDependency.withVersion(GradleDependency.TOOL_CONTENT_ANALYSIS_APACHE_TIKA_PARSER))
        api(GradleDependency.withVersion(GradleDependency.TOOL_TEMPLATE_ENGINE_APACHE_VELOCITY))
        api(GradleDependency.withVersion(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_JTS))
        api(GradleDependency.withVersion(GradleDependency.TOOL_GEOMETRY_LOCATION_TECH_SPATIAL4J))
        api(GradleDependency.withVersion(GradleDependency.TOOL_GOOGLE_GUAVA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_HUTOOL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_IO_VAVR))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JDK_BURNING_WAVE_CORE))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JNA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JNA_JPMS))
        api(GradleDependency.withVersion(GradleDependency.TOOL_JOB_QUARTZ))
        api(GradleDependency.withVersion(GradleDependency.TOOL_MAPSTRUCT_PLUS))
        api(GradleDependency.withVersion(GradleDependency.TOOL_PASSAY))
        api(GradleDependency.withVersion(GradleDependency.TOOL_REFLECT_ASM))
        api(GradleDependency.withVersion(GradleDependency.TOOL_REFLECT_RONMAMO))
        api(GradleDependency.withVersion(GradleDependency.TOOL_RATE_LIMITING_BUCKET4J))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_FASTJSON))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_FASTJSON2))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_FST))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SERIALIZATION_PROTOBUF_GOOGLE_PROTOC))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SPI_GOOGLE_AUTO_SERVICE))
        api(GradleDependency.withVersion(GradleDependency.TOOL_STREAM_JDFRAME))
        api(GradleDependency.withVersion(GradleDependency.TOOL_SQUAREUP_JAVAPOET))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VALIDATION_HIBERNATE))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VALIDATION_JAKARTA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VALIDATION_JSON_SCHEMA))
        api(GradleDependency.withVersion(GradleDependency.TOOL_VJTOOL))
        api(GradleDependency.withVersion(GradleDependency.TOOL_XML_DOM4J))
        api(GradleDependency.withVersion(GradleDependency.TOOL_XML_JAVAX_JAXB_API))
        api(GradleDependency.withVersion(GradleDependency.TOOL_YITTER_SNOWFLAKE_ID))

        api(GradleDependency.withVersion(GradleDependency.TEST_JUNIT))
        api(GradleDependency.withVersion(GradleDependency.TEST_OPENJDK_JMH_CORE))

        api(GradleDependency.withVersion(GradleDependency.WEB_GRPC_SPRING_BOOT_SERVER))
        api(GradleDependency.withVersion(GradleDependency.WEB_GRPC_SPRING_BOOT_CLIENT))
        api(GradleDependency.withVersion(GradleDependency.WEB_GRPC_PROTO_GEN))
        api(GradleDependency.withVersion(GradleDependency.WEB_OKHTTPS))
    }
}

javaPlatform {
    allowDependencies()
}

mavenPublishing {
    configure(com.vanniktech.maven.publish.JavaPlatform())
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