import com.android.build.api.dsl.ApplicationExtension
import buildlogic.convention.configureKotlinAndroid
import buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                // AGP 9.0+ has built-in Kotlin support, no need for kotlin.android plugin
            }

            extensions.configure<ApplicationExtension> {
                namespace = libs.findVersion("projectApplicationNamespace")
                    .orElseThrow {
                        IllegalStateException(
                            "Missing 'projectApplicationNamespace' in libs.versions.toml. " +
                            "Add: projectApplicationNamespace = \"com.yourcompany.yourapp\""
                        )
                    }
                    .toString()

                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }

                configureKotlinAndroid(this)
            }
        }
    }
}
