import androidx.room.gradle.RoomExtension
import buildlogic.convention.libs

plugins {
    id("com.google.devtools.ksp")
    id("androidx.room")
}

extensions.configure<RoomExtension> {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    "commonMainApi"(libs.findLibrary("androidx-room-runtime").get())
    "commonMainApi"(libs.findLibrary("sqlite-bundled").get())
    "kspAndroid"(libs.findLibrary("androidx-room-compiler").get())
    "kspIosSimulatorArm64"(libs.findLibrary("androidx-room-compiler").get())
    "kspIosArm64"(libs.findLibrary("androidx-room-compiler").get())
    "kspIosX64"(libs.findLibrary("androidx-room-compiler").get())
}
