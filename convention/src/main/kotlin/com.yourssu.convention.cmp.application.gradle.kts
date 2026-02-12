import buildlogic.convention.applyHierarchyTemplate
import buildlogic.convention.configureAndroidLibraryTarget
import buildlogic.convention.configureIosTargets
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

val libs = the<LibrariesForLibs>()

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
    commonMainImplementation(libs.jetbrains.compose.runtime)
    commonMainImplementation(libs.jetbrains.compose.foundation)
    commonMainImplementation(libs.jetbrains.compose.material3)
    commonMainImplementation(libs.jetbrains.compose.ui)

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    commonMainImplementation(libs.jetbrains.compose.resources)
    commonMainImplementation(libs.jetbrains.compose.ui.tooling.preview)

    // Single-variant model: use androidMainImplementation instead of debugImplementation
    androidMainImplementation(libs.jetbrains.compose.ui.tooling)
}
