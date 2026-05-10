package com.khan366kos.workgraph.backend.neo4j

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.neo4j.driver.Session

internal suspend inline fun <T> withSession(
    session: Session,
    crossinline block: (Session) -> T
): Result<T> = withContext(Dispatchers.IO) {
    runCatching { session.use(block) }
        .onFailure { if (it is CancellationException) throw it }
}
