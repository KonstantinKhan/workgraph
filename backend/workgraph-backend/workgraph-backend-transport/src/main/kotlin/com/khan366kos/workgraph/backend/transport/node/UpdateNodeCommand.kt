package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateNodeCommand(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("properties")
    val properties: Map<String, String>? = null
)
