package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteEventResponseDto(
    @SerialName("messageType")
    val messageType: String,
    @SerialName("deletedEventId")
    val deletedEventId: String
)
