package buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    extension: ApplicationExtension
) {
    extension.apply {
        buildFeatures { compose = true }
    }

    dependencies {
        val bomPlatform = platform(library("androidx-compose-bom"))
        "implementation"(bomPlatform)
        "testImplementation"(bomPlatform)
        "debugImplementation"(library("androidx-compose-ui-tooling-preview"))
        "debugImplementation"(library("androidx-compose-ui-tooling"))
    }
}
