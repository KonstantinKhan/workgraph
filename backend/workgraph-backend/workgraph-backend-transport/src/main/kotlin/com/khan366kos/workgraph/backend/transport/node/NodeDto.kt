package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NodeDto(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("properties")
    val properties: Map<String, String> = emptyMap()
)
