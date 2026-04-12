package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("createdEvent")
    val createdEvent: EventResponseDto
)
