import com.khan366kos.workgraph.backend.domain.AppContext
import com.khan366kos.workgraph.backend.domain.repository.node.DbNodeRequest
import com.khan366kos.workgraph.backend.domain.repository.node.INodeRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class NodeService(private val nodeRepository: INodeRepository) {

    @OptIn(ExperimentalUuidApi::class)
    suspend fun createNode(context: AppContext): Unit {

        with(context) {
            nodeRepository.create(
                DbNodeRequest(
                    node = requestNode
                )
            )
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