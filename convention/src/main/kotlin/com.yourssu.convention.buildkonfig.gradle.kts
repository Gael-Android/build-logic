import buildlogic.convention.pathToPackageName
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

plugins {
    id("com.codingfeline.buildkonfig")
}

extensions.configure<BuildKonfigExtension> {
    packageName = project.pathToPackageName()
    defaultConfigs {
        val apiKey = gradleLocalProperties(rootDir, rootProject.providers)
            .getProperty("API_KEY")
            ?: throw IllegalStateException(
                "Missing API_KEY property in local.properties"
            )
        buildConfigField(FieldSpec.Type.STRING, "API_KEY", apiKey)
    }
}
