package buildlogic.convention

import org.gradle.api.Project
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
 * Android library settings (namespace, compileSdk, minSdk) are auto-configured in the precompiled
 * script plugins (kmp.library, cmp.application). Modules can override in build.gradle.kts
 * using kotlin { androidLibrary { ... } } if needed.
 */
internal fun Project.configureAndroidLibraryTarget() {
    dependencies {
        "coreLibraryDesugaring"(library("android-desugarJdkLibs"))
    }
}
