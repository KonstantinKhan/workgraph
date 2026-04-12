package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadEventResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("readEvent")
    val readEvent: EventResponseDto
)
