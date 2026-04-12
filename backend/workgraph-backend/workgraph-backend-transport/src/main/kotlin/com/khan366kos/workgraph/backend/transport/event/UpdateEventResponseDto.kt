package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateEventResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("updatedEvent")
    val updatedEvent: EventResponseDto
)
