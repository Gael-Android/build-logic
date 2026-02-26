import buildlogic.convention.library

plugins {
    id("com.yourssu.convention.cmp.library")
}

dependencies {
    commonMainImplementation(platform(library("koin-bom")))
    androidMainImplementation(platform(library("koin-bom")))

    commonMainImplementation(library("koin-compose"))
    commonMainImplementation(library("koin-compose-viewmodel"))

    commonMainImplementation(library("jetbrains-compose-runtime"))
    commonMainImplementation(library("jetbrains-compose-viewmodel"))
    commonMainImplementation(library("jetbrains-lifecycle-viewmodel"))
    commonMainImplementation(library("jetbrains-lifecycle-compose"))
    commonMainImplementation(library("jetbrains-lifecycle-viewmodel-nav3"))

    commonMainImplementation(library("jetbrains-lifecycle-viewmodel-savedstate"))
    commonMainImplementation(library("jetbrains-savedstate"))
    commonMainImplementation(library("jetbrains-bundle"))
    commonMainImplementation(library("jetbrains-navigation3-ui"))

    androidMainImplementation(library("koin-android"))
    androidMainImplementation(library("koin-androidx-compose"))
    androidMainImplementation(library("koin-androidx-navigation"))
    androidMainImplementation(library("koin-core-viewmodel"))

    // Android compose UI testing
    val composeTestBom = platform(library("androidx-compose-bom"))
    "androidTestImplementation"(composeTestBom)
    "androidTestImplementation"(library("androidx-compose-ui-test-junit4"))
    "debugImplementation"(library("androidx-compose-ui-test-manifest"))
}
