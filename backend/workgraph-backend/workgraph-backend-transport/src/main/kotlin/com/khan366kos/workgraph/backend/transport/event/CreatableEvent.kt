package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CreatableEvent(
    @SerialName("type")
    val type: String,
    @SerialName("eventTime")
    val eventTime: Instant,
    @SerialName("actorId")
    val actorId: String,
    @SerialName("nodeIds")
    val nodeIds: List<String>,
    @SerialName("metadata")
    val metadata: Map<String, String> = emptyMap()
)
