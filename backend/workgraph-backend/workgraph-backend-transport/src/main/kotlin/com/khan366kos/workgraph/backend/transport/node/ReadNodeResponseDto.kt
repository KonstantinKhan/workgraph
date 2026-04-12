package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadNodeResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("readNode")
    val readNode: NodeResponseDto
)
