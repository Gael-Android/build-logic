import buildlogic.convention.libs

plugins {
    id("com.yourssu.convention.cmp.library")
}

dependencies {
    "commonMainImplementation"(platform(libs.findLibrary("koin-bom").get()))
    "androidMainImplementation"(platform(libs.findLibrary("koin-bom").get()))

    "commonMainImplementation"(libs.findLibrary("koin-compose").get())
    "commonMainImplementation"(libs.findLibrary("koin-compose-viewmodel").get())

    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-runtime").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-viewmodel").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-lifecycle-viewmodel").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-lifecycle-compose").get())

    "commonMainImplementation"(libs.findLibrary("jetbrains-lifecycle-viewmodel-savedstate").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-savedstate").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-bundle").get())
    "commonMainImplementation"(libs.findLibrary("jetbrains-compose-navigation").get())

    "androidMainImplementation"(libs.findLibrary("koin-android").get())
    "androidMainImplementation"(libs.findLibrary("koin-androidx-compose").get())
    "androidMainImplementation"(libs.findLibrary("koin-androidx-navigation").get())
    "androidMainImplementation"(libs.findLibrary("koin-core-viewmodel").get())
}
