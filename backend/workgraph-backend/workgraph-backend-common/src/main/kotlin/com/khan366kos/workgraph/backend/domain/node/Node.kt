package com.khan366kos.workgraph.backend.domain.node

data class Node(
    val id: NodeId,
    val type: NodeType,
    val title: String,
    val content: String,
    val properties: NodeProperties = NodeProperties.Empty
) {
    companion object {
        val None = Node(
            id = NodeId.None,
            type = NodeType.DOCUMENT,
            title = "",
            content = "",
            properties = NodeProperties.Empty
        )
    }
}
