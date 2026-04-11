package com.khan366kos.workgraph.backend.domain.edge

@JvmInline
value class EdgeId(val value: String) {
    fun asString(): String = value

    companion object {
        val None = EdgeId("")
    }
}
