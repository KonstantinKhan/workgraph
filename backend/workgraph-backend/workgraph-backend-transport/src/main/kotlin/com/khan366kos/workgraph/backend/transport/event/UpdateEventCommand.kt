package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateEventCommand(
    @SerialName("updateEvent")
    val updateEvent: UpdatableEvent
)
