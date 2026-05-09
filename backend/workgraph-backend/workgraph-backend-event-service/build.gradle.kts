plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.workgraphBackendCommon)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}