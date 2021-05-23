package io.alienhead.ktor_graphql_template_functional_tests.utils

import org.http4k.client.JavaHttpClient
import org.http4k.client.asGraphQLHandler
import org.http4k.core.Uri
import org.http4k.graphql.GraphQLRequest
import org.http4k.graphql.GraphQLResponse

fun graphQLHandler() = JavaHttpClient().asGraphQLHandler(Uri.of("http://localhost:8080/graphql"))

fun executeGraphQL(query: String, variables: Map<String, Any> = emptyMap()): GraphQLResponse {
    val handler = graphQLHandler()
    return handler(GraphQLRequest(query, variables = variables))
}

fun gvar(variableName: String) = "${'$'}$variableName"
