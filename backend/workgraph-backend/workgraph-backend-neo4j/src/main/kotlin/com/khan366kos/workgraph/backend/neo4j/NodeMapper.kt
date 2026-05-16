package com.khan366kos.workgraph.backend.neo4j

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.edge.EdgeId
import com.khan366kos.workgraph.backend.domain.edge.EdgeType
import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.node.NodeContent
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeProperties
import com.khan366kos.workgraph.backend.domain.node.NodeTitle
import com.khan366kos.workgraph.backend.domain.node.NodeType

internal fun org.neo4j.driver.types.Node.toDomain(): Node {
    val properties = keys()
        .filter { it.startsWith("prop_") }
        .associate { it.removePrefix("prop_") to get(it).asString() }

    return Node(
        id = NodeId(get("id").asString()),
        type = NodeType.valueOf(get("type").asString()),
        title = NodeTitle(get("title").asString()),
        content = NodeContent(get("content").asString()),
        properties = NodeProperties(properties)
    )
}

internal fun Node.toNeo4jParams(): Map<String, Any> {
    val params = mutableMapOf<String, Any>(
        "type" to type.name,
        "title" to title.asString(),
        "content" to content.asString()
    )
    properties.asMap().forEach { (k, v) -> params["prop_$k"] = v }
    return params
}

internal fun org.neo4j.driver.types.Relationship.toDomain(fromNode: NodeId, toNode: NodeId): Edge =
    Edge(
        id = EdgeId(get("id").asString()),
        fromNode = fromNode,
        toNode = toNode,
        type = EdgeType.valueOf(type())
    )