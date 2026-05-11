package com.khan366kos.workgraph.backend.domain

import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeType

data class AppContext(
    var requestNode: Node = Node.None,
    var responseNode: Node = Node.None,
    var responseNodes: List<Node> = emptyList(),

    var nodeType: NodeType = NodeType.EMPTY,

    var nodeId: NodeId = NodeId.None,
)