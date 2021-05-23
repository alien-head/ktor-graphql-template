package io.alienhead.ktor_graphql_template.api.config.plugins

import io.alienhead.ktor_graphql_template.api.config.db.DatabaseFactory
import io.alienhead.ktor_graphql_template.api.data.mockData
import io.ktor.application.Application
import kotlinx.coroutines.launch
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase(testing: Boolean = false) {
    val url = environment.config.property("ktor.db.uri").getString()

    val flywayUser = environment.config.property("ktor.db.flyway.username").getString()
    val flywayPass = environment.config.property("ktor.db.flyway.password").getString()
    flyway(url, flywayUser, flywayPass)

    val user = environment.config.property("ktor.db.username").getString()
    val pass = environment.config.property("ktor.db.password").getString()
    exposed(url, user, pass)

    launch {
        mockData()
    }
}

private fun flyway(url: String, user: String, pass: String) {
    Flyway.configure()
        .dataSource(url, user, pass)
        .locations("classpath:db/migration", "filesystem:src/main/resources/db/migration")
        .load()
        .migrate()
}

private fun exposed(url: String, user: String, pass: String) {
    Database.connect(DatabaseFactory.create(url, user, pass))
}
