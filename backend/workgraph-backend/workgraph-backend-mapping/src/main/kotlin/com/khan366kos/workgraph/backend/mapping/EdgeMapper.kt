package com.khan366kos.workgraph.backend.mapping

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.edge.EdgeId
import com.khan366kos.workgraph.backend.domain.edge.EdgeType
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.mapping.extensions.toEdgeId
import com.khan366kos.workgraph.backend.mapping.extensions.toEdgeType
import com.khan366kos.workgraph.backend.mapping.extensions.toNodeId
import com.khan366kos.workgraph.backend.transport.edge.CreateEdgeCommand
import com.khan366kos.workgraph.backend.transport.edge.DeleteEdgeCommand
import com.khan366kos.workgraph.backend.transport.edge.EdgeResponseDto
import com.khan366kos.workgraph.backend.transport.edge.GetEdgeQuery
import com.khan366kos.workgraph.backend.transport.edge.ListEdgesQuery
import com.khan366kos.workgraph.backend.transport.edge.UpdateEdgeCommand

object EdgeMapper {

    fun Edge.toResponseDto(): EdgeResponseDto = EdgeResponseDto(
        id = id.asString(),
        fromNodeId = fromNode.asString(),
        toNodeId = toNode.asString(),
        type = type.asString()
    )

    fun CreateEdgeCommand.toDomain(id: EdgeId): Edge = Edge(
        id = id,
        fromNode = createEdge.fromNodeId.toNodeId(),
        toNode = createEdge.toNodeId.toNodeId(),
        type = createEdge.type.toEdgeType()
    )

    fun UpdateEdgeCommand.toDomain(existing: Edge): Edge = Edge(
        id = existing.id,
        fromNode = existing.fromNode,
        toNode = existing.toNode,
        type = updateEdge.type?.toEdgeType() ?: existing.type
    )

    fun GetEdgeQuery.edgeId(): EdgeId = getEdgeId.toEdgeId()

    fun DeleteEdgeCommand.edgeId(): EdgeId = deleteEdgeId.toEdgeId()

    fun ListEdgesQuery.toNodeIdOrNull(): NodeId? = nodeId?.toNodeId()

    fun ListEdgesQuery.toEdgeTypeOrNull(): EdgeType? = type?.toEdgeType()
}