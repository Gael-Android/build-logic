import buildlogic.convention.featureSiblingDomainPath

plugins {
    id("com.yourssu.convention.cmp.feature")
}

dependencies {
    commonMainImplementation(project(":core:designsystem"))
    commonMainImplementation(project(featureSiblingDomainPath(expectedLayer = "presentation")))
}
