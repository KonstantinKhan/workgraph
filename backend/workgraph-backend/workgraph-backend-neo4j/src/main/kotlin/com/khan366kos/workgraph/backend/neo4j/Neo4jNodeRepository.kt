package com.khan366kos.workgraph.backend.neo4j

import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeFilterRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeIdRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeRequest
import com.khan366kos.workgraph.backend.domain.repository.node.INodeRepository
import com.khan366kos.workgraph.backend.domain.repository.node.NodeRepoResult
import org.neo4j.driver.Driver
import org.neo4j.driver.SessionConfig
import org.neo4j.driver.exceptions.NoSuchRecordException

class Neo4jNodeRepository(
    private val driver: Driver,
    private val database: String = "neo4j"
) : INodeRepository {

    private fun openSession() = driver.session(SessionConfig.forDatabase(database))

    override suspend fun create(request: DbNodeRequest): NodeRepoResult =
        withSession(openSession()) { session ->
            session.executeWrite { tx ->
                tx.run(
                    $$"CREATE (n:Node) SET n = $props RETURN n",
                    mapOf("props" to request.node.toNeo4jParams())
                ).single().get("n").asNode().toDomain()
            }
        }.fold(
            onSuccess = { NodeRepoResult.Single(it) },
            onFailure = { NodeRepoResult.DbError(it) }
        )

    override suspend fun read(request: DbNodeIdRequest): NodeRepoResult =
        withSession(openSession()) { session ->
            session.executeRead { tx ->
                tx.run(
                    $$"MATCH (n:Node) WHERE elementId(n) = $id RETURN n",
                    mapOf("id" to request.nodeId.asString())
                ).single().get("n").asNode().toDomain()
            }
        }.fold(
            onSuccess = { NodeRepoResult.Single(it) },
            onFailure = { cause ->
                if (cause is NoSuchRecordException) NodeRepoResult.NotFound
                else NodeRepoResult.DbError(cause)
            }
        )

    override suspend fun update(request: DbNodeRequest): NodeRepoResult =
        withSession(openSession()) { session ->
            val node = request.node
            session.executeWrite { tx ->
                tx.run(
                    $$"MATCH (n:Node) WHERE elementId(n) = $id SET n = $props RETURN n",
                    mapOf("id" to node.id.asString(), "props" to node.toNeo4jParams())
                ).single().get("n").asNode().toDomain()
            }
        }.fold(
            onSuccess = { NodeRepoResult.Single(it) },
            onFailure = { cause ->
                if (cause is NoSuchRecordException) NodeRepoResult.NotFound
                else NodeRepoResult.DbError(cause)
            }
        )

    override suspend fun delete(request: DbNodeIdRequest): NodeRepoResult =
        withSession(openSession()) { session ->
            session.executeWrite { tx ->
                val id = request.nodeId.asString()
                val domainNode = tx.run(
                    $$"MATCH (n:Node) WHERE elementId(n) = $id RETURN n",
                    mapOf("id" to id)
                ).single().get("n").asNode().toDomain()
                tx.run(
                    $$"MATCH (n:Node) WHERE elementId(n) = $id DETACH DELETE n",
                    mapOf("id" to id)
                ).consume()
                domainNode
            }
        }.fold(
            onSuccess = { NodeRepoResult.Single(it) },
            onFailure = { cause ->
                if (cause is NoSuchRecordException) NodeRepoResult.NotFound
                else NodeRepoResult.DbError(cause)
            }
        )

    override suspend fun search(request: DbNodeFilterRequest): NodeRepoResult =
        withSession(openSession()) { session ->
            session.executeRead { tx ->
                tx.run(
                    $$"MATCH (n:Node {type: $type}) RETURN n",
                    mapOf("type" to request.nodeType.name)
                ).list { it.get("n").asNode().toDomain() }
            }
        }.fold(
            onSuccess = { NodeRepoResult.Multiple(it) },
            onFailure = { NodeRepoResult.DbError(it) }
        )
}
