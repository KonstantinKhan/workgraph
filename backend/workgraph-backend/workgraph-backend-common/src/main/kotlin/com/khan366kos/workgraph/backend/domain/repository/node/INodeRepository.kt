package com.khan366kos.workgraph.backend.domain.repository.node

interface INodeRepository {
    suspend fun create(request: DbNodeRequest): DbNodeResponse
    suspend fun read(request: DbNodeIdRequest): DbNodeResponse
    suspend fun update(request: DbNodeRequest): DbNodeResponse
    suspend fun delete(request: DbNodeIdRequest): DbNodeResponse
    suspend fun search(request: DbNodeFilterRequest): DbNodesResponse
}
