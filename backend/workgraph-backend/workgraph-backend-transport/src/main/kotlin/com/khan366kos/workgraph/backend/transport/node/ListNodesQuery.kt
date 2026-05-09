package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListNodesQuery(
    @SerialName("type")
    val type: String? = null,
    @SerialName("page")
    val page: Int = 1,
    @SerialName("pageSize")
    val pageSize: Int = 20
): INodeRequest
