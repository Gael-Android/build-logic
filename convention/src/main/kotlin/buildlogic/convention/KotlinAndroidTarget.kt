package buildlogic.convention

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Configures Android target for APPLICATION modules using com.android.application plugin.
 * Uses the traditional androidTarget {} DSL.
 */
internal fun Project.configureAndroidTarget() {
    extensions.configure<KotlinMultiplatformExtension> {
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }
}

/**
 * Configures Android target for LIBRARY modules using com.android.kotlin.multiplatform.library plugin.
 * Android library settings (namespace, compileSdk, minSdk) are auto-configured from build-logic.
 * Modules can override values in build.gradle.kts using kotlin { android { ... } } if needed.
 */
internal fun Project.configureAndroidLibraryTarget() {
    val compileSdkVersion = libs.findVersion("projectCompileSdkVersion")
        .get()
        .toString()
        .toInt()

    val minSdkVersion = libs.findVersion("projectMinSdkVersion")
        .get()
        .toString()
        .toInt()

    extensions.configure<KotlinMultiplatformExtension> {
        val androidDsl = (this as ExtensionAware).extensions.run {
            (findByName("android") ?: findByName("androidLibrary"))
                as? KotlinMultiplatformAndroidLibraryExtension
        } ?: throw IllegalStateException(
            "KMP Android DSL extension was not found. " +
                "Ensure 'com.android.kotlin.multiplatform.library' is applied."
        )

        androidDsl.namespace = pathToPackageName()
        androidDsl.compileSdk = compileSdkVersion
        androidDsl.minSdk = minSdkVersion
    }

    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("android-desugarJdkLibs").get())
    }
}
