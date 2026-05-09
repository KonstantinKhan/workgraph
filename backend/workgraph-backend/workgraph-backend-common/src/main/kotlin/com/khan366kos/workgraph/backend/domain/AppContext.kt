package com.khan366kos.workgraph.backend.domain

import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.node.NodeId

data class AppContext(
    var requestNode: Node = Node.None,
    var responseNode: Node = Node.None,
    var responseNodes: List<Node> = emptyList(),

    var nodeId: NodeId = NodeId.None,
)