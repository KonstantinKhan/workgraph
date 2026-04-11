package com.khan366kos.workgraph.backend.domain.event

enum class EventType(val value: String) {
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted"),
    ARCHIVED("archived"),
    RESTORED("restored"),
    LINKED("linked"),
    UNLINKED("unlinked");

    fun asString(): String = value
}
