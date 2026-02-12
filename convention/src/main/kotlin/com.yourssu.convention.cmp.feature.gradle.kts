import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

val libs = the<LibrariesForLibs>()

plugins {
    id("com.yourssu.convention.cmp.library")
}

dependencies {
    commonMainImplementation(platform(libs.koin.bom))
    androidMainImplementation(platform(libs.koin.bom))

    commonMainImplementation(libs.koin.compose)
    commonMainImplementation(libs.koin.compose.viewmodel)

    commonMainImplementation(libs.jetbrains.compose.runtime)
    commonMainImplementation(libs.jetbrains.compose.viewmodel)
    commonMainImplementation(libs.jetbrains.lifecycle.viewmodel)
    commonMainImplementation(libs.jetbrains.lifecycle.compose)

    commonMainImplementation(libs.jetbrains.lifecycle.viewmodel.savedstate)
    commonMainImplementation(libs.jetbrains.savedstate)
    commonMainImplementation(libs.jetbrains.bundle)
    commonMainImplementation(libs.jetbrains.compose.navigation)

    androidMainImplementation(libs.koin.android)
    androidMainImplementation(libs.koin.androidx.compose)
    androidMainImplementation(libs.koin.androidx.navigation)
    androidMainImplementation(libs.koin.core.viewmodel)
}
