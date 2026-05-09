package com.khan366kos.workgraph.backend.domain

class AppError(
    val field: String,
    val exception: Throwable
)
