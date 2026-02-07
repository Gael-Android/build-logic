package buildlogic.convention

import org.gradle.api.Project
import java.util.Locale

fun Project.getPackagePrefix(): String {
    return libs.findVersion("projectPackagePrefix")
        .orElseThrow {
            IllegalStateException(
                "Missing 'projectPackagePrefix' in libs.versions.toml. " +
                "Add: projectPackagePrefix = \"com.yourcompany\""
            )
        }
        .toString()
}

fun Project.pathToPackageName(): String {
    val relativePackageName = path
        .replace(':', '.')
        .lowercase()

    return "${getPackagePrefix()}$relativePackageName"
}

fun Project.pathToResourcePrefix(): String {
    return path
        .replace(':', '_')
        .lowercase()
        .drop(1) + "_"
}

fun Project.pathToFrameworkName(): String {
    val parts = this.path.split(":", "-", "_", " ")
    return parts.joinToString("") { part ->
        part.replaceFirstChar {
            it.titlecase(Locale.ROOT)
        }
    }
}
