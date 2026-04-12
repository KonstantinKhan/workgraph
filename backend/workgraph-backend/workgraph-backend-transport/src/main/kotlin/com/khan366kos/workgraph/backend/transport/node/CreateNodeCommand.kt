package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNodeCommand(
    @SerialName("createNode")
    val createNode: CreatableNode
)
