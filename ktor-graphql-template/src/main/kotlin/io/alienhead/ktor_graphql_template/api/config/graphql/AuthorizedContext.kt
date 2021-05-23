package io.alienhead.ktor_graphql_template.api.config.graphql

import com.expediagroup.graphql.generator.execution.GraphQLContext

data class AuthorizedContext(
    var mdc: String? = null,
    val token: String? = null
) : GraphQLContext
