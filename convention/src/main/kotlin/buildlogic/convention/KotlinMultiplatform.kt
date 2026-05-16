package buildlogic.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Configures Kotlin Multiplatform for library modules.
 * Android library settings (namespace, compileSdk, minSdk) are auto-configured
 * in precompiled script plugins (kmp.library, cmp.application).
 * Modules can override in build.gradle.kts using kotlin { androidLibrary { ... } } if needed.
 */
internal fun Project.configureKotlinMultiplatform() {
    // KMP library module에 Android target과 Android 공통 의존성을 붙입니다.
    configureAndroidLibraryTarget()

    extensions.configure<KotlinMultiplatformExtension> {
        listOf(
            iosX64(), // Intel Mac에서 실행하는 iOS Simulator용 Kotlin/Native 타깃입니다.
            iosArm64(), // 실제 iPhone/iPad 기기용 Kotlin/Native 타깃입니다.
            iosSimulatorArm64() // Apple Silicon Mac에서 실행하는 iOS Simulator용 타깃입니다.
        ).forEach { iosTarget ->
            // 위에서 선언한 각 iOS 타깃마다 Xcode가 가져갈 .framework 바이너리를 만듭니다.
            iosTarget.binaries.framework {
                // 현재 Gradle module path를 기반으로 framework 이름을 만듭니다.
                baseName = this@configureKotlinMultiplatform.pathToFrameworkName()
                // Kotlin/Native가 bundle id를 추론하지 않도록 module path 기반 id를 명시합니다.
                binaryOption("bundleId", this@configureKotlinMultiplatform.pathToPackageName())
            }
        }

        // commonMain, iosMain, iosArm64Main 같은 계층형 source set 관계를 기본 템플릿으로 구성합니다.
        applyHierarchyTemplate()

        compilerOptions {
            // expect/actual class 사용 경고를 이 프로젝트의 공통 옵션으로 허용합니다.
            freeCompilerArgs.add("-Xexpect-actual-classes")
            // RequiresOptIn API 사용을 모듈마다 반복 선언하지 않도록 공통 opt-in으로 둡니다.
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            // kotlin.time의 experimental API 사용을 공통 opt-in으로 둡니다.
            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
        }
    }
}
