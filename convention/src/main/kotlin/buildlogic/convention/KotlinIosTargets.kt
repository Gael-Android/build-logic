package buildlogic.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureIosTargets() {
    // 버전 카탈로그에 공통 iOS framework 이름이 있으면 그 값을 우선 사용합니다.
    val frameworkBaseName = versionCatalog.findVersion("projectIosFrameworkBaseName")
        // VersionConstraint를 실제 문자열 이름으로 변환합니다.
        .map { it.toString() }
        // 비어 있는 값은 framework 이름으로 쓰지 않습니다.
        .filter { it.isNotBlank() }
        // 카탈로그 값이 없으면 현재 Gradle module path를 기반으로 framework 이름을 만듭니다.
        .orElse(pathToFrameworkName())

    extensions.configure<KotlinMultiplatformExtension> {
        listOf(
            iosX64(), // Intel Mac에서 실행하는 iOS Simulator용 Kotlin/Native 타깃입니다.
            iosArm64(), // 실제 iPhone/iPad 기기용 Kotlin/Native 타깃입니다.
            iosSimulatorArm64() // Apple Silicon Mac에서 실행하는 iOS Simulator용 타깃입니다.
        ).forEach { iosTarget ->
            // 위에서 선언한 각 iOS 타깃마다 Xcode가 가져갈 .framework 바이너리를 만듭니다.
            iosTarget.binaries.framework {
                // 생성되는 framework의 파일/모듈 이름입니다.
                baseName = frameworkBaseName
                // Swift/iOS 앱에 링크할 정적 framework로 생성합니다.
                isStatic = true
                // Kotlin/Native가 bundle id를 추론하지 않도록 module path 기반 id를 명시합니다.
                binaryOption("bundleId", this@configureIosTargets.pathToPackageName())
            }
        }
    }
}
