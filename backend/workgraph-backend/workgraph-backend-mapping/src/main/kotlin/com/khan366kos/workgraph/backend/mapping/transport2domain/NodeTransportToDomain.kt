package com.khan366kos.workgraph.backend.mapping.transport2domain

import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.node.NodeContent
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeProperties
import com.khan366kos.workgraph.backend.domain.node.NodeTitle
import com.khan366kos.workgraph.backend.domain.node.NodeType
import com.khan366kos.workgraph.backend.transport.node.CreatableNode
import com.khan366kos.workgraph.backend.transport.node.UpdatableNode

fun CreatableNode.toDomain(): Node = Node(
    id = NodeId.None,
    type = NodeType.valueOf(type),
    title = NodeTitle(title),
    content = content?.let { NodeContent(it) } ?: NodeContent.None,
    properties = properties?.let { NodeProperties(it) } ?: NodeProperties.None
)

fun UpdatableNode.toDomain(): Node = Node(
    id = NodeId(id),
    type = type?.let { NodeType.valueOf(it) } ?: NodeType.EMPTY,
    title = title?.let { NodeTitle(it) } ?: NodeTitle.None,
    content = content?.let { NodeContent(it) } ?: NodeContent.None,
    properties = properties?.let { NodeProperties(it) } ?: NodeProperties.None
)