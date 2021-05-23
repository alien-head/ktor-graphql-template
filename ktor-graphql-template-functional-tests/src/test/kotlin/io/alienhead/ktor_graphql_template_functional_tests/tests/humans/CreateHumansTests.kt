package io.alienhead.ktor_graphql_template_functional_tests.tests.humans

import io.alienhead.ktor_graphql_template.models.graphql.HumanInput
import io.alienhead.ktor_graphql_template.models.graphql.createHumanContainer
import io.alienhead.ktor_graphql_template_functional_tests.utils.executeGraphQL
import io.alienhead.ktor_graphql_template_functional_tests.utils.toModel
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class CreateHumansTests : DescribeSpec({
    it("should create a human") {
        val expectedHuman = HumanInput("Iscalis", "ThaBigOneJohnson")

        val query = """
            mutation createHuman(${'$'}human: HumanInput!) {
                createHuman(human: ${'$'}human) {
                    id
                    firstName
                    lastName
                }
            }
        """.trimIndent()

        val response = executeGraphQL(query, mapOf<String, Any>("human" to expectedHuman))

        response.errors.shouldBeNull()

        val actualHuman = response.data.toModel(createHumanContainer::class.java).createHuman

        actualHuman.firstName shouldBe expectedHuman.firstName
        actualHuman.lastName shouldBe expectedHuman.lastName
    }
})
