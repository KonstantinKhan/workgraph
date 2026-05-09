package com.khan366kos.workgraph.backend.domain.exceptions

import com.khan366kos.workgraph.backend.domain.node.NodeId

class NodeNotFoundException(nodeId: NodeId) : RuntimeException("Node not found with id ${nodeId.asString()}")

class NodeOperationFailedException(cause: Throwable) : RuntimeException("Node operation failed", cause)