package io.alienhead.ktor_graphql_template.api.schema.human

import io.alienhead.ktor_graphql_template.api.config.db.DatabaseFactory.suspendedTransaction
import io.alienhead.ktor_graphql_template.api.utils.asUuid
import io.alienhead.ktor_graphql_template.models.graphql.Human
import io.alienhead.ktor_graphql_template.models.graphql.Humans
import io.alienhead.ktor_graphql_template.models.graphql.toHuman
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import java.util.UUID

object HumanRepository {
    suspend fun insert(human: Human) {
        suspendedTransaction {
            Humans.insert {
                it[id] = human.id.asUuid()
                it[firstName] = human.firstName
                it[lastName] = human.lastName
            }
        }
    }

    suspend fun update(human: Human) {
        suspendedTransaction {
            Humans.update({ Humans.id eq human.id.asUuid() }) {
                it[firstName] = human.firstName
                it[lastName] = human.lastName
            }
        }
    }

    suspend fun delete(id: UUID) {
        suspendedTransaction {
            Humans.deleteWhere { Humans.id eq id }
        }
    }

    suspend fun selectAll() = suspendedTransaction {
        Humans.selectAll().map {
            it.toHuman()
        }
    }

    suspend fun selectById(id: UUID) = suspendedTransaction {
        Humans.select { Humans.id eq id }.firstOrNull()?.toHuman()
    }
}
