package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class UpdatableEvent(
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String? = null,
    @SerialName("eventTime")
    val eventTime: Instant? = null,
    @SerialName("actorId")
    val actorId: String? = null,
    @SerialName("nodeIds")
    val nodeIds: List<String>? = null,
    @SerialName("metadata")
    val metadata: Map<String, String>? = null
)
