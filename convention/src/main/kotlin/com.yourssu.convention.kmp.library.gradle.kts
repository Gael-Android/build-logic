import buildlogic.convention.configureKotlinMultiplatform
import buildlogic.convention.library
import buildlogic.convention.pathToPackageName
import buildlogic.convention.versionValue

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
}

configureKotlinMultiplatform()

kotlin {
    androidLibrary {
        namespace = pathToPackageName()
        compileSdk = versionValue("projectCompileSdkVersion").toInt()
        minSdk = versionValue("projectMinSdkVersion").toInt()
    }
}

dependencies {
    commonMainImplementation(library("kotlinx-serialization-json"))
    commonTestImplementation(library("kotlin-test"))
    commonTestImplementation(library("kotlinx-coroutines-test"))
}
