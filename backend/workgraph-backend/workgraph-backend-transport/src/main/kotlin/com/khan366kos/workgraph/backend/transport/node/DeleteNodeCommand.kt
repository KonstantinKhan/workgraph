package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteNodeCommand(
    @SerialName("deleteNodeId")
    val deleteNodeId: String
)
