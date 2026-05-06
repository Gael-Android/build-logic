package buildlogic.convention

import org.gradle.api.GradleException
import org.gradle.api.Project

fun Project.featureSiblingDomainPath(expectedLayer: String): String {
    val segments = path.split(":").filter { it.isNotBlank() }
    if (segments.size != 3 || segments[0] != "feature" || segments[2] != expectedLayer) {
        throw GradleException(
            "Plugin must be applied to ':feature:<name>:$expectedLayer' modules only, but was applied to '$path'.",
        )
    }

    return ":feature:${segments[1]}:domain"
}
