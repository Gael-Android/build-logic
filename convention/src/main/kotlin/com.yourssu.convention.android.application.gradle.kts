import buildlogic.convention.configureKotlinAndroid
import buildlogic.convention.versionValue
import com.android.build.api.dsl.ApplicationExtension

plugins {
    id("com.android.application")
}

extensions.configure<ApplicationExtension> {
    namespace = versionValue("projectApplicationNamespace")

    defaultConfig {
        applicationId = versionValue("projectApplicationId")
        targetSdk = versionValue("projectTargetSdkVersion").toInt()
        versionCode = versionValue("projectVersionCode").toInt()
        versionName = versionValue("projectVersionName")
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
