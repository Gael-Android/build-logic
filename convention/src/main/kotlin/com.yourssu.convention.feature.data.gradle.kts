import buildlogic.convention.featureSiblingDomainPath
import buildlogic.convention.library

plugins {
    id("com.yourssu.convention.kmp.library")
}

dependencies {
    commonMainImplementation(project(":core:domain"))
    commonMainImplementation(project(":core:data"))
    commonMainImplementation(project(featureSiblingDomainPath(expectedLayer = "data")))

    commonMainImplementation(library("koin-core"))
    commonMainImplementation(library("ktor-client-core"))

    commonTestImplementation(library("ktor-client-mock"))
}
