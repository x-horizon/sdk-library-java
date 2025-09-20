plugins {
    kotlin("jvm").version("2.2.20")
}

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://maven.aliyun.com/repository/spring/")
    maven("https://repo.spring.io/milestone/")
    maven("https://repo.spring.io/snapshot/")
    mavenCentral()
}