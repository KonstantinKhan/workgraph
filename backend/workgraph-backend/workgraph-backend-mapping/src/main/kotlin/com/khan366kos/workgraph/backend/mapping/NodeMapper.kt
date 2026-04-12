package com.khan366kos.workgraph.backend.mapping

import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeProperties
import com.khan366kos.workgraph.backend.domain.node.NodeType
import com.khan366kos.workgraph.backend.mapping.extensions.asMap
import com.khan366kos.workgraph.backend.mapping.extensions.toNodeId
import com.khan366kos.workgraph.backend.mapping.extensions.toNodeProperties
import com.khan366kos.workgraph.backend.mapping.extensions.toNodeType
import com.khan366kos.workgraph.backend.transport.node.CreateNodeCommand
import com.khan366kos.workgraph.backend.transport.node.DeleteNodeCommand
import com.khan366kos.workgraph.backend.transport.node.GetNodeQuery
import com.khan366kos.workgraph.backend.transport.node.ListNodesQuery
import com.khan366kos.workgraph.backend.transport.node.NodeResponseDto
import com.khan366kos.workgraph.backend.transport.node.UpdateNodeCommand

object NodeMapper {

    fun NodeResponseDto.toDomain(): Node = Node(
        id = id.toNodeId(),
        type = type.toNodeType(),
        title = title,
        content = content,
        properties = properties.toNodeProperties()
    )

    fun Node.toDto(): NodeResponseDto = NodeResponseDto(
        id = id.asString(),
        type = type.asString(),
        title = title,
        content = content,
        properties = properties.asMap()
    )

    fun CreateNodeCommand.toDomain(id: NodeId): Node = Node(
        id = id,
        type = type.toNodeType(),
        title = title,
        content = content,
        properties = properties.toNodeProperties()
    )

    fun UpdateNodeCommand.toDomain(existing: Node): Node = Node(
        id = existing.id,
        type = this.type?.toNodeType() ?: existing.type,
        title = this.title ?: existing.title,
        content = this.content ?: existing.content,
        properties = this.properties?.toNodeProperties() ?: existing.properties
    )

    fun GetNodeQuery.nodeId(): NodeId = id.toNodeId()

    fun DeleteNodeCommand.nodeId(): NodeId = id.toNodeId()

    fun ListNodesQuery.toNodeTypeOrNull(): NodeType? = type?.let { it.toNodeType() }
}
