import buildlogic.convention.configureKotlinAndroid
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

val libs = the<LibrariesForLibs>()

plugins {
    id("com.android.application")
}

extensions.configure<ApplicationExtension> {
    namespace = libs.versions.projectApplicationNamespace.get()

    defaultConfig {
        applicationId = libs.versions.projectApplicationId.get()
        targetSdk = libs.versions.projectTargetSdkVersion.get().toInt()
        versionCode = libs.versions.projectVersionCode.get().toInt()
        versionName = libs.versions.projectVersionName.get()
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
