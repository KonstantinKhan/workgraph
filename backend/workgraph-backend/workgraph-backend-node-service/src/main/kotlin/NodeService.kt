import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.domain.edge.Edge
import com.khan366kos.workgraph.backend.domain.edge.EdgeType
import com.khan366kos.workgraph.backend.domain.exceptions.NodeNotFoundException
import com.khan366kos.workgraph.backend.domain.exceptions.NodeOperationFailedException
import com.khan366kos.workgraph.backend.domain.node.Node
import com.khan366kos.workgraph.backend.domain.node.NodeType
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeFilterRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeIdRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeRequest
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeWithParentRequest
import com.khan366kos.workgraph.backend.domain.repository.node.INodeRepository
import com.khan366kos.workgraph.backend.domain.repository.node.NodeRepoResult
import com.khan366kos.workgraph.backend.domain.repository.node.NodeWithEdgeRepoResult
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

    suspend fun readNode(context: AppContext): Node =
        when (val result = nodeRepo.read(DbNodeIdRequest(context.nodeId))) {
            is NodeRepoResult.Single -> result.node
            is NodeRepoResult.NotFound -> throw NodeNotFoundException(context.nodeId)
            is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
            else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
        }

    suspend fun updateNode(context: AppContext): Node =
        when (val result = nodeRepo.update(DbNodeRequest(context.requestNode))) {
            is NodeRepoResult.Single -> result.node
            is NodeRepoResult.NotFound -> throw NodeNotFoundException(context.requestNode.id)
            is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
            else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
        }

    suspend fun deleteNode(context: AppContext): Node =
        when (val result = nodeRepo.delete(DbNodeIdRequest(context.nodeId))) {
            is NodeRepoResult.Single -> result.node
            is NodeRepoResult.NotFound -> throw NodeNotFoundException(context.nodeId)
            is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
            else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
        }

    suspend fun searchNode(context: AppContext): List<Node> =
        when (val result = nodeRepo.search(DbNodeFilterRequest(context.nodeType))) {
            is NodeRepoResult.Multiple -> result.nodes
            is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
            else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
        }

    suspend fun createChildNode(context: AppContext): Pair<Node, Edge> =
        when (val result = nodeRepo.createWithParent(
            DbNodeWithParentRequest(context.requestNode, context.parentNodeId, context.requestEdgeType)
        )) {
            is NodeWithEdgeRepoResult.Created -> result.node to result.edge
            is NodeWithEdgeRepoResult.ParentNotFound -> throw NodeNotFoundException(context.parentNodeId)
            is NodeWithEdgeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
        }

    suspend fun searchTaskNodes(): List<Node> =
        when (val result = nodeRepo.search(DbNodeFilterRequest(NodeType.TASK))) {
            is NodeRepoResult.Multiple -> result.nodes
            is NodeRepoResult.DbError -> throw NodeOperationFailedException(result.cause)
            else -> throw NodeOperationFailedException(RuntimeException("Unexpected result"))
        }

    suspend fun searchTaskChildEdges(): List<Edge> =
        nodeRepo.searchEdges(NodeType.TASK, EdgeType.HAS_CHILD)

    @OptIn(ExperimentalUuidApi::class)
    private fun nodeId(): Uuid = Uuid.random()
}