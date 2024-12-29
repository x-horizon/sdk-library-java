import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

dependencies {
    api(project(GradleModule.toReferenceName(GradleModule.TOOL_LANG)))

    api(GradleDependency.JDBC_TDENGINE.withoutVersion) { exclude(group = "commons-logging", module = "commons-logging") }
}