package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatableEdge(
    @SerialName("id")
    val id: String,
    @SerialName("fromNodeId")
    val fromNodeId: String? = null,
    @SerialName("toNodeId")
    val toNodeId: String? = null,
    @SerialName("type")
    val type: String? = null,
)