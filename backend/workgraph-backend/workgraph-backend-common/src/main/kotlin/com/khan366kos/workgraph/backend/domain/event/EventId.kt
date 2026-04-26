package com.khan366kos.workgraph.backend.domain.event

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class EventId(private val value: String) {
    fun asString(): String = value

    companion object {
        val None = EventId("")
    }
}
