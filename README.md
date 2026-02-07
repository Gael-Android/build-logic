# Build Logic Convention Plugins

Kotlin Multiplatform (KMP) 및 Compose Multiplatform (CMP) 프로젝트를 위한 재사용 가능한 Gradle Convention Plugin 모음입니다.

## 개요

이 build-logic은 여러 KMP/CMP 프로젝트에서 공통으로 사용하는 빌드 설정을 추상화하여, 새 프로젝트를 빠르게 설정하고 일관된 빌드 구성을 유지할 수 있게 해줍니다.

### 주요 특징

- **프로젝트 독립적**: 하드코딩된 값 없이 Version Catalog에서 설정을 읽어옴
- **Git Submodule 지원**: 여러 프로젝트에서 동일한 빌드 로직 공유 가능
- **AGP 9.0 호환**: 최신 Android Gradle Plugin과 호환
- **멀티플랫폼 지원**: Android, iOS, Desktop 타겟 자동 구성

---

## 사용 방법

### 1. Git Submodule로 추가

```bash
git submodule add https://github.com/Gael-Android/build-logic.git build-logic
```

기존 프로젝트를 clone할 때:
```bash
git clone --recursive https://github.com/your-org/your-project.git
# 또는
git clone https://github.com/your-org/your-project.git
git submodule update --init --recursive
```

### 2. settings.gradle.kts 설정

```kotlin
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "YourProjectName"
include(":composeApp")
include(":androidApp")
// ... 기타 모듈
```

### 3. Version Catalog 설정

템플릿 복사:
```bash
cp build-logic/libs.versions.toml.template gradle/libs.versions.toml
```

`gradle/libs.versions.toml`에서 프로젝트에 맞게 값 수정:

```toml
[versions]
# ===== 필수 설정 =====
# 패키지 접두사 (BuildKonfig, namespace 생성에 사용)
projectPackagePrefix = "com.yourcompany"

# Android Application namespace
projectApplicationNamespace = "com.yourcompany.yourapp"

# Android Application ID (Play Store 식별자)
projectApplicationId = "com.yourcompany.yourapp"

# 앱 버전 정보
projectVersionName = "1.0.0"
projectVersionCode = "1"

# Android SDK 버전
projectMinSdkVersion = "26"
projectTargetSdkVersion = "35"
projectCompileSdkVersion = "35"

# ===== 선택 설정 =====
# iOS Framework 이름 (미설정 시 모듈 경로에서 자동 생성)
projectIosFrameworkBaseName = "ComposeApp"

# Feature 모듈의 core 의존성 (빈 문자열이면 추가 안함)
featureCorePresentationModule = ":core:presentation"
featureCoreDesignSystemModule = ":core:designsystem"
```

---

## 제공하는 플러그인

### Android 플러그인

| Plugin ID | 용도 |
|-----------|------|
| `com.yourssu.convention.android.application` | Android Application 기본 설정 |
| `com.yourssu.convention.android.application.compose` | Android Application + Jetpack Compose |

### KMP/CMP 플러그인

| Plugin ID | 용도 |
|-----------|------|
| `com.yourssu.convention.kmp.library` | KMP 라이브러리 모듈 (Android + iOS + Desktop) |
| `com.yourssu.convention.cmp.library` | CMP 라이브러리 모듈 (KMP + Compose Multiplatform) |
| `com.yourssu.convention.cmp.application` | CMP 앱 모듈 (메인 Compose 앱) |
| `com.yourssu.convention.cmp.feature` | CMP Feature 모듈 (ViewModel, Navigation 등 포함) |

### 유틸리티 플러그인

| Plugin ID | 용도 |
|-----------|------|
| `com.yourssu.convention.buildkonfig` | BuildKonfig 설정 (API 키 등 빌드 시점 상수) |
| `com.yourssu.convention.room` | Room Database 설정 (KSP 포함) |

---

## 플러그인 상세 설명

### `convention.kmp.library`

KMP 라이브러리의 기본 설정을 제공합니다.

**자동으로 적용되는 플러그인:**
- `com.android.kotlin.multiplatform.library`
- `org.jetbrains.kotlin.multiplatform`
- `org.jetbrains.kotlin.plugin.serialization`

**자동으로 추가되는 의존성:**
- `kotlinx-serialization-json`
- `kotlin-test` (테스트)

**사용 예시:**
```kotlin
// core/domain/build.gradle.kts
plugins {
    alias(libs.plugins.convention.kmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.yourcompany.core.domain"
    }
}
```

