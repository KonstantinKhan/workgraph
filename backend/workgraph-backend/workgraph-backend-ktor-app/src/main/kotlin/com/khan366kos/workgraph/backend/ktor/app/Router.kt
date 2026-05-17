package com.khan366kos.workgraph.backend.ktor.app

import NodeService
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.ktor.app.helpers.handleRoute
import com.khan366kos.workgraph.backend.ktor.app.helpers.handleParams
import com.khan366kos.workgraph.backend.mapping.domain2transport.buildTaskTree
import com.khan366kos.workgraph.backend.mapping.domain2transport.toResponseDto
import com.khan366kos.workgraph.backend.mapping.transport2domain.setCommand
import com.khan366kos.workgraph.backend.mapping.transport2domain.setQuery
import com.khan366kos.workgraph.backend.transport.node.CreateChildNodeCommand
import com.khan366kos.workgraph.backend.transport.node.CreateNodeCommand
import com.khan366kos.workgraph.backend.transport.node.ListNodesQuery
import com.khan366kos.workgraph.backend.transport.node.UpdateNodeCommand
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Routing.node(
    nodeService: NodeService,
): Route = route("node") {
    post("create") {
        call.handleRoute<CreateNodeCommand>() { request ->
            this.setCommand(request)
            this.responseNode = nodeService.createNode(this)
        }
    }

    post("create-child") {
        call.handleRoute<CreateChildNodeCommand> { request ->
            this.setCommand(request)
            val (node, edge) = nodeService.createChildNode(this)
            this.responseNode = node
            this.responseEdge = edge
        }
    }

    get("edges/has-child") {
        val edges = nodeService.searchTaskChildEdges()
        call.respond(edges.map { it.toResponseDto() })
    }

    get("tree") {
        val nodes = nodeService.searchTaskNodes()
        val edges = nodeService.searchTaskChildEdges()
        call.respond(buildTaskTree(nodes, edges))
    }

    get("{nodeId}") {
        call.handleParams() {
            this.setQuery(NodeId(call.parameters["nodeId"]!!))
            this.responseNode = nodeService.readNode(this)
        }
    }

    put("update") {
        call.handleRoute<UpdateNodeCommand>() { request ->
            this.setCommand(request)
            this.responseNode = nodeService.updateNode(this)
        }
    }

    delete("{nodeId}") {
        call.handleParams() {
            this.setQuery(NodeId(call.parameters["nodeId"]!!))
            this.responseNode = nodeService.deleteNode(this)
        }
    }

    post("search") {
        call.handleRoute<ListNodesQuery>() { request ->
            this.setCommand(request)
            this.responseNodes = nodeService.searchNode(this)
        }
    }
}