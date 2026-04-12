package com.khan366kos.workgraph.backend.mapping.extensions

import com.khan366kos.workgraph.backend.domain.actor.ActorId
import com.khan366kos.workgraph.backend.domain.edge.EdgeId
import com.khan366kos.workgraph.backend.domain.edge.EdgeType
import com.khan366kos.workgraph.backend.domain.event.EventId
import com.khan366kos.workgraph.backend.domain.event.EventType
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeProperties
import com.khan366kos.workgraph.backend.domain.node.NodeType

// ─── ID Value Objects ────────────────────────────────────────────────────────

fun String.toNodeId(): NodeId = NodeId(this)

fun String.toEdgeId(): EdgeId = EdgeId(this)

fun String.toEventId(): EventId = EventId(this)

fun String.toActorId(): ActorId = ActorId(this)

// ─── Enum Types ──────────────────────────────────────────────────────────────

private inline fun <reified T : Enum<T>> String.toEnumByPropertyValue(
    enumValueSelector: (T) -> String,
): T =
    enumValues<T>().find { enumValueSelector(it) == this }
        ?: throw IllegalArgumentException("Unknown ${T::class.simpleName}: '$this'")

fun String.toNodeType(): NodeType = toEnumByPropertyValue(NodeType::value)

fun String.toEdgeType(): EdgeType = toEnumByPropertyValue(EdgeType::value)

fun String.toEventType(): EventType = toEnumByPropertyValue(EventType::value)

// ─── NodeProperties ──────────────────────────────────────────────────────────

fun Map<String, String>.toNodeProperties(): NodeProperties = NodeProperties(this)

fun NodeProperties.asMap(): Map<String, String> = value

// ─── Instant ─────────────────────────────────────────────────────────────────
// kotlinx.datetime.Instant has built-in toInstantIsoString() and fromInstantIsoString()
// Use kotlinx.datetime.Instant.parse() for ISO-8601 strings when needed.
