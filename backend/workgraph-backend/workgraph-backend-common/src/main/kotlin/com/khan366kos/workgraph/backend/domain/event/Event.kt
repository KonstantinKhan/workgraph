package com.khan366kos.workgraph.backend.domain.event

import com.khan366kos.workgraph.backend.domain.actor.ActorId
import com.khan366kos.workgraph.backend.domain.node.NodeId
import kotlin.time.Instant

data class Event(
    val id: EventId,
    val type: EventType,
    val eventTime: Instant,
    val recordedAt: Instant,
    val actor: ActorId,
    val nodeIds: List<NodeId>,
    val metadata: Map<String, String> = emptyMap()
) {
    companion object {
        val None = Event(
            id = EventId.None,
            type = EventType.CREATED,
            eventTime = Instant.DISTANT_PAST,
            recordedAt = Instant.DISTANT_PAST,
            actor = ActorId.None,
            nodeIds = emptyList(),
            metadata = emptyMap()
        )
    }
}
