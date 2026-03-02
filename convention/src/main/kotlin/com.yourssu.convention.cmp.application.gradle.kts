import buildlogic.convention.applyHierarchyTemplate
import buildlogic.convention.configureAndroidLibraryTarget
import buildlogic.convention.configureDesktopTarget
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
configureDesktopTarget()

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
    commonMainImplementation(library("compose-runtime"))
    commonMainImplementation(library("compose-foundation"))
    commonMainImplementation(library("compose-material3"))
    commonMainImplementation(library("compose-ui"))

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    commonMainImplementation(library("compose-components-resources"))
    commonMainImplementation(library("compose-ui-tooling-preview"))

    // AGP 9 single-variant model: tooling is on androidMainImplementation.
    "androidMainImplementation"(library("compose-ui-tooling"))

    // Android compose UI testing (only added when Android test configurations exist)
    if (configurations.findByName("androidTestImplementation") != null) {
        val composeTestBom = platform(library("androidx-compose-bom"))
        "androidTestImplementation"(composeTestBom)
        "androidTestImplementation"(library("androidx-compose-ui-test-junit4"))
    }
    if (configurations.findByName("debugImplementation") != null) {
        "debugImplementation"(library("androidx-compose-ui-test-manifest"))
    }
}
