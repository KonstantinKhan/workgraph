import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeRequest
import com.khan366kos.workgraph.backend.domain.repository.node.INodeRepository
import com.khan366kos.workgraph.backend.domain.repository.node.NodeRepoResult
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class NodeService(
    private val nodeRepo: INodeRepository
) {
    @OptIn(ExperimentalUuidApi::class)
    suspend fun createNode(context: AppContext): Node =
        when(val result = nodeRepo.create(DbNodeRequest(context.requestNode))) {
            is NodeRepoResult.Single -> result.node
            is NodeRepoResult.DbError -> throw result.cause
            else -> throw RuntimeException("Unexpected result")
        }


    @OptIn(ExperimentalUuidApi::class)
    fun readNode(context: AppContext): Unit {

        context.apply {

        }
    }

    fun updateNode(context: AppContext): Unit {
        context.apply {
            responseNode = requestNode
        }
    }

    fun deleteNode(context: AppContext): Unit {
        context.apply {

        }

    }

    fun searchNode(context: AppContext) {
        context.apply {

        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun nodeId(): Uuid = Uuid.random()

}