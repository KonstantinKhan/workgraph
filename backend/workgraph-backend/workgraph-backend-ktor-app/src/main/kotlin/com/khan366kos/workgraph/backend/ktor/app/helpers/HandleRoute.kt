package com.khan366kos.workgraph.backend.ktor.app.helpers

import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.mapping.domain2transport.toDto
import com.khan366kos.workgraph.backend.mapping.domain2transport.toResponseDto
import com.khan366kos.workgraph.backend.transport.node.CreateChildNodeCommand
import com.khan366kos.workgraph.backend.transport.node.CreateChildNodeResponseDto
import com.khan366kos.workgraph.backend.transport.node.CreateNodeCommand
import com.khan366kos.workgraph.backend.transport.node.INodeRequest
import com.khan366kos.workgraph.backend.transport.node.ListNodesQuery
import com.khan366kos.workgraph.backend.transport.node.UpdateNodeCommand
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond

suspend inline fun <reified T : INodeRequest> ApplicationCall.handleRoute(
    crossinline block: suspend AppContext.(T) -> Unit
) {
    val context: AppContext = AppContext()
    when (val request = receive<T>()) {
        is CreateNodeCommand -> {
            context.block(request)
            respond(context.responseNode.toDto())
        }

        is CreateChildNodeCommand -> {
            context.block(request)
            respond(CreateChildNodeResponseDto(
                node = context.responseNode.toDto(),
                edge = context.responseEdge.toResponseDto()
            ))
        }

        is UpdateNodeCommand -> {
            context.block(request)
            respond(context.responseNode.toDto())
        }

        is ListNodesQuery -> {
            context.block(request)
            respond(context.responseNodes.map { it.toDto() })
        }
    }
}

suspend inline fun ApplicationCall.handleParams(
    crossinline block: suspend AppContext.() -> Unit
) {
    val context: AppContext = AppContext()
    context.block()
    respond(context.responseNode.toDto())
}