import buildlogic.convention.featureSiblingDomainPath

plugins {
    id("com.yourssu.convention.kmp.library")
}

dependencies {
    commonMainImplementation(project(":core:data"))
    commonMainImplementation(project(featureSiblingDomainPath(expectedLayer = "data")))
}
