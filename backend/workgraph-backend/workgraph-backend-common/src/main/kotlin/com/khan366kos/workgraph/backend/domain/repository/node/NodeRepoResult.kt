package com.khan366kos.workgraph.backend.domain.repository.node

import com.khan366kos.workgraph.backend.domain.node.Node

sealed class NodeRepoResult {
    data class Single(val node: Node) : NodeRepoResult()
    data class Multiple(val nodes: List<Node>) : NodeRepoResult()
    data object NotFound : NodeRepoResult()
    data object AlreadyExists : NodeRepoResult()
    data class DbError(val cause: Throwable) : NodeRepoResult()
}