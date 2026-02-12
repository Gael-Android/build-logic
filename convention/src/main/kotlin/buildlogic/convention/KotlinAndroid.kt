package buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import gradle.kotlin.dsl.accessors._1a0bc088e68ff99eb07b399de993c4b5.coreLibraryDesugaring
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    extension: ApplicationExtension
) {
    val libs = the<LibrariesForLibs>()

    with(extension) {
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()

        defaultConfig.minSdk = libs.versions.projectMinSdkVersion.get().toInt()

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }

        configureKotlin()

        dependencies {
            coreLibraryDesugaring(libs.android.desugarJdkLibs)
        }
    }
}

internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)

            freeCompilerArgs.add(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            )
        }
    }
}
