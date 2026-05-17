package com.khan366kos.workgraph.backend.neo4j

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.edge.EdgeType
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeType
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeFilterRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeIdRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbChildNodeRequest
import com.khan366kos.workgraph.backend.domain.repository.node.INodeRepository
import com.khan366kos.workgraph.backend.domain.repository.node.NodeRepoResult
import com.khan366kos.workgraph.backend.domain.repository.node.NodeWithEdgeRepoResult
import org.neo4j.driver.Driver
import org.neo4j.driver.SessionConfig
import org.neo4j.driver.exceptions.NoSuchRecordException
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class Neo4jNodeRepository(
    private val driver: Driver,
    private val database: String = "neo4j"
) : INodeRepository {

    private fun openSession() = driver.session(SessionConfig.forDatabase(database))

    override suspend fun createChild(request: DbChildNodeRequest): NodeWithEdgeRepoResult =
        withSession(openSession()) { session ->
            session.executeWrite { tx ->
                val childId = id()
                val edgeId = id()
                val row = tx.run(
                    $$"""
                    MATCH (parent:Node {id: $parentId})
                    CREATE (child:Node) SET child = $childProps, child.id = $childId
                    CREATE (parent)-[r:$${request.edgeType.name} {id: $edgeId}]->(child)
                    RETURN child, r
                    """.trimIndent(),
                    mapOf(
                        "parentId" to request.parentNodeId.asString(),
                        "childId" to childId,
                        "childProps" to request.node.toNeo4jParams(),
                        "edgeId" to edgeId
                    )
                ).single()
                val childNode = row.get("child").asNode().toDomain()
                val edge = row.get("r").asRelationship().toDomain(
                    fromNode = request.parentNodeId,
                    toNode = childNode.id
                )
                Pair(childNode, edge)
            }
        }.fold(
            onSuccess = { (node, edge) -> NodeWithEdgeRepoResult.Created(node, edge) },
            onFailure = { cause ->
                if (cause is NoSuchRecordException) NodeWithEdgeRepoResult.ParentNotFound
                else NodeWithEdgeRepoResult.DbError(cause)
            }
        )

    override suspend fun create(request: DbNodeRequest): NodeRepoResult =
        withSession(openSession()) { session ->
            session.executeWrite { tx ->
                tx.run(
                    $$"CREATE (n:Node) SET n = $props, n.id = $id RETURN n",
                    mapOf("id" to id(), "props" to request.node.toNeo4jParams()),
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
            println("current node: $node")
            session.executeWrite { tx ->
                tx.run(
                    $$"MATCH (n:Node) WHERE n.id = $id SET n += $props RETURN n",
                    mapOf("id" to node.id.asString(), "props" to node.toNeo4jParams())
                ).single().get("n").asNode().toDomain()
            }
        }.fold(
            onSuccess = {
                println("Updated node: $it")
                NodeRepoResult.Single(it) },
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
                    $$"MATCH (n:Node) WHERE n.id = $id RETURN n",
                    mapOf("id" to id)
                ).single().get("n").asNode().toDomain()
                tx.run(
                    $$"MATCH (n:Node) WHERE n.id = $id DETACH DELETE n",
                    mapOf("id" to id)
                ).consume()
                domainNode
            }
        }.fold(
            onSuccess = {
                NodeRepoResult.Single(it) },
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
            onSuccess = {
                NodeRepoResult.Multiple(it) },
            onFailure = { NodeRepoResult.DbError(it) }
        )

    override suspend fun searchEdges(nodeType: NodeType, edgeType: EdgeType): List<Edge> =
        withSession(openSession()) { session ->
            session.executeRead { tx ->
                tx.run(
                    $$"""
                    MATCH (p:Node {type: $nodeType})-[r]->(c:Node {type: $nodeType})
                    WHERE type(r) = $edgeType
                    RETURN r, p.id as fromNodeId, c.id as toNodeId
                    """.trimIndent(),
                    mapOf("nodeType" to nodeType.name, "edgeType" to edgeType.name)
                ).list { record ->
                    record.get("r").asRelationship().toDomain(
                        fromNode = NodeId(record.get("fromNodeId").asString()),
                        toNode = NodeId(record.get("toNodeId").asString())
                    )
                }
            }
        }.getOrElse { emptyList() }
}

@OptIn(ExperimentalUuidApi::class)
private fun id() = Uuid.random().toString()
