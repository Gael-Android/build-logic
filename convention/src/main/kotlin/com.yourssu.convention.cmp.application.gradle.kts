import buildlogic.convention.applyHierarchyTemplate
import buildlogic.convention.configureAndroidLibraryTarget
import buildlogic.convention.configureIosTargets
import buildlogic.convention.libs
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

configureAndroidLibraryTarget()
configureIosTargets()

extensions.configure<KotlinMultiplatformExtension> {
    applyHierarchyTemplate()
}

dependencies {
    // Core Compose dependencies
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-runtime").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-foundation").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-material3").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-ui").get())

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-resources").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-ui-tooling-preview").get())

    // Single-variant model: use androidMainImplementation instead of debugImplementation
    "androidMainImplementation"(libs.findLibrary("jetbrains-compose-ui-tooling").get())
}
