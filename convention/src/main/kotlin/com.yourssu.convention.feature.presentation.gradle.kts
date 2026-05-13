import buildlogic.convention.featureSiblingDomainPath

plugins {
    id("com.yourssu.convention.cmp.feature")
}

dependencies {
    commonMainImplementation(project(":core:domain"))
    commonMainImplementation(project(":core:designsystem"))
    commonMainImplementation(project(":core:navigation"))
    commonMainImplementation(project(":core:presentation"))
    commonMainImplementation(project(":core:resources"))
    commonMainImplementation(project(featureSiblingDomainPath(expectedLayer = "presentation")))
}
