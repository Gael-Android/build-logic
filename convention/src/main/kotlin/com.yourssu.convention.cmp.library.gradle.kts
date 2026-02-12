import buildlogic.convention.libs

plugins {
    id("com.yourssu.convention.kmp.library")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

dependencies {
    // Core Compose dependencies
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-ui").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-foundation").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-material3").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-material-icons-core").get())

    // CMP 1.10.0+: Resources and preview tooling are now separate modules
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-resources").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-ui-tooling-preview").get())

    // Single-variant model: use androidMainImplementation instead of debugImplementation
    "androidMainImplementation"(libs.findLibrary("jetbrains-compose-ui-tooling").get())
}
