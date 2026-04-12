package com.khan366kos.workgraph.backend.domain.actor

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class ActorId(val value: String) {
    fun asString(): String = value

    companion object {
        val None = ActorId("")
    }
}