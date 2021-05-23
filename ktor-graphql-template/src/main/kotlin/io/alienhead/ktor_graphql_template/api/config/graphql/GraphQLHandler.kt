package io.alienhead.ktor_graphql_template.api.config.graphql

import com.expediagroup.graphql.server.execution.GraphQLRequestHandler
import com.expediagroup.graphql.server.execution.GraphQLServer
import com.fasterxml.jackson.databind.ObjectMapper
import io.alienhead.ktor_graphql_template.api.schema.graphQL
import io.ktor.request.ApplicationRequest

class GraphQLHandler(
    requestParser: KtorGraphQLRequestParser,
    contextFactory: KtorGraphQLContextFactory,
    requestHandler: GraphQLRequestHandler
) : GraphQLServer<ApplicationRequest>(requestParser, contextFactory, requestHandler)

fun getGraphQLServer(mapper: ObjectMapper): GraphQLHandler {
    val dataLoaderRegistryFactory = GraphQLDataLoaderRegistryFactory()
    val requestParser = KtorGraphQLRequestParser(mapper)
    val contextFactory = KtorGraphQLContextFactory()
    val requestHandler = GraphQLRequestHandler(graphQL, dataLoaderRegistryFactory)

    return GraphQLHandler(requestParser, contextFactory, requestHandler)
}
