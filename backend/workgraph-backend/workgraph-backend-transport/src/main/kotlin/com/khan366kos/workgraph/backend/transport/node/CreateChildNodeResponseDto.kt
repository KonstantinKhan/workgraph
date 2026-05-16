package com.khan366kos.workgraph.backend.transport.node

import com.khan366kos.workgraph.backend.transport.edge.EdgeResponseDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateChildNodeResponseDto(
    @SerialName("node")
    val node: NodeResponseDto,
    @SerialName("edge")
    val edge: EdgeResponseDto
)