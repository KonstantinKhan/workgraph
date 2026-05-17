package com.khan366kos.workgraph.backend.domain.repository.node

import com.khan366kos.workgraph.backend.domain.edge.EdgeType
import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.node.NodeId

data class DbChildNodeRequest(
    val node: Node,
    val parentNodeId: NodeId,
    val edgeType: EdgeType
)