package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadEdgeResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("readEdge")
    val readEdge: EdgeResponseDto
)
