package com.khan366kos.workgraph.backend.domain.event

import com.khan366kos.workgraph.backend.domain.actor.ActorId
import com.khan366kos.workgraph.backend.domain.edge.EdgeId
import com.khan366kos.workgraph.backend.domain.node.NodeId
import kotlin.time.Instant

data class Event(
    val id: EventId,
    val type: EventType,
    val eventTime: Instant,
    val recordedAt: Instant,
    val actorId: ActorId,
    val nodeIds: List<NodeId>,
    val edgeIds: List<EdgeId>,
    val metadata: Map<String, String> = emptyMap()
) {
    companion object {
        val None = Event(
            id = EventId.None,
            type = EventType.UNDEFINED,
            eventTime = Instant.DISTANT_PAST,
            recordedAt = Instant.DISTANT_PAST,
            actorId = ActorId.None,
            nodeIds = emptyList(),
            edgeIds = emptyList(),
            metadata = emptyMap()
        )
    }
}
