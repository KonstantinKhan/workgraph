package com.khan366kos.workgraph.backend.domain.repository.node

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.node.Node

sealed class NodeWithEdgeRepoResult {
    data class Created(val node: Node, val edge: Edge) : NodeWithEdgeRepoResult()
    data object ParentNotFound : NodeWithEdgeRepoResult()
    data class DbError(val cause: Throwable) : NodeWithEdgeRepoResult()
}