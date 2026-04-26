package com.khan366kos.workgraph.backend.domain.node

enum class NodeType(private val value: String) {
    EMPTY("empty"),
    DOCUMENT("document"),
    TASK("task"),
    NOTE("note"),
    LINK("link"),
    FOLDER("folder"),
    COMMENT("comment");
}
