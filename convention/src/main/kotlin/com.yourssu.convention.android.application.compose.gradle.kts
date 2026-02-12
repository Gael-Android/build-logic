import buildlogic.convention.configureAndroidCompose
import com.android.build.api.dsl.ApplicationExtension

plugins {
    id("com.yourssu.convention.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

val extension = extensions.getByType<ApplicationExtension>()
configureAndroidCompose(extension)
