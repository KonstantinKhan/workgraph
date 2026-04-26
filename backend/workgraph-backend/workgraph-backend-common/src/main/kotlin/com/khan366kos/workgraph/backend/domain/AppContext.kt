package com.khan366kos.workgraph.backend.domain

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.node.Node

data class AppContext(
    val node: Node = Node.None,
)