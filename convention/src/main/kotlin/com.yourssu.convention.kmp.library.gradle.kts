import buildlogic.convention.configureKotlinMultiplatform
import buildlogic.convention.libs

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
}

configureKotlinMultiplatform()

dependencies {
    "commonMainImplementation"(libs.findLibrary("kotlinx-serialization-json").get())
    "commonTestImplementation"(libs.findLibrary("kotlin-test").get())
}
