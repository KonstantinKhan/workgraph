import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.domain.node.NodeId
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class NodeService {
    @OptIn(ExperimentalUuidApi::class)
    fun createNode(context: AppContext): Unit {
        context.apply {
            responseNode = requestNode.copy(
                id = NodeId(nodeId()),
            )
        }
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