package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateEdgeCommand(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String? = null
)
