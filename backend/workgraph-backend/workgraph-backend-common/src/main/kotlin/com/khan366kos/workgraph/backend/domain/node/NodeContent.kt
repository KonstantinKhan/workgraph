package com.khan366kos.workgraph.backend.domain.node

@JvmInline
value class NodeContent(private val value: String) {
    fun asString(): String = value

    companion object {
        val None = NodeContent("")
    }
}