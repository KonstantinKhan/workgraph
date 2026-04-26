package com.khan366kos.workgraph.backend.mapping

import com.khan366kos.workgraph.backend.domain.node.NodeContent
import com.khan366kos.workgraph.backend.domain.node.NodeId
import com.khan366kos.workgraph.backend.domain.node.NodeProperties
import com.khan366kos.workgraph.backend.domain.node.NodeTitle
import com.khan366kos.workgraph.backend.domain.node.NodeType
import com.khan366kos.workgraph.backend.mapping.transport2domain.toDomain
import com.khan366kos.workgraph.backend.transport.node.CreatableNode
import io.kotest.core.Tag
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NodeMappingTests : FunSpec({
    test("CreatableNode with all fields should map to Domain correctly").config(
        tags = setOf(Tag("CreatableNode")),
    ) {
        val creatableNode = CreatableNode(
            type = "TASK",
            title = "Test task node",
            content = "Some content",
            properties = mapOf("key1" to "value1", "key2" to "value2")
        )

        val domainNode = creatableNode.toDomain()

        with(domainNode) {
            id shouldBe NodeId.None
            type shouldBe NodeType.TASK
            title shouldBe NodeTitle("Test task nodes")
            content shouldBe NodeContent("Some content")
            properties shouldBe NodeProperties(mapOf("key1" to "value1", "key2" to "value2"))
        }
    }
})