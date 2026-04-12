package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateEdgeCommand(
    @SerialName("updateEdge")
    val updateEdge: UpdatableEdge
)
