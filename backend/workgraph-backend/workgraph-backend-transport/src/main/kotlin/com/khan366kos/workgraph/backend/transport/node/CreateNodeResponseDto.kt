package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNodeResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("createdNode")
    val createdNode: NodeResponseDto
)
