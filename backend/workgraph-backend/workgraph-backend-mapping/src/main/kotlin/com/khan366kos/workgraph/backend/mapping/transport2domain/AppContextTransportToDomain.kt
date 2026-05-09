package com.khan366kos.workgraph.backend.mapping.transport2domain

import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.transport.node.CreateNodeCommand
import com.khan366kos.workgraph.backend.transport.node.UpdateNodeCommand

fun AppContext.setCommand(command: CreateNodeCommand) = apply {
    requestNode = command.createNode.toDomain()
}

fun AppContext.setQuery(query: NodeId) = apply {
    nodeId = nodeId
}

fun AppContext.setCommand(command: UpdateNodeCommand) = apply {
    requestNode = command.updateNode.toDomain()
}