package com.khan366kos.workgraph.backend.mapping

import com.khan366kos.workgraph.backend.domain.actor.ActorId
import com.khan366kos.workgraph.backend.domain.event.Event
import com.khan366kos.workgraph.backend.domain.event.EventId
import com.khan366kos.workgraph.backend.domain.event.EventType
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.transport.event.EventResponseDto
import com.khan366kos.workgraph.backend.transport.event.GetEventQuery
import com.khan366kos.workgraph.backend.transport.event.ListEventsQuery
import kotlinx.datetime.Instant

object EventMapper {

    fun EventResponseDto.toDomain(): Event = Event(
        id = id,
        type = type,
        eventTime = eventTime,
        recordedAt = recordedAt,
        actor = actorId,
        nodeIds = nodeIds,
        metadata = metadata
    )

    fun Event.toDto(): EventResponseDto = EventResponseDto(
        id = id,
        type = type,
        eventTime = eventTime,
        recordedAt = recordedAt,
        actorId = actor,
        nodeIds = nodeIds,
        metadata = metadata
    )

    fun GetEventQuery.eventId(): EventId = id

    fun ListEventsQuery.toEventTypeOrNull(): EventType? = type

    fun ListEventsQuery.toActorIdOrNull(): ActorId? = actorId

    fun ListEventsQuery.toNodeIdOrNull(): NodeId? = nodeId

    fun ListEventsQuery.toFromTimeOrNull(): Instant? = fromTime

    fun ListEventsQuery.toToTimeOrNull(): Instant? = toTime
}
