package buildlogic.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * APPLICATION 모듈의 Android 타깃을 구성합니다.
 *
 * 기존에는 `com.android.application` 조합에서 `androidTarget {}` DSL을 사용했지만,
 * AGP 9.0+ 환경에서 deprecation 경고가 발생하므로 현재는 해당 설정을 비활성화했습니다.
 * Android 타깃 구성은 `com.android.kotlin.multiplatform.library` + `androidLibrary {}` 경로로 통일합니다.
 */
//internal fun Project.configureAndroidTarget() {
    // AGP 9.0+에서 `androidTarget {}` DSL이 deprecated되어 build-logic 빌드 시 경고가 발생합니다.
    // 경고 원인 제거와 설정 경로 일원화를 위해 기존 블록을 의도적으로 주석 처리합니다.
    /*
    extensions.configure<KotlinMultiplatformExtension> {
        androidTarget {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }
    */
//}

/**
 * LIBRARY 모듈의 Android 관련 의존성을 구성합니다.
 * `com.android.kotlin.multiplatform.library` 플러그인 사용을 기준으로 하며,
 * Android 라이브러리 설정(namespace, compileSdk, minSdk)은 프리컴파일드 스크립트 플러그인
 * (`kmp.library`, `cmp.application`)에서 자동으로 구성됩니다.
 * 필요하면 각 모듈의 `build.gradle.kts`에서 `kotlin { androidLibrary { ... } }`로 덮어쓸 수 있습니다.
 */
internal fun Project.configureAndroidLibraryTarget() {
    dependencies {
        "coreLibraryDesugaring"(library("android-desugarJdkLibs"))
    }
}
