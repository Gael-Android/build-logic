import buildlogic.convention.library

plugins {
    id("com.yourssu.convention.kmp.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

dependencies {
    // Core Compose dependencies
    commonMainImplementation(library("jetbrains-compose-ui"))
    commonMainImplementation(library("jetbrains-compose-foundation"))
    commonMainImplementation(library("jetbrains-compose-material3"))
    commonMainImplementation(library("jetbrains-compose-material-icons-core"))

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    commonMainImplementation(library("jetbrains-compose-resources"))
    commonMainImplementation(library("jetbrains-compose-ui-tooling-preview"))

    // AGP 9 + com.android.kotlin.multiplatform.library (androidLibrary DSL) requires
    // tooling on androidRuntimeClasspath for preview support.
    "androidRuntimeClasspath"(library("jetbrains-compose-ui-tooling"))
}
