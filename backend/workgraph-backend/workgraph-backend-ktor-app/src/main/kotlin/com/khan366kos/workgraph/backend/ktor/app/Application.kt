package com.khan366kos.workgraph.backend.ktor.app

import com.khan366kos.workgraph.backend.ktor.app.configs.AppConfig
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module(
    config: AppConfig = AppConfig(),
) {
    configureSerialization()
    configureHTTP()
    configureRouting(config.nodeService)
}
