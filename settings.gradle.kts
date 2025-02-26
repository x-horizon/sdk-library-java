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
rootDir.walkTopDown()
    .filter {
        it.isDirectory &&
                it.name != "buildSrc" &&
                (it.resolve("build.gradle.kts").exists() || it.resolve("build.gradle").exists())
    }
    .forEach { moduleDir ->
        val relativePath = moduleDir.relativeTo(rootDir).path
        val modulePath = relativePath.replace(File.separator, "-")
        include(":$modulePath")
        project(":$modulePath").projectDir = moduleDir
    }