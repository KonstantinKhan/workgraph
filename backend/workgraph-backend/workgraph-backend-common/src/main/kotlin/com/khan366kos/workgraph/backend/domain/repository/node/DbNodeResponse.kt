package com.khan366kos.workgraph.backend.domain.repository.node

import com.khan366kos.workgraph.backend.domain.AppError
import com.khan366kos.workgraph.backend.domain.node.Node

class DbNodeResponse(
    val isSuccess: Boolean,
    val errors: List<AppError>,
    val result: Node
)