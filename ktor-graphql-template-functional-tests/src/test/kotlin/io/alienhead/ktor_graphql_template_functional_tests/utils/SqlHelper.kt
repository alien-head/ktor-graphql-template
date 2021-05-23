package io.alienhead.ktor_graphql_template_functional_tests.utils

import io.alienhead.ktor_graphql_template.models.graphql.Human
import io.alienhead.ktor_graphql_template.models.graphql.Humans
import io.alienhead.ktor_graphql_template.models.graphql.toHuman
import io.alienhead.ktor_graphql_template.models.graphql.toHumans
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object SqlHelper {
    init {
        Database.connect(
            "jdbc:postgresql://localhost:5434/template_db",
            driver = "org.postgresql.Driver",
            user = "example_user",
            password = "pass"
        )
    }

    fun humans(): List<Human> {
        return transaction {
            Humans.selectAll().toList().toHumans()
        }
    }

    fun randomHuman() = transaction {
        Humans.selectAll().toList().random().toHuman()
    }
}
