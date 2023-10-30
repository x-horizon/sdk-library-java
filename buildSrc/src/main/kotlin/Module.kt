// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

import java.io.File
import java.util.concurrent.ConcurrentHashMap

object GradleModule {

    const val BOM = "bom"

    const val CACHE_ALL = "cache-all"
    const val CACHE_CAFFEINE = "cache-caffeine"
    const val CACHE_CONTRACT = "cache-contract"
    const val CACHE_LETTUCE = "cache-lettuce"
    const val CACHE_MAP = "cache-map"
    const val CACHE_REDIS = "cache-redis"

    const val CLOUD_COMMUNICATION_AMAZON = "cloud-communication-amazon"
    const val CLOUD_COMMUNICATION_JAKARTA = "cloud-communication-jakarta"
    const val CLOUD_COMMUNICATION_TWILIO = "cloud-communication-twilio"
    const val CLOUD_NATIVE_DOCKER = "cloud-native-docker"
    const val CLOUD_NATIVE_KUBERNETES = "cloud-native-kubernetes"

    const val CONCURRENT_ACTOR = "concurrent-actor"
    const val CONCURRENT_REDIS = "concurrent-redis"

    const val CONTRACT_BASE = "contract-base"
    const val CONTRACT_COMPONENT_MESSAGE_SPRING = "contract-component-message-spring"
    const val CONTRACT_COMPONENT_REDIS = "contract-component-redis"
    const val CONTRACT_CONSTANT = "contract-constant"
    const val CONTRACT_MODEL = "contract-model"

    const val DATA_CASSANDRA = "data-cassandra"
    const val DATA_ELASTICSEARCH = "data-elasticsearch"
    const val DATA_HSQLDB = "data-hsqldb"
    const val DATA_MYSQL = "data-mysql"
    const val DATA_POSTGRESQL = "data-postgresql"

    const val DOC_KNIFE4J = "doc-knife4j"

    const val GATEWAY_SPRING = "gateway-spring"

    const val MESSAGE_AZURE_SERVICE_BUS = "message-azure-service-bus"
    const val MESSAGE_COAP = "message-coap"
    const val MESSAGE_GOOGLE_PUBSUB = "message-google-pubsub"
    const val MESSAGE_KAFKA = "message-kafka"
    const val MESSAGE_MQTT = "message-mqtt"
    const val MESSAGE_RABBITMQ = "message-rabbitmq"
    const val MESSAGE_REDIS = "message-redis"
    const val MESSAGE_ROCKETMQ = "message-rocketmq"

    const val METRIC_DROP_WIZARD = "metric-drop-wizard"
    const val METRIC_MICROMETER_PROMETHEUS = "metric-micrometer-prometheus"
    const val METRIC_SPRING = "metric-spring"
    const val METRIC_SYSTEM = "metric-system"

    const val ORM_MYBATIS_CONTRACT = "orm-mybatis-contract"
    const val ORM_MYBATIS_FLEX = "orm-mybatis-flex"
    const val ORM_MYBATIS_PLUS = "orm-mybatis-plus"
    const val ORM_SPRING_JDBC = "orm-spring-jdbc"
    const val ORM_SPRING_JPA = "orm-spring-jpa"
    const val ORM_TD_ENGINE_JDBC = "orm-td-engine-jdbc"

    const val OSS_MINIO = "oss-minio"

    const val PLATFORM_WECHAT_ENTERPRISE = "platform-wechat-enterprise"
    const val PLATFORM_WECHAT_MINI_APP = "platform-wechat-mini-app"
    const val PLATFORM_WECHAT_OFFICIAL = "platform-wechat-official"
    const val PLATFORM_WECHAT_OPEN = "platform-wechat-open"

    const val PLUGGABLE_ANNOTATION_API_LOMBOK = "pluggable-annotation-api-lombok"
    const val PLUGGABLE_ANNOTATION_API_MICA_AUTO = "pluggable-annotation-api-mica-auto"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_JMH = "pluggable-annotation-api-processor-jmh"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_LOMBOK_MAPSTRUCT_BINDING = "pluggable-annotation-api-processor-lombok-mapstruct-binding"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_MAPSTRUCT = "pluggable-annotation-api-processor-mapstruct"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_MYBATIS_FLEX = "pluggable-annotation-api-processor-mybatis-flex"
    const val PLUGGABLE_ANNOTATION_API_PROCESSOR_SPRING = "pluggable-annotation-api-processor-spring"

    const val POOL_APACHE = "pool-apache"
    const val POOL_DATABASE_HIKARICP = "pool-database-hikaricp"

