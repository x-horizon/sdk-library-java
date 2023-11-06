// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://maven.aliyun.com/repository/spring/")
        maven("https://repo.spring.io/milestone/")
        maven("https://repo.spring.io/snapshot/")
        gradlePluginPortal()
    }
}

rootProject.name = "library-java"

System.setProperty("rootProjectAbsolutePath", rootDir.absolutePath)

include(":bom")

include(":cache:all")
include(":cache:caffeine")
include(":cache:contract")
include(":cache:lettuce")
include(":cache:map")
include(":cache:redis")

include(":cloud:communication:amazon")
include(":cloud:communication:dromara-sms")
include(":cloud:communication:jakarta")
include(":cloud:communication:twilio")
include(":cloud:native:docker")
include(":cloud:native:kubernetes")

include(":concurrent:actor")
include(":concurrent:redis")

include(":contract:base")
include(":contract:component:redis")
include(":contract:component:message:spring")
include(":contract:constant")
include(":contract:model")

include(":data:cassandra")
include(":data:elasticsearch")
include(":data:hsqldb")
include(":data:mysql")
include(":data:postgresql")

include(":doc:knife4j")

include(":gateway:spring")

include(":message:azure-service-bus")
include(":message:coap")
include(":message:google-pubsub")
include(":message:kafka")
include(":message:mqtt")
include(":message:rabbitmq")
include(":message:redis")
include(":message:rocketmq")

include(":metric:drop-wizard")
include(":metric:micrometer:prometheus")
include(":metric:spring")
include(":metric:system")

include(":orm:mybatis-contract")
include(":orm:mybatis-contract-postgresql")
include(":orm:mybatis-flex")
include(":orm:mybatis-flex-postgresql")
include(":orm:mybatis-plus")
include(":orm:spring-jdbc")
include(":orm:spring-jpa")
include(":orm:td-engine-jdbc")

include(":oss:minio")

include(":pluggable-annotation-api:lombok")
include(":pluggable-annotation-api:mica-auto")
include(":pluggable-annotation-api:processor:jmh")
include(":pluggable-annotation-api:processor:lombok-mapstruct-binding")
include(":pluggable-annotation-api:processor:mapstruct")
include(":pluggable-annotation-api:processor:mybatis-flex")
include(":pluggable-annotation-api:processor:spring")

include(":pool:apache")
include(":pool:database:hikaricp")

include(":protocol:javax-sip")

include(":registration:zookeeper")

include(":script:java-script")

include(":security:contract")
include(":security:spring")
include(":security:sa-token")

include(":test:cassandra-unit")
include(":test:container")
include(":test:db-unit")
include(":test:db-unit-spring")
include(":test:jmh")
include(":test:junit")
include(":test:mockito")
include(":test:spring")

include(":todo")

include(":tool:content-analysis")
include(":tool:convert:all")
include(":tool:convert:jackson")
include(":tool:convert:mapstruct")
include(":tool:convert:spring")
include(":tool:enums")
include(":tool:excel")
include(":tool:exec")
include(":tool:freemarker")
include(":tool:geometry")
include(":tool:id:snowflake")
include(":tool:job:quartz")
include(":tool:lang")
include(":tool:log")
include(":tool:rate-limiting")
include(":tool:serialization:fastjson")
include(":tool:serialization:fastjson2")
include(":tool:serialization:fst")
include(":tool:serialization:gson")
include(":tool:serialization:jackson")
include(":tool:serialization:protobuf:thingsboard")
include(":tool:serialization:protobuf:google")
include(":tool:serialization:protobuf:squareup")
include(":tool:spring:contract")
include(":tool:spring:webflux")
include(":tool:spring:webmvc")
include(":tool:validation:hibernate")
include(":tool:validation:jakarta")
include(":tool:validation:json-schema")
include(":tool:validation:spring")
include(":tool:xml")

include(":transaction:spring")

include(":web:netty")
include(":web:openfeign")
include(":web:reactor")
include(":web:websocket")
