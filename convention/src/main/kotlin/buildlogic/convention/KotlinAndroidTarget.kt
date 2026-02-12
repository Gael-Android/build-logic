package buildlogic.convention

import gradle.kotlin.dsl.accessors._1a0bc088e68ff99eb07b399de993c4b5.coreLibraryDesugaring
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
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
 * Android library settings (namespace, compileSdk, minSdk) must be configured in each module's
 * build.gradle.kts using kotlin { androidLibrary { ... } }
 */
internal fun Project.configureAndroidLibraryTarget() {
    val libs = the<LibrariesForLibs>()

    dependencies {
        coreLibraryDesugaring(libs.android.desugarJdkLibs)
    }
}
