package com.khan366kos.workgraph.backend.mapping.domain2transport

import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.transport.node.TaskTreeNodeDto

fun buildTaskTree(nodes: List<Node>, edges: List<Edge>): List<TaskTreeNodeDto> {
    val childrenByParent = mutableMapOf<String, MutableList<String>>()
    val parentsByChild = mutableMapOf<String, MutableList<String>>()

    for (edge in edges) {
        val from = edge.fromNode.asString()
        val to = edge.toNode.asString()
        childrenByParent.getOrPut(from) { mutableListOf() }.add(to)
        parentsByChild.getOrPut(to) { mutableListOf() }.add(from)
    }

    val nodeMap = nodes.associateBy { it.id.asString() }
    val childIds = parentsByChild.keys.toSet()
    val rootNodes = nodes.filter { it.id.asString() !in childIds }

    fun buildNode(node: Node): TaskTreeNodeDto {
        val id = node.id.asString()
        return TaskTreeNodeDto(
            id = id,
            type = node.type.name,
            title = node.title.asString(),
            content = node.content.asString(),
            properties = node.properties.asMap(),
            parentCount = parentsByChild[id]?.size ?: 0,
            children = childrenByParent[id]
                ?.mapNotNull { childId -> nodeMap[childId]?.let { buildNode(it) } }
                ?: emptyList()
        )
    }

    return rootNodes.map { buildNode(it) }
}
