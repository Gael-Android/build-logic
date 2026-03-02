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
    commonMainImplementation(library("compose-material3"))
    commonMainImplementation(library("jetbrains-compose-material-icons-core"))

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    commonMainImplementation(library("compose-components-resources"))
    commonMainImplementation(library("compose-ui-tooling-preview"))

    // AGP 9 single-variant model: tooling is on androidMainImplementation.
    "androidMainImplementation"(library("compose-ui-tooling"))
}
