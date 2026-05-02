package com.khan366kos.workgraph.backend.domain

import com.khan366kos.workgraph.backend.domain.node.Node

data class AppContext(
    var requestNode: Node = Node.None,
    var responseNode: Node = Node.None,
)