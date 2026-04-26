package com.khan366kos.workgraph.backend.mapping.domain2transport

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.transport.edge.EdgeResponseDto

fun Edge.toResponseDto(): EdgeResponseDto = EdgeResponseDto(
    id = id.asString(),
    fromNodeId = fromNode.asString(),
    toNodeId = toNode.asString(),
    type = type.name
)