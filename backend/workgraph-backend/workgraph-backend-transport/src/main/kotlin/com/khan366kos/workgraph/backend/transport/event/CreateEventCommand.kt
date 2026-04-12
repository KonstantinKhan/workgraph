package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventCommand(
    @SerialName("createEvent")
    val createEvent: CreatableEvent
)
