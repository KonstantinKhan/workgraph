package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateEdgeResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("updatedEdge")
    val updatedEdge: EdgeResponseDto
)
