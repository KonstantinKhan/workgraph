package com.khan366kos.workgraph.backend.transport.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class ListEventsQuery(
    @SerialName("type")
    val type: String? = null,
    @SerialName("actorId")
    val actorId: String? = null,
    @SerialName("nodeId")
    val nodeId: String? = null,
    @SerialName("fromTime")
    val fromTime: Instant? = null,
    @SerialName("toTime")
    val toTime: Instant? = null,
    @SerialName("page")
    val page: Int = 1,
    @SerialName("pageSize")
    val pageSize: Int = 20
)
