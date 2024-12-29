plugins {
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://maven.aliyun.com/repository/spring/")
    maven("https://repo.spring.io/milestone/")
    maven("https://repo.spring.io/snapshot/")
    mavenCentral()
}