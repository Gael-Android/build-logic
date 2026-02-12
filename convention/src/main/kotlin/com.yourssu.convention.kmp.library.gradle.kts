import buildlogic.convention.configureKotlinMultiplatform
import buildlogic.convention.library

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
}

configureKotlinMultiplatform()

dependencies {
    commonMainImplementation(library("kotlinx-serialization-json"))
    commonTestImplementation(library("kotlin-test"))
}
