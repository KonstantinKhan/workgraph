import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.domain.exceptions.NodeNotFoundException
import com.khan366kos.workgraph.backend.domain.exceptions.NodeOperationFailedException
import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeIdRequest
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
        when (val result = nodeRepo.create(DbNodeRequest(context.requestNode))) {
            is NodeRepoResult.Single -> result.node
            is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
            else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
        }


    suspend fun readNode(context: AppContext) {
        context.apply {
            when (val result = nodeRepo.read(DbNodeIdRequest(nodeId))) {
                is NodeRepoResult.Single -> responseNode = result.node
                is NodeRepoResult.NotFound -> throw NodeNotFoundException(nodeId)
                is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
                else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
            }
        }
    }

    suspend fun updateNode(context: AppContext) {
        context.apply {
            when (val result = nodeRepo.update(DbNodeRequest(requestNode))) {
                is NodeRepoResult.Single -> responseNode = result.node
                is NodeRepoResult.NotFound -> throw NodeNotFoundException(requestNode.id)
                is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
                else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
            }
        }
    }

    suspend fun deleteNode(context: AppContext) {
        context.apply {
            when (val result = nodeRepo.delete(DbNodeIdRequest(nodeId))) {
                is NodeRepoResult.Single -> responseNode = result.node
                is NodeRepoResult.NotFound -> throw NodeNotFoundException(nodeId)
                is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
                else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
            }
        }
    }

    fun searchNode(context: AppContext) {
        context.apply {

        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun nodeId(): Uuid = Uuid.random()

}