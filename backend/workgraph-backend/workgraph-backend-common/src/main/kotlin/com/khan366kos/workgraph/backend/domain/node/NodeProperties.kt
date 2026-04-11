package com.khan366kos.workgraph.backend.domain.node

@JvmInline
value class NodeProperties(val value: Map<String, String>) {
    fun asString(): String = value.entries.joinToString(separator = "&") { "${it.key}=${it.value}" }

    companion object {
        val Empty = NodeProperties(emptyMap())
    }
}
