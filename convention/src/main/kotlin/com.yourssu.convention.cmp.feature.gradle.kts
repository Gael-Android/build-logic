import buildlogic.convention.library

plugins {
    id("com.yourssu.convention.cmp.library")
}

dependencies {
    commonMainImplementation(platform(library("koin-bom")))
    androidMainImplementation(platform(library("koin-bom")))

    commonMainImplementation(library("koin-compose"))
    commonMainImplementation(library("koin-compose-viewmodel"))

    commonMainImplementation(library("compose-runtime"))
    commonMainImplementation(library("androidx-lifecycle-viewmodel-compose"))
    commonMainImplementation(library("jetbrains-lifecycle-viewmodel"))
    commonMainImplementation(library("androidx-lifecycle-runtime-compose"))
    commonMainImplementation(library("androidx-lifecycle-viewmodel-navigation3"))

    commonMainImplementation(library("jetbrains-lifecycle-viewmodel-savedstate"))
    commonMainImplementation(library("jetbrains-savedstate"))
    commonMainImplementation(library("jetbrains-bundle"))
    commonMainImplementation(library("androidx-navigation3-ui"))

    androidMainImplementation(library("koin-android"))
    androidMainImplementation(library("koin-androidx-compose"))
    androidMainImplementation(library("koin-androidx-navigation"))
    androidMainImplementation(library("koin-core-viewmodel"))
}
