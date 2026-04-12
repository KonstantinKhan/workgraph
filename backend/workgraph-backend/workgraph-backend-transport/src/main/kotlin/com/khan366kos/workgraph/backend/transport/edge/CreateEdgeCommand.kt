package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEdgeCommand(
    @SerialName("createEdge")
    val createEdge: CreatableEdge,
)
