package buildlogic.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.library(alias: String) = versionCatalog.findLibrary(alias).get()

fun Project.versionValue(alias: String) = versionCatalog.findVersion(alias).get().requiredVersion
