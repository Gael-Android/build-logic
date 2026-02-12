import buildlogic.convention.configureKotlinMultiplatform
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

val libs = the<LibrariesForLibs>()

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
}

configureKotlinMultiplatform()

dependencies {
    commonMainImplementation(libs.kotlinx.serialization.json)
    commonTestImplementation(libs.kotlin.test)
}
