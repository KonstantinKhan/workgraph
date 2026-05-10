package com.khan366kos.workgraph.backend.ktor.app.configs

import NodeService
import com.khan366kos.workgraph.backend.domain.repository.node.INodeRepository
import com.khan366kos.workgraph.backend.neo4j.Neo4jNodeRepository
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase

class AppConfig {
    private val driver = GraphDatabase.driver(
        System.getenv("NEO4J_URI") ?: "bolt://localhost:7687",
        AuthTokens.basic(
            System.getenv("NEO4J_USER") ?: "neo4j",
            System.getenv("NEO4J_PASSWORD") ?: "password"
        )
    )

    val nodeRepository: INodeRepository = Neo4jNodeRepository(
        driver = driver,
        database = System.getenv("NEO4J_DATABASE") ?: "neo4j"
    )
    val nodeService: NodeService = NodeService(nodeRepository)
}