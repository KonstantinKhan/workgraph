package com.khan366kos.workgraph.backend.ktor.app

import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.mapping.domain2transport.toDto
import com.khan366kos.workgraph.backend.mapping.transport2domain.toDomain
import com.khan366kos.workgraph.backend.transport.node.CreateNodeCommand
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Routing.node(): Route = route("node") {
    get() {
        call.respondText(text = "Node endpoint")
    }
    post("create") {
        val command = call.receive<CreateNodeCommand>()
        val context = AppContext()
        val node = command.createNode.toDomain()
        context.apply {
            this.requestNode = node
        }
        val response = context.requestNode
        context.apply {
            this.responseNode = response
        }
        call.respond(context.responseNode.toDto())
    }
}