### `convention.cmp.library`

Compose Multiplatform 라이브러리 설정을 제공합니다.

**자동으로 적용되는 플러그인:**
- `convention.kmp.library` (위의 모든 설정 포함)
- `org.jetbrains.kotlin.plugin.compose`
- `org.jetbrains.compose`

**자동으로 추가되는 의존성:**
- Compose UI, Foundation, Material3
- Compose Resources
- UI Tooling Preview

**사용 예시:**
```kotlin
// core/designsystem/build.gradle.kts
plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {
    androidLibrary {
        namespace = "com.yourcompany.core.designsystem"
    }
}
```

### `convention.cmp.feature`

Feature 모듈을 위한 설정을 제공합니다. ViewModel, Navigation, Koin 등 Feature 개발에 필요한 의존성이 포함됩니다.

**자동으로 적용되는 플러그인:**
- `convention.cmp.library` (위의 모든 설정 포함)

**자동으로 추가되는 의존성:**
- `:core:presentation`, `:core:designsystem` (Version Catalog에서 설정한 경우)
- Koin (DI)
- Lifecycle ViewModel
- Navigation Compose
- SavedState

**사용 예시:**
```kotlin
// feature/auth/presentation/build.gradle.kts
plugins {
    alias(libs.plugins.convention.cmp.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.yourcompany.feature.auth.presentation"
    }
}
```

### `convention.cmp.application`

메인 Compose Multiplatform 앱 모듈 설정입니다.

**자동으로 구성되는 타겟:**
- Android (com.android.kotlin.multiplatform.library)
- iOS (iosX64, iosArm64, iosSimulatorArm64)
- Desktop (JVM)

**사용 예시:**
```kotlin
// composeApp/build.gradle.kts
plugins {
    alias(libs.plugins.convention.cmp.application)
}

kotlin {
    androidLibrary {
        namespace = "com.yourcompany.app"
    }
}
```

### `convention.room`

Room Database 설정을 제공합니다.

**자동으로 적용되는 플러그인:**
- `com.google.devtools.ksp`
- `androidx.room`

**자동으로 추가되는 의존성:**
- Room Runtime
- SQLite Bundled
- Room Compiler (KSP - 모든 타겟)

**사용 예시:**
```kotlin
// feature/chat/database/build.gradle.kts
plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.room)
}

kotlin {
    androidLibrary {
        namespace = "com.yourcompany.feature.chat.database"
    }
}
```

### `convention.buildkonfig`

BuildKonfig 설정을 제공합니다. `local.properties`의 API 키를 코드에서 사용할 수 있게 해줍니다.

**사용 전 설정:**
```properties
# local.properties
API_KEY=your_api_key_here
```

**사용 예시:**
```kotlin
// core/data/build.gradle.kts
plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.buildkonfig)
}
```

```kotlin
// 코드에서 사용
import com.yourcompany.core.data.BuildKonfig

val apiKey = BuildKonfig.API_KEY
```

---

## 프로젝트 구조 예시

```
your-project/
├── build-logic/              # Git Submodule
├── gradle/
│   └── libs.versions.toml    # Version Catalog (필수 설정 포함)
├── composeApp/               # CMP Application 모듈
├── androidApp/               # Android 진입점 (MainActivity)
├── iosApp/                   # iOS 진입점 (Xcode 프로젝트)
├── core/
│   ├── domain/               # KMP Library
│   ├── data/                 # KMP Library + BuildKonfig
│   ├── presentation/         # CMP Library
│   └── designsystem/         # CMP Library
├── feature/
│   └── auth/
│       ├── domain/           # KMP Library
│       ├── data/             # KMP Library
│       └── presentation/     # CMP Feature
├── settings.gradle.kts
└── build.gradle.kts
```

---

## Submodule 업데이트

build-logic에 변경사항이 있을 때:

```bash
# 최신 버전으로 업데이트
git submodule update --remote build-logic

# 변경사항 커밋
git add build-logic
git commit -m "Update build-logic submodule"
```

---

## 문제 해결

### "Missing 'projectPackagePrefix' in libs.versions.toml" 오류

`gradle/libs.versions.toml`에 필수 설정이 누락되었습니다. 위의 "Version Catalog 설정" 섹션을 참고하세요.

### Submodule 폴더가 비어있음

```bash
git submodule update --init --recursive
```

### Plugin ID를 찾을 수 없음

`settings.gradle.kts`에 `includeBuild("build-logic")`이 있는지 확인하세요.

---

## 라이선스

MIT License
