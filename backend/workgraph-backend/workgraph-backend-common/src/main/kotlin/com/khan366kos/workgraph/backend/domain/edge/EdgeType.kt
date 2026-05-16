package com.khan366kos.workgraph.backend.domain.edge

enum class EdgeType(val value: String) {
    EMPTY("empty"),
    HAS_CHILD("has_child"),
    REFERENCES("references"),
    DEPENDS_ON("depends_on"),
    RELATES_TO("relates_to"),
    BLOCKS("blocks"),
    DUPLICATES("duplicates"),
    PART_OF("part_of"),
    CUSTOM("custom");
}
