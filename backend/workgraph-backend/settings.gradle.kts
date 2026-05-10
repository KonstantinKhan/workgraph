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
include("workgraph-backend-event-service")
include("workgraph-backend-event-service")
include("workgraph-backend-node-service")
include("workgraph-backend-edge-service")
include("workgraph-backend-neo4j")