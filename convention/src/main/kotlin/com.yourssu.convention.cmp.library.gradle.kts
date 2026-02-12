import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

val libs = the<LibrariesForLibs>()

plugins {
    id("com.yourssu.convention.kmp.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

dependencies {
    // Core Compose dependencies
    commonMainImplementation(libs.jetbrains.compose.ui)
    commonMainImplementation(libs.jetbrains.compose.foundation)
    commonMainImplementation(libs.jetbrains.compose.material3)
    commonMainImplementation(libs.jetbrains.compose.material.icons.core)

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    commonMainImplementation(libs.jetbrains.compose.resources)
    commonMainImplementation(libs.jetbrains.compose.ui.tooling.preview)

    // Single-variant model: use androidMainImplementation instead of debugImplementation
    androidMainImplementation(libs.jetbrains.compose.ui.tooling)
}
