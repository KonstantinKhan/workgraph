rootProject.name = "workgraph-backend"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("workgraph-backend-common")
include("workgraph-backend-transport")
include("workgraph-backend-mapping")
include("workgraph-backend-ktor-app")