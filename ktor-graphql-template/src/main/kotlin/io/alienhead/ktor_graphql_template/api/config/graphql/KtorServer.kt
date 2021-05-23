package io.alienhead.ktor_graphql_template.api.config.graphql

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

class KtorServer {
    private val mapper = jacksonObjectMapper()
    private val ktorGraphQLServer = getGraphQLServer(mapper)

    /**
     * Handle incoming Ktor Http requests and send them back to the response methods.
     */
    suspend fun handle(applicationCall: ApplicationCall) {
        // Execute the query against the schema
        val result = ktorGraphQLServer.execute(applicationCall.request)

        if (result != null) {
            // write response as json
            @Suppress("BlockingMethodInNonBlockingContext")
            val json = mapper.writeValueAsString(result)
            applicationCall.response.call.respond(json)
        } else {
            applicationCall.response.call.respond(HttpStatusCode.BadRequest, "Invalid request")
        }
    }
}
