package com.khan366kos.workgraph.backend.domain.repository.node

interface INodeRepository {
    suspend fun create(request: DbNodeRequest): NodeRepoResult
    suspend fun read(request: DbNodeIdRequest): NodeRepoResult
    suspend fun update(request: DbNodeRequest): NodeRepoResult
    suspend fun delete(request: DbNodeIdRequest): NodeRepoResult
    suspend fun search(request: DbNodeFilterRequest): NodeRepoResult
}
