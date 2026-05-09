package com.khan366kos.workgraph.backend.ktor.app.configs

import NodeService
import com.khan366kos.workgraph.backend.domain.repository.node.INodeRepository

class AppConfig {
    val nodeRepository: INodeRepository = TODO()
    val nodeService: NodeService = NodeService(nodeRepository)
}