    const val PROTOCOL_JAVAX_SIP = "protocol-javax-sip"

    const val REGISTRATION_ZOOKEEPER = "registration-zookeeper"

    const val SCRIPT_JAVA_SCRIPT = "script-java-script"

    const val SECURITY_CONTRACT = "security-contract"
    const val SECURITY_SPRING = "security-spring"
    const val SECURITY_SA_TOKEN = "security-sa-token"

    const val TEST_CASSANDRA_UNIT = "test-cassandra-unit"
    const val TEST_CONTAINER = "test-container"
    const val TEST_DB_UNIT = "test-db-unit"
    const val TEST_DB_UNIT_SPRING = "test-db-unit-spring"
    const val TEST_JMH = "test-jmh"
    const val TEST_JUNIT = "test-junit"
    const val TEST_MOCKITO = "test-mockito"
    const val TEST_SPRING = "test-spring"

    const val TODO = "todo"

    const val TOOL_CONTENT_ANALYSIS = "tool-content-analysis"
    const val TOOL_CONVERT_ALL = "tool-convert-all"
    const val TOOL_CONVERT_JACKSON = "tool-convert-jackson"
    const val TOOL_CONVERT_MAPSTRUCT = "tool-convert-mapstruct"
    const val TOOL_CONVERT_SPRING = "tool-convert-spring"
    const val TOOL_ENUMS = "tool-enums"
    const val TOOL_EXCEL = "tool-excel"
    const val TOOL_EXEC = "tool-exec"
    const val TOOL_FREEMARKER = "tool-freemarker"
    const val TOOL_GEOMETRY = "tool-geometry"
    const val TOOL_ID_SNOWFLAKE = "tool-id-snowflake"
    const val TOOL_JOB_QUARTZ = "tool-job-quartz"
    const val TOOL_LANG = "tool-lang"
    const val TOOL_LOG = "tool-log"
    const val TOOL_RATE_LIMITING = "tool-rate-limiting"
    const val TOOL_SERIALIZATION_FASTJSON = "tool-serialization-fastjson"
    const val TOOL_SERIALIZATION_FASTJSON2 = "tool-serialization-fastjson2"
    const val TOOL_SERIALIZATION_FST = "tool-serialization-fst"
    const val TOOL_SERIALIZATION_GSON = "tool-serialization-gson"
    const val TOOL_SERIALIZATION_JACKSON = "tool-serialization-jackson"
    const val TOOL_SERIALIZATION_PROTOBUF_GOOGLE = "tool-serialization-protobuf-google"
    const val TOOL_SERIALIZATION_PROTOBUF_THINGSBOARD = "tool-serialization-protobuf-thingsboard"
    const val TOOL_SERIALIZATION_PROTOBUF_SQUAREUP = "tool-serialization-protobuf-squareup"
    const val TOOL_SPRING_CONTRACT = "tool-spring-contract"
    const val TOOL_SPRING_WEBFLUX = "tool-spring-webflux"
    const val TOOL_SPRING_WEBMVC = "tool-spring-webmvc"
    const val TOOL_VALIDATION_HIBERNATE = "tool-validation-hibernate"
    const val TOOL_VALIDATION_JAKARTA = "tool-validation-jakarta"
    const val TOOL_VALIDATION_JSON_SCHEMA = "tool-validation-json-schema"
    const val TOOL_VALIDATION_SPRING_BOOT = "tool-validation-spring"
    const val TOOL_XML = "tool-xml"

    const val TRANSACTION_SPRING = "transaction-spring"

    const val WEB_NETTY = "web-netty"
    const val WEB_OPEN_FEIGN = "web-openfeign"
    const val WEB_REACTOR = "web-reactor"
    const val WEB_WEBSOCKET = "web-websocket"

    @JvmStatic
    fun toReferenceName(moduleName: String): String {
        // this function can convert module name like "parent-child1-name-child2-name" to the reference module name like ":parent:child1-name:child2-name"
        return ModuleHelper.toReferenceName(moduleName)
    }

    @JvmStatic
    fun toModuleName(projectName: String): String {
        // the input is project.toString(), for example: "project ':parent:child1:child2'"
        // this function can convert project.toString() to the module name: "parent-child1-child2"
        return projectName.removePrefix("project ':").removeSuffix("'").replace(":", "-")
    }

}

internal object ModuleHelper {

    private const val SYSTEM_PROPERTY_ROOT_PROJECT_ABSOLUTE_PATH = "rootProjectAbsolutePath"

