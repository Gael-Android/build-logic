package buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import gradle.kotlin.dsl.accessors._1a0bc088e68ff99eb07b399de993c4b5.debugImplementation
import gradle.kotlin.dsl.accessors._1a0bc088e68ff99eb07b399de993c4b5.implementation
import gradle.kotlin.dsl.accessors._1a0bc088e68ff99eb07b399de993c4b5.testImplementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
internal fun Project.configureAndroidCompose(
    extension: ApplicationExtension
) {
    val libs = the<LibrariesForLibs>()

    with(extension) {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.androidx.compose.bom
            implementation(platform(bom))
            testImplementation(platform(bom))
            debugImplementation(libs.androidx.compose.ui.tooling.preview)
            debugImplementation(libs.androidx.compose.ui.tooling)
        }
    }
}
