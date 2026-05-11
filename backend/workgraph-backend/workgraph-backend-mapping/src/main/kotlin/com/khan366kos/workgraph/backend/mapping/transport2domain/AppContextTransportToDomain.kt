package com.khan366kos.workgraph.backend.mapping.transport2domain

import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeType
import com.khan366kos.workgraph.backend.transport.node.CreateNodeCommand
import com.khan366kos.workgraph.backend.transport.node.ListNodesQuery
import com.khan366kos.workgraph.backend.transport.node.UpdateNodeCommand

fun AppContext.setCommand(command: CreateNodeCommand) = apply {
    requestNode = command.createNode.toDomain()
}

fun AppContext.setQuery(query: NodeId) = apply {
    nodeId = query
}

fun AppContext.setCommand(command: UpdateNodeCommand) = apply {
    requestNode = command.updateNode.toDomain()
}

fun AppContext.setCommand(command: ListNodesQuery) = apply {
    nodeType = command.type?.let { NodeType.valueOf(it) } ?: NodeType.EMPTY
}