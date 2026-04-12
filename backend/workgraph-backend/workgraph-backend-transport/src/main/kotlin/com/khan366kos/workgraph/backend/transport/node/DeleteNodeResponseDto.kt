package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteNodeResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("deletedNodeId")
    val deletedNodeId: String
)
