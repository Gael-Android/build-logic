import androidx.room.gradle.RoomExtension
import buildlogic.convention.library

plugins {
    id("com.google.devtools.ksp")
    id("androidx.room")
}

extensions.configure<RoomExtension> {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    "commonMainApi"(library("androidx-room-runtime"))
    "commonMainApi"(library("sqlite-bundled"))
    "kspAndroid"(library("androidx-room-compiler"))
    "kspIosSimulatorArm64"(library("androidx-room-compiler"))
    "kspIosArm64"(library("androidx-room-compiler"))
    "kspIosX64"(library("androidx-room-compiler"))
}
