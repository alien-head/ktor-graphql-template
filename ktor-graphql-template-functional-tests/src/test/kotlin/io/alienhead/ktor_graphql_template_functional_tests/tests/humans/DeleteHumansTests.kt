package io.alienhead.ktor_graphql_template_functional_tests.tests.humans

import io.alienhead.ktor_graphql_template_functional_tests.utils.SqlHelper
import io.alienhead.ktor_graphql_template_functional_tests.utils.executeGraphQL
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class DeleteHumansTests : DescribeSpec({
    it("should delete a human") {
        val humans = SqlHelper.humans()
        val humanToDelete = humans.random()

        val query = """
            mutation deleteHuman(${'$'}id: ID!) {
                deleteHuman(id: ${'$'}id)
            }
        """.trimIndent()

        val response = executeGraphQL(query, mapOf<String, Any>("id" to humanToDelete.id.value))

        response.errors.shouldBeNull()

        val expectedMessage = "Deleted Human ${humanToDelete.id.value}"

        val responseMessage = (response.data as Map<String, String>)["deleteHuman"] as String
        responseMessage shouldBe expectedMessage

        val actualHumans = SqlHelper.humans()

        actualHumans.size shouldBe humans.size - 1
    }
})
