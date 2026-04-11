package com.khan366kos.workgraph.backend.domain.node

enum class NodeType(val value: String) {
    DOCUMENT("document"),
    TASK("task"),
    NOTE("note"),
    LINK("link"),
    FOLDER("folder"),
    COMMENT("comment");

    fun asString(): String = value
}