    private val ignoreModuleNames = setOf(
        ".idea",
        ".git",
        ".gradle",
        "gradle",
        "build",
        "target",
        "out",
        "dist",
        "src",
        "config",
        "buildSrc",
    )

    private data class ModuleTreeNode(val name: String, val children: MutableList<ModuleTreeNode> = mutableListOf())

    private val moduleNameMappingReferenceModuleNameMap = ConcurrentHashMap<String, String>().apply {
        // cache the special reference module name that cannot parse
        put(GradleModule.TEST_DB_UNIT_SPRING, ":test:db-unit-spring")
        put(GradleModule.TOOL_SERIALIZATION_FASTJSON2, ":tool:serialization:fastjson2")
    }

    private var rootModuleTreeNode: ModuleTreeNode? = null

    internal fun toReferenceName(moduleName: String): String {
        return moduleNameMappingReferenceModuleNameMap.getOrPut(moduleName) {
            toReferenceName(
                getRootModuleTree().children.firstOrNull { subModuleTreeNode -> moduleName.startsWith(subModuleTreeNode.name) } ?: throw NoSuchElementException("could not find module node by module name [$moduleName], please check!"),
                moduleName,
                StringBuilder()
            )
        }
    }

    private fun toReferenceName(moduleTreeNode: ModuleTreeNode, moduleName: String, referenceModuleName: StringBuilder): String {
        if (moduleName.startsWith(moduleTreeNode.name)) {
            referenceModuleName.append(moduleTreeNode.name).append(":")
            moduleTreeNode.children.forEach { child -> toReferenceName(child, moduleName.removePrefix(moduleTreeNode.name + "-"), referenceModuleName) }
        }
        return ":" + referenceModuleName.toString().removeSuffix(":")
    }

    private fun getRootModuleTree(): ModuleTreeNode {
        if (null == rootModuleTreeNode) {
            val rootPath = System.getProperty(SYSTEM_PROPERTY_ROOT_PROJECT_ABSOLUTE_PATH)
            val moduleTreeNode = ModuleTreeNode(rootPath)
            generateModuleTree(moduleTreeNode, rootPath)
            rootModuleTreeNode = moduleTreeNode
        }
        return rootModuleTreeNode as ModuleTreeNode
    }

    private fun generateModuleTree(parent: ModuleTreeNode, modulePath: String) {
        return File(modulePath).listFiles()
            ?.filter { file -> file.isDirectory }
            ?.filter { directory -> !ignoreModuleNames.contains(directory.name) }
            ?.forEach continuing@{ directory ->
                val moduleNode = ModuleTreeNode(directory.name)
                parent.children.add(moduleNode)
                generateModuleTree(moduleNode, directory.absolutePath)
            }
            ?: return
    }

}

//private val moduleNameMappingReferenceModuleNameMap = ConcurrentHashMap<String, String>().apply {
//    // cache the special reference module name that cannot parse
//    put(GradleModule.CONTRACT_BASE, ":contract:base") // will parse to :contract:constant
//    put(GradleModule.TOOL_ID_SNOWFLAKE, ":tool:id:snowflake") // will parse to :tool:lang
//}

//private fun toReferenceName(moduleTreeNode: ModuleTreeNode, moduleName: String, referenceModuleName: StringBuilder): String {
//    referenceModuleName.append(moduleTreeNode.name).append(":")
//    if (moduleTreeNode.children.isNotEmpty()) {
//        val theMostMatchedModuleName = moduleTreeNode.children
//            .map { child -> child.name }
//            .minByOrNull { childName -> getMostSimilar(moduleName, childName) }
//        moduleTreeNode.children
//            .find { child -> child.name == theMostMatchedModuleName }
//            ?.let { toReferenceName(it, moduleName.removePrefix(moduleTreeNode.name + "-"), referenceModuleName) }
//    }
//    return ":" + referenceModuleName.toString().removeSuffix(":")
//}

//    private fun getMostSimilar(compare: String, compared: String): Int {
//        val compareLength = compare.length
//        val comparedLength = compared.length
//        val dp = Array(compareLength + 1) { IntArray(comparedLength + 1) }
//
//        for (i in 0..compareLength) {
//            dp[i][0] = i
//        }
//
//        for (j in 0..comparedLength) {
//            dp[0][j] = j
//        }
//
//        for (i in 1..compareLength) {
//            for (j in 1..comparedLength) {
//                if (compare[i - 1] == compared[j - 1]) {
//                    dp[i][j] = dp[i - 1][j - 1]
//                } else {
//                    dp[i][j] = 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
//                }
//            }
//        }
//
//        return dp[compareLength][comparedLength]
//    }

