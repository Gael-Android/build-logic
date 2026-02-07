import buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CmpFeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.yourssu.convention.cmp.library")
            }

            dependencies {
                // Optional core module dependencies (configurable via Version Catalog)
                // Use "none" as placeholder in Gradle 9+ (empty strings not allowed)
                libs.findVersion("featureCorePresentationModule")
                    .map { it.toString() }
                    .filter { it.isNotBlank() && it != "none" && it.startsWith(":") }
                    .ifPresent { "commonMainImplementation"(project(it)) }

                libs.findVersion("featureCoreDesignSystemModule")
                    .map { it.toString() }
                    .filter { it.isNotBlank() && it != "none" && it.startsWith(":") }
                    .ifPresent { "commonMainImplementation"(project(it)) }

                "commonMainImplementation"(platform(libs.findLibrary("koin-bom").get()))
                "androidMainImplementation"(platform(libs.findLibrary("koin-bom").get()))

                "commonMainImplementation"(libs.findLibrary("koin-compose").get())
                "commonMainImplementation"(libs.findLibrary("koin-compose-viewmodel").get())

                "commonMainImplementation"(libs.findLibrary("jetbrains-compose-runtime").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-compose-viewmodel").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-lifecycle-viewmodel").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-lifecycle-compose").get())

                "commonMainImplementation"(libs.findLibrary("jetbrains-lifecycle-viewmodel-savedstate").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-savedstate").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-bundle").get())
                "commonMainImplementation"(libs.findLibrary("jetbrains-compose-navigation").get())

                "androidMainImplementation"(libs.findLibrary("koin-android").get())
                "androidMainImplementation"(libs.findLibrary("koin-androidx-compose").get())
                "androidMainImplementation"(libs.findLibrary("koin-androidx-navigation").get())
                "androidMainImplementation"(libs.findLibrary("koin-core-viewmodel").get())
            }
        }
    }
}
