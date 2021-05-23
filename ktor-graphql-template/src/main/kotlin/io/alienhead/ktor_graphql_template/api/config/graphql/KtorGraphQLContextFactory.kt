package io.alienhead.ktor_graphql_template.api.config.graphql

import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import io.ktor.request.ApplicationRequest
import java.util.UUID

// May not be necessary
class KtorGraphQLContextFactory : GraphQLContextFactory<AuthorizedContext, ApplicationRequest> {

    override suspend fun generateContext(request: ApplicationRequest): AuthorizedContext {
        // Parse any headers from the Ktor request
        val customHeader: String? = request.headers["my-custom-header"]

        return AuthorizedContext(UUID.randomUUID().toString())
    }
}
