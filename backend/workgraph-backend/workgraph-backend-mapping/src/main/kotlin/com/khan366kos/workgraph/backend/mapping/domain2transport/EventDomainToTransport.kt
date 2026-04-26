package com.khan366kos.workgraph.backend.mapping.domain2transport

import com.khan366kos.workgraph.backend.domain.event.Event
import com.khan366kos.workgraph.backend.transport.event.EventResponseDto

fun Event.toDto(): EventResponseDto = EventResponseDto(
    id = id.asString(),
    type = type.name,
    eventTime = eventTime,
    recordedAt = recordedAt,
    actorId = actorId.asString(),
    nodeIds = nodeIds.map { it.asString() },
    edgeIds = edgeIds.map { it.asString() },
    metadata = metadata
)