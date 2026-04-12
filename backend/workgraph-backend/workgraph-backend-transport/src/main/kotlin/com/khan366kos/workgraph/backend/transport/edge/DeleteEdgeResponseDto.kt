package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteEdgeResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("deletedEdge")
    val deletedEdge: EdgeResponseDto
)
