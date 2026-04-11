package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDto(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("eventTime")
    val eventTime: String,
    @SerialName("recordedAt")
    val recordedAt: String,
    @SerialName("actorId")
    val actorId: String,
    @SerialName("nodeIds")
    val nodeIds: List<String>,
    @SerialName("metadata")
    val metadata: Map<String, String> = emptyMap()
)
