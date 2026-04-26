package com.khan366kos.workgraph.backend.domain.event

import kotlinx.serialization.Serializable

@Serializable
enum class EventType(private val value: String) {
    UNDEFINED("undefined"),
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted"),
    ARCHIVED("archived"),
    RESTORED("restored"),
    LINKED("linked"),
    UNLINKED("unlinked");

    fun asString(): String = value
}
