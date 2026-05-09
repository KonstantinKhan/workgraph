package com.khan366kos.workgraph.backend.domain.node

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@JvmInline
value class NodeId(private val value: String) {

    @OptIn(ExperimentalUuidApi::class)
    constructor(uuid: Uuid) : this(uuid.toString())

    fun asString(): String = value

    @OptIn(ExperimentalUuidApi::class)
    fun asUuid(): Uuid = Uuid.parse(value)

    companion object {
        val None = NodeId("")
    }
}
