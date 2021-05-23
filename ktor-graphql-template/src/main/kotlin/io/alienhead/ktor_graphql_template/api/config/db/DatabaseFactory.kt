package io.alienhead.ktor_graphql_template.api.config.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import javax.sql.DataSource

object DatabaseFactory {

    fun create(url: String, user: String, pass: String): DataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            jdbcUrl = url
            username = user
            password = pass
            validate()
        }
        return HikariDataSource(config)
    }

    suspend fun <T> suspendedTransaction(block: suspend () -> T): T = newSuspendedTransaction { block() }
}
