plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":workgraph-backend-common"))
    implementation(project(":workgraph-backend-transport"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}