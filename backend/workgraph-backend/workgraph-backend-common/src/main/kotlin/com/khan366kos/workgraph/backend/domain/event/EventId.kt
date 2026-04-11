package com.khan366kos.workgraph.backend.domain.event

@JvmInline
value class EventId(val value: String) {
    fun asString(): String = value

    companion object {
        val None = EventId("")
    }
}
