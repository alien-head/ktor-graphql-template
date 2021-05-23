package io.alienhead.ktor_graphql_template.api.schema

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.toSchema
import graphql.GraphQL
import io.alienhead.ktor_graphql_template.api.config.graphql.scalars.CustomHooks
import io.alienhead.ktor_graphql_template.api.schema.human.HumanSchema

private val config = SchemaGeneratorConfig(
    supportedPackages = listOf(
        "io.alienhead.ktor_graphql_template.api.schema",
        "io.alienhead.ktor_graphql_template.models"
    ),
    hooks = CustomHooks()
)

private val queries = listOf(
    HumanSchema.queries()
)
private val mutations = listOf(
    HumanSchema.mutations()
)

private val graphQLSchema = toSchema(config, queries, mutations)

val graphQL: GraphQL = GraphQL.newGraphQL(graphQLSchema).build()
