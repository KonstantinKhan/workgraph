package com.khan366kos.workgraph.backend.transport.node

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateChildNodeCommand(
    @SerialName("createNode")
    val createNode: CreatableNode,
    @SerialName("parentNodeId")
    val parentNodeId: String,
    @SerialName("edgeType")
    val edgeType: String
) : INodeRequest