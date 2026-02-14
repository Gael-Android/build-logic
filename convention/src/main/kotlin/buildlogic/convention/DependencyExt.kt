package buildlogic.convention

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.commonMainApi(dependencyNotation: Any): Dependency? =
    add("commonMainApi", dependencyNotation)

fun DependencyHandler.kspAndroid(dependencyNotation: Any): Dependency? =
    add("kspAndroid", dependencyNotation)

fun DependencyHandler.kspIosArm64(dependencyNotation: Any): Dependency? =
    add("kspIosArm64", dependencyNotation)

fun DependencyHandler.kspIosSimulatorArm64(dependencyNotation: Any): Dependency? =
    add("kspIosSimulatorArm64", dependencyNotation)

fun DependencyHandler.kspIosX64(dependencyNotation: Any): Dependency? =
    add("kspIosX64", dependencyNotation)
