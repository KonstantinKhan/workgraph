plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(projects.workgraphBackendCommon)

    implementation(libs.neo4j)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}