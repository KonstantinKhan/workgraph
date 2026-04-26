package com.khan366kos.workgraph.backend.mapping.domain2transport

import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.transport.node.NodeResponseDto

fun Node.toDto(): NodeResponseDto = NodeResponseDto(
    id = id.asString(),
    type = type.name,
    title = title.asString(),
    content = content.asString(),
    properties = properties.asMap()
)