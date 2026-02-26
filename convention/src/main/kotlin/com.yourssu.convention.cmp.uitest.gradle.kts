import buildlogic.convention.library

// Opt-in 플러그인: Compose UI 테스트 의존성만 추가
// cmp.library / cmp.feature / cmp.application 위에 적용

dependencies {
    // CMP multiplatform Compose UI 테스트 (commonTest)
    "commonTestImplementation"(library("jetbrains-compose-ui-test"))

    // Android Instrumented Test
    if (configurations.findByName("androidTestImplementation") != null) {
        val composeTestBom = platform(library("androidx-compose-bom"))
        "androidTestImplementation"(composeTestBom)
        "androidTestImplementation"(library("androidx-compose-ui-test-junit4"))
    }
    if (configurations.findByName("debugImplementation") != null) {
        "debugImplementation"(library("androidx-compose-ui-test-manifest"))
    }
}
