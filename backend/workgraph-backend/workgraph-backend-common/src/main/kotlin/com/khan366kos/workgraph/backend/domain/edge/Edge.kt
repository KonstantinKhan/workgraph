package com.khan366kos.workgraph.backend.domain.edge

import com.khan366kos.workgraph.backend.domain.node.NodeId

data class Edge(
    val id: EdgeId,
    val fromNode: NodeId,
    val toNode: NodeId,
    val type: EdgeType
) {
    companion object {
        val None = Edge(
            id = EdgeId.None,
            fromNode = NodeId.None,
            toNode = NodeId.None,
            type = EdgeType.REFERENCES
        )
    }
}
