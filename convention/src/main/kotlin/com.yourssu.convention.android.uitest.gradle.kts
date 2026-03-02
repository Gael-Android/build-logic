import buildlogic.convention.library
import com.android.build.api.dsl.ApplicationExtension

extensions.configure<ApplicationExtension> {
    defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

dependencies {
    if (configurations.findByName("androidTestImplementation") != null) {
        val composeTestBom = platform(library("androidx-compose-bom"))
        "androidTestImplementation"(composeTestBom)
        "androidTestImplementation"(library("androidx-compose-ui-test-junit4"))
        "androidTestImplementation"(library("androidx-junit"))
        "androidTestImplementation"(library("junit"))
    }

    if (configurations.findByName("debugImplementation") != null) {
        "debugImplementation"(library("androidx-compose-ui-test-manifest"))
    }
}
