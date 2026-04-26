package com.khan366kos.workgraph.backend.domain.node

data class Node(
    val id: NodeId,
    val type: NodeType,
    val title: NodeTitle,
    val content: NodeContent,
    val properties: NodeProperties
) {
    companion object {
        val None = Node(
            id = NodeId.None,
            type = NodeType.EMPTY,
            title = NodeTitle.None,
            content = NodeContent.None,
            properties = NodeProperties.None
        )
    }
}
