package buildlogic.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureIosTargets() {
    val frameworkBaseName = libs.findVersion("projectIosFrameworkBaseName")
        .map { it.toString() }
        .filter { it.isNotBlank() }
        .orElse(pathToFrameworkName())

    extensions.configure<KotlinMultiplatformExtension> {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = frameworkBaseName
                isStatic = true
            }
        }
    }
}
