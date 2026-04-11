package com.khan366kos.workgraph.backend.transport.edge

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListEdgesQuery(
    @SerialName("nodeId")
    val nodeId: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("page")
    val page: Int = 1,
    @SerialName("pageSize")
    val pageSize: Int = 20
)
