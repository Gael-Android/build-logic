import buildlogic.convention.applyHierarchyTemplate
import buildlogic.convention.configureAndroidLibraryTarget
import buildlogic.convention.configureIosTargets
import buildlogic.convention.library
import buildlogic.convention.pathToPackageName
import buildlogic.convention.versionValue
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

kotlin {
    androidLibrary {
        namespace = pathToPackageName()
        compileSdk = versionValue("projectCompileSdkVersion").toInt()
        minSdk = versionValue("projectMinSdkVersion").toInt()
    }
}

extensions.configure<KotlinMultiplatformExtension> {
    applyHierarchyTemplate()
}

dependencies {
    // Core Compose dependencies
    commonMainImplementation(library("jetbrains-compose-runtime"))
    commonMainImplementation(library("jetbrains-compose-foundation"))
    commonMainImplementation(library("jetbrains-compose-material3"))
    commonMainImplementation(library("jetbrains-compose-ui"))

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    commonMainImplementation(library("jetbrains-compose-resources"))
    commonMainImplementation(library("jetbrains-compose-ui-tooling-preview"))

    // Single-variant model: use androidMainImplementation instead of debugImplementation
    androidMainImplementation(library("jetbrains-compose-ui-tooling"))
}
