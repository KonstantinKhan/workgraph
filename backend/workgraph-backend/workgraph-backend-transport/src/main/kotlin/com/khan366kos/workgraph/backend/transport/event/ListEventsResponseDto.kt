package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListEventsResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("events")
    val events: List<EventResponseDto>
)
