import buildlogic.convention.library

plugins {
    id("com.yourssu.convention.kmp.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

dependencies {
    // Core Compose dependencies
    commonMainImplementation(library("compose-ui"))
    commonMainImplementation(library("compose-foundation"))
    commonMainImplementation(library("compose-material-icons-core"))
    commonMainImplementation(library("compose-material-icons-extended"))
    commonMainImplementation(library("compose-material3"))

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    commonMainImplementation(library("compose-components-resources"))
    commonMainImplementation(library("compose-ui-tooling-preview"))

    // AGP 9 + com.android.kotlin.multiplatform.library (androidLibrary DSL) requires
    // tooling on androidRuntimeClasspath for preview support.
    "androidRuntimeClasspath"(library("compose-ui-tooling"))
}
