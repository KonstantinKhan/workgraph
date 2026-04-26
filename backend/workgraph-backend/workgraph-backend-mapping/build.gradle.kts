plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.workgraphBackendCommon)
    implementation(projects.workgraphBackendTransport)

    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertion.core)
    testImplementation(libs.kotest.property)

}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}