package io.alienhead.ktor_graphql_template_functional_tests.tests.humans

import io.alienhead.ktor_graphql_template.models.graphql.HumansContainer
import io.alienhead.ktor_graphql_template_functional_tests.utils.SqlHelper
import io.alienhead.ktor_graphql_template_functional_tests.utils.executeGraphQL
import io.alienhead.ktor_graphql_template_functional_tests.utils.toModel
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class GetHumansTests : DescribeSpec({
    it("should get all humans") {
        val query = """
            {
                humans {
                    id
                    firstName
                    lastName
                }
            }
        """.trimIndent()

        val response = executeGraphQL(query)

        response.errors.shouldBeNull()

        val humans = response.data.toModel(HumansContainer::class.java).humans
        val expectedHumans = SqlHelper.humans()

        humans shouldBe expectedHumans
    }
})
