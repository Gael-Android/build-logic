# Build Logic Convention Plugins

Reusable Gradle convention plugins for Kotlin Multiplatform (KMP) and Compose Multiplatform (CMP) projects.

## Usage

### 1. Add as Git Submodule

```bash
git submodule add https://github.com/Gael-Android/build-logic.git build-logic
```

### 2. Configure settings.gradle.kts

```kotlin
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
```

### 3. Copy Version Catalog Template

```bash
cp build-logic/libs.versions.toml.template gradle/libs.versions.toml
```

Then customize the values for your project.

### 4. Required Version Catalog Settings

Add these to your `gradle/libs.versions.toml`:

```toml
[versions]
# === Required: Build-logic settings ===
projectPackagePrefix = "com.yourcompany"
projectApplicationNamespace = "com.yourcompany.yourapp"
projectApplicationId = "com.yourcompany.yourapp"
projectVersionName = "1.0"
projectMinSdkVersion = "26"
projectTargetSdkVersion = "35"
projectCompileSdkVersion = "35"
projectVersionCode = "1"

# === Optional settings ===
projectIosFrameworkBaseName = "ComposeApp"
featureCorePresentationModule = ":core:presentation"
featureCoreDesignSystemModule = ":core:designsystem"
```

## Available Plugins

| Plugin ID | Description |
|-----------|-------------|
| `com.yourssu.convention.android.application` | Android Application setup |
| `com.yourssu.convention.android.application.compose` | Android Application with Compose |
| `com.yourssu.convention.cmp.application` | Compose Multiplatform Application |
| `com.yourssu.convention.kmp.library` | KMP Library module |
| `com.yourssu.convention.cmp.library` | CMP Library module |
| `com.yourssu.convention.cmp.feature` | CMP Feature module (with optional core dependencies) |
| `com.yourssu.convention.buildkonfig` | BuildKonfig setup |
| `com.yourssu.convention.room` | Room database setup |

## Example Module build.gradle.kts

```kotlin
plugins {
    alias(libs.plugins.convention.cmp.feature)
}

kotlin {
    androidLibrary {
        namespace = "com.yourcompany.feature.example"
    }
}
```

## Updating Submodule

```bash
git submodule update --remote build-logic
git add build-logic
git commit -m "Update build-logic submodule"
```
