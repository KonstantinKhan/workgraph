package com.khan366kos.workgraph.backend.domain.node

@JvmInline
value class NodeProperties(val value: Map<String, String>) {
    fun asMap(): Map<String, String> = value

    companion object {
        val None = NodeProperties(emptyMap())
    }
}
