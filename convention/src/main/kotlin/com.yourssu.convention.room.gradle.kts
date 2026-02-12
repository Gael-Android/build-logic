import androidx.room.gradle.RoomExtension
import gradle.kotlin.dsl.accessors._20eea9be2489819579bbf099e38996c7.commonMainApi
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

val libs = the<LibrariesForLibs>()

plugins {
    id("com.google.devtools.ksp")
    id("androidx.room")
}

extensions.configure<RoomExtension> {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    commonMainApi(libs.androidx.room.runtime)
    commonMainApi(libs.sqlite.bundled)
    "kspAndroid"(libs.androidx.room.compiler)
    "kspIosSimulatorArm64"(libs.androidx.room.compiler)
    "kspIosArm64"(libs.androidx.room.compiler)
    "kspIosX64"(libs.androidx.room.compiler)
}
