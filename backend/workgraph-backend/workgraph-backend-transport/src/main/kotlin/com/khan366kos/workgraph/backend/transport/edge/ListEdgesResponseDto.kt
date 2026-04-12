package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListEdgesResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("edges")
    val edges: List<EdgeResponseDto>
)