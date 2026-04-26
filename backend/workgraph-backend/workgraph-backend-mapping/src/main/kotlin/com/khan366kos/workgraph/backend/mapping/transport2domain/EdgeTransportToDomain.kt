package com.khan366kos.workgraph.backend.mapping.transport2domain

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.edge.EdgeId
import com.khan366kos.workgraph.backend.domain.edge.EdgeType
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.transport.edge.CreatableEdge
import com.khan366kos.workgraph.backend.transport.edge.UpdatableEdge

fun CreatableEdge.toDomain(): Edge =
    Edge(
        id = EdgeId.None,
        fromNode = NodeId(fromNodeId),
        toNode = NodeId(toNodeId),
        type = EdgeType.valueOf(type)
    )

fun UpdatableEdge.toDomain(): Edge = Edge(
    id = EdgeId(id),
    fromNode = fromNodeId?.let { NodeId(it) } ?: NodeId.None,
    toNode = toNodeId?.let { NodeId(it) } ?: NodeId.None,
    type = type?.let { EdgeType.valueOf(it) } ?: EdgeType.EMPTY
)