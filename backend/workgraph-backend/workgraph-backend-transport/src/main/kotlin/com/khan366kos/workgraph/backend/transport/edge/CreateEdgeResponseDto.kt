package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEdgeResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("createdEdge")
    val createdEdge: EdgeResponseDto
)