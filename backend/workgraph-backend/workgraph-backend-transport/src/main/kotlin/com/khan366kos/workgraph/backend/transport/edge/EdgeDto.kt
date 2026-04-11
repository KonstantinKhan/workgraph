package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EdgeDto(
    @SerialName("id")
    val id: String,
    @SerialName("fromNodeId")
    val fromNodeId: String,
    @SerialName("toNodeId")
    val toNodeId: String,
    @SerialName("type")
    val type: String
)
