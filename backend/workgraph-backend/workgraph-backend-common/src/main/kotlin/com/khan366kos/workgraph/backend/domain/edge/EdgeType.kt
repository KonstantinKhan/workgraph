package com.khan366kos.workgraph.backend.domain.edge

enum class EdgeType(val value: String) {
    REFERENCES("references"),
    DEPENDS_ON("depends_on"),
    RELATES_TO("relates_to"),
    BLOCKS("blocks"),
    DUPLICATES("duplicates"),
    PART_OF("part_of"),
    CUSTOM("custom");

    fun asString(): String = value
}
