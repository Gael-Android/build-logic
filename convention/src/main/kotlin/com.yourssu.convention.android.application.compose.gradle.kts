import buildlogic.convention.configureAndroidCompose
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("com.yourssu.convention.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

val extension = extensions.getByType<ApplicationExtension>()
configureAndroidCompose(extension)
