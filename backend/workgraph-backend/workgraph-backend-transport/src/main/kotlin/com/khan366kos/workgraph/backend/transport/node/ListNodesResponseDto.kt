package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListNodesResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("nodes")
    val nodes: List<NodeResponseDto>
)
