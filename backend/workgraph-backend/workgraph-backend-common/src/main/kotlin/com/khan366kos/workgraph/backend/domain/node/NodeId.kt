package com.khan366kos.workgraph.backend.domain.node

@JvmInline
value class NodeId(val value: String) {
    fun asString(): String = value

    companion object {
        val None = NodeId("")
    }
}
