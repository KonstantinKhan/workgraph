package com.khan366kos.workgraph.backend.mapping.transport2domain

import com.khan366kos.workgraph.backend.domain.actor.ActorId
import com.khan366kos.workgraph.backend.domain.edge.EdgeId
import com.khan366kos.workgraph.backend.domain.event.Event
import com.khan366kos.workgraph.backend.domain.event.EventId
import com.khan366kos.workgraph.backend.domain.event.EventType
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.transport.event.CreatableEvent

fun CreatableEvent.toDomain(): Event = Event(
    id = EventId.None,
    type = EventType.valueOf(type),
    eventTime = eventTime,
    recordedAt = recordedAt,
    actorId = ActorId(actorId),
    nodeIds = nodeIds?.map { NodeId(it) } ?: emptyList(),
    edgeIds = edgeIds?.map { EdgeId(it) } ?: emptyList(),
    metadata = metadata ?: emptyMap(),